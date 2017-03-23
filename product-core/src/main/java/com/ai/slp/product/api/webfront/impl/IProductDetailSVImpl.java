package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProdAttrValue;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUAttr;
import com.ai.slp.product.api.webfront.param.ProductSKUAttrValue;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.vo.SkuStorageVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

@Service(validation = "true")
@Component
public class IProductDetailSVImpl implements IProductDetailSV {
	private static final Logger logger = LoggerFactory.getLogger(IProductDetailSVImpl.class);
	
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;
	
	@Autowired
	IProdAttrAtomSV prodAttrAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProdPictureAtomSV pictureAtomSV;
	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	@Autowired
	IProdSaleAllAtomSV prodSaleAllAtomSV;
	@Autowired
	IStorageNumBusiSV storageNumBusiSV;

	private static List<String> ACTIVE_STATUS_LIST = new ArrayList<>();

	static {
		ACTIVE_STATUS_LIST.add(ProductConstants.Product.State.IN_SALE);
		ACTIVE_STATUS_LIST.add(ProductConstants.Product.State.SALE_OUT);
	}
	
	@Override
	public ProductSKUResponse queryProducSKUById(ProductSKURequest skuReq) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())&& StringUtils.isBlank(skuReq.getSkuAttrs())){
			throw new BusinessException("","SKU标识和SKU属性为空,无法处理");
		}
		// 查询商品
		Product product = productAtomSV.selectByProductId(skuReq.getTenantId(), skuReq.getSkuId());
		
		if (product == null) {
			logger.warn("未查询到指定的销售商品,租户ID:{},SKU标识:{},商品ID:{}",skuReq.getTenantId(), skuReq.getSkuId(), skuReq.getSkuId());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		//若不是有效状态,则不处理
		if (!ACTIVE_STATUS_LIST.contains(product.getState())){
			logger.warn("销售商品为无效状态,租户ID:{},SKU标识:{},商品ID:{},状态:{}",skuReq.getTenantId(), skuReq.getSkuId(), skuReq.getSkuId(),product.getState());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		/**
		 * 先查询缓存,没有的话查数据库
		 */
		ICacheClient cacheClient = MCSClientFactory.getCacheClient("");
		ProductSKUResponse skuResponse = new ProductSKUResponse();
		
		String attrStr = cacheClient.get(CommonConstants.PROD_SKU_ATTR+skuReq.getSkuId());
		if(StringUtils.isEmpty(attrStr)){
		/**
		 * 解析json数据
		 */
		List<ProdAttrValue> prodAttrValues = JSON.parseObject(attrStr, new TypeReference<List<ProdAttrValue>>(){});
		skuResponse.setProductAttrList(prodAttrValues);
		}else
		{
		BeanUtils.copyProperties(skuResponse, product);
		List<ProdCatAttrAttch> prodCatAttrAttchs = prodSkuBusiSV.querySkuDetail(skuReq.getTenantId(),product,skuReq.getSkuAttrs());
		// SKU图片
		String attrPic = null;
		// 查询已设置SKU的属性和属性值信息
		if(!CollectionUtils.isEmpty(prodCatAttrAttchs)){
		for (ProdCatAttrAttch prodCatAttrAttch : prodCatAttrAttchs) {
			// 查询属性对应属性值集合
			List<ProdAttr> prodAttrs = prodAttrAtomSV.queryOfProdAndAttr(skuReq.getTenantId(), product.getProdId(), prodCatAttrAttch.getAttrId());
			
			List<ProdAttrValue> attrValueList = new ArrayList<>();
			//ProdAttrParam prodAttrParam = new ProdAttrParam();
			for (ProdAttr prodAttr : prodAttrs) {
			ProdAttrValue prodAttrValue = new ProdAttrValue();
			prodAttrValue.setAttrvalueDefId(prodAttr.getAttrvalueDefId());
			prodAttrValue.setAttrValueName(prodAttr.getAttrValueName());
			attrValueList.add(prodAttrValue);
			// 若此属性是否包含图片
			if (ProductCatConstants.ProductCatAttr.IsPicture.YES.equals(prodCatAttrAttch.getIsPicture())) {
				// 查询主图
				ProdPicture prodPicture = pictureAtomSV.queryMainOfProdIdAndAttrVal(product.getProdId(),prodAttr.getAttrvalueDefId());
				
				if (prodPicture != null) {
					ProductImage productImage = new ProductImage();
					BeanUtils.copyProperties(productImage, prodPicture);
					prodAttrValue.setImage(productImage);
				}
			}
			attrValueList.add(prodAttrValue);
			}
			//prodAttrParam.setAttrValueList(attrValueList);
			skuResponse.setProductAttrList(attrValueList);
			}
		}
		/**
		 * 查询ES缓存
		 */
		 IProductSearch productSearch = new ProductSearchImpl();
		 ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(skuReq.getSkuId()).build();
		 Result<Map<String, Object>> result = productSearch.search(productSearchCriteria);
	        List<Map<String,Object>> reslist = result.getContents();
	        String info = JSON.toJSONString(reslist);
	        List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
	        if(!CollectionUtil.isEmpty(skuList)){
	        	SKUInfo skuInfo = skuList.get(0);
	        	BeanUtils.copyProperties(skuResponse, skuInfo);
	        }else
	        {
	        // 设置主图
		skuResponse.setProductImageList(getProductSkuPic(attrPic, product));
		// 设置评论数
		//skuResponse.setCommentNum((long)prodCommentAtomSV.countBySkuId(product.getProdId(),false));
		// 设置商品销量
		skuResponse.setSaleNum(prodSaleAllAtomSV.queryNumOfProduc(skuReq.getTenantId(), product.getProdId()));
		
		// 获取当前库存和价格
		SkuStorageVo skuStorageVo = storageNumBusiSV.queryStorageOfSku(skuReq.getTenantId(), product.getProdId());
		skuResponse.setUsableNum(skuStorageVo.getUsableNum());
		skuResponse.setSalePrice(skuStorageVo.getSalePrice());
	        }
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		skuResponse.setResponseHeader(responseHeader);
		return skuResponse;
		}

	@Override
	public ProductSKUConfigResponse queryProductSKUConfig(ProductSKURequest skuReq)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())){
			throw new BusinessException("","SKU标识,无法处理");
		}
		// 查询商品
		//SKUID等同于PRODID
		Product product = productAtomSV.selectByProductId(skuReq.getTenantId(), skuReq.getSkuId());
		if (product == null) {
			logger.warn("未查询到指定的销售商品,租户ID:{},SKU标识:{},商品ID:{}",skuReq.getTenantId(), skuReq.getSkuId(), skuReq.getSkuId());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		//若不是有效状态,则不处理
		if (!ACTIVE_STATUS_LIST.contains(product.getState())){
			logger.warn("销售商品为无效状态,租户ID:{},SKU标识:{},商品ID:{},状态:{}",skuReq.getTenantId(), skuReq.getSkuId(), skuReq.getSkuId(),product.getState());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		/**
		 * 查询缓存,若不存在则查数据库
		 */
		ICacheClient cacheClient = MCSClientFactory.getCacheClient("");
		String dataStr = cacheClient.get(CommonConstants.SKU_ATTR_CONFIG+skuReq.getSkuId());
		ProductSKUConfigResponse configResponse = null;
		if(StringUtils.isEmpty(dataStr)){
			/**
			 * 解析json数据
			 */
			ProductSKUConfigResponse productSKUData = JSON.parseObject(dataStr, new TypeReference<ProductSKUConfigResponse>(){});
			BeanUtils.copyProperties(configResponse, productSKUData);
		}else
		{
		List<AttrInfo> skuAttr = prodSkuBusiSV.querySkuAttr(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		configResponse = new ProductSKUConfigResponse();
		// 查询关键属性
		List<AttrInfo> attrInfos = prodAttrAtomSV.queryAttrOfProdId(skuReq.getSkuId());
		List<ProductSKUAttr> productSKUAttrs = new ArrayList<ProductSKUAttr>();
		List<ProductSKUAttrValue> productSKUAttrValues = new ArrayList<>();
		ProductSKUAttr productSKUAttr = new ProductSKUAttr();
		if(!CollectionUtils.isEmpty(attrInfos)){
			for (AttrInfo attrInfo : attrInfos) {
				ProductSKUAttrValue productSKUAttrValue = new ProductSKUAttrValue();
				BeanUtils.copyProperties(productSKUAttrValue, attrInfo);
				productSKUAttrValues.add(productSKUAttrValue);
			}
			AttrInfo attrInfo = attrInfos.get(0);
			productSKUAttr.setAttrId(Long.parseLong(attrInfo.getAttrid()));
			productSKUAttr.setAttrName(attrInfo.getAttrname());
			productSKUAttr.setAttrValueList(productSKUAttrValues);
		}
		productSKUAttrs.add(productSKUAttr);
		configResponse.setProductAttrList(productSKUAttrs);
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		if(skuAttr == null){
			configResponse = new ProductSKUConfigResponse();
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "无数据");
			}
		configResponse.setResponseHeader(responseHeader);
		}
		return configResponse;
	}
	
	/**
	 * 获取SKU商品的展示图片
	 */
	private List<ProductImage> getProductSkuPic(String attrPic, Product product) {
		List<ProdPicture> pictureList = null;
		List<ProductImage> productImageList = new ArrayList<>();
		if (StringUtils.isNotBlank(attrPic)) {
			pictureList = pictureAtomSV.queryProdIdAndAttrVal(product.getProdId(), attrPic);
		} else{
			pictureList = pictureAtomSV.queryPicOfProd(product.getProdId());
		}
		for (ProdPicture picture : pictureList) {
			ProductImage productImage = new ProductImage();
			BeanUtils.copyProperties(productImage, picture);
			// 将主图放在第一个位置
			if (ProductConstants.ProdPicture.IsMainPic.YES.equals(picture.getIsMainPic())) {
				productImageList.add(0, productImage);
			} else{
				productImageList.add(productImage);
			}
		}
		return productImageList;
	}
}
