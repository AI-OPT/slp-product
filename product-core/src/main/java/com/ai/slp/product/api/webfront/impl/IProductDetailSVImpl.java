package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.SKUInfo;
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
import com.ai.slp.product.util.ConvertUtils;
import com.alibaba.dubbo.config.annotation.Service;

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
		CommonUtils.checkTenantId(skuReq.getTenantId(), "");
		if(StringUtils.isBlank(skuReq.getSkuId())){
			throw new BusinessException(ErrorCodeConstants.PRODUCT_ID_NULL, "SKU标识不能为空");
		}
		ProductSKUResponse skuResponse = new ProductSKUResponse();
		/**
		 * 查询ES缓存
		 */
		IProductSearch productSearch = new ProductSearchImpl();
		List<SearchCriteria> criterias = new ArrayList<>();
		criterias.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID,skuReq.getSkuId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
		Result<SKUInfo> result = productSearch.searchByCriteria(criterias,0,30,null);
		List<SKUInfo> skuInfos = result.getContents();
		
		
		if (!CollectionUtil.isEmpty(skuInfos)) {
			SKUInfo skuInfo = skuInfos.get(0);
			if (skuInfo == null) {
				logger.warn("未查询到指定 的销售商品,租户ID:{},SKU标识:{},商品ID:{}", skuReq.getTenantId(), skuReq.getSkuId(),
						skuReq.getSkuId());
				throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST, "未查询到指定的SKU信息");
			}
			/**
			 * 商品属性
			 */
			skuResponse = ConvertUtils.convertToProductSKUResponse(skuInfo);
			if (skuResponse == null) {
				throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST, "SKU信息为null");
			}
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		skuResponse.setResponseHeader(responseHeader);
		return skuResponse;
	}

	@Override
	public ProductSKUConfigResponse queryProductSKUConfig(ProductSKURequest skuReq)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(skuReq.getTenantId(), "");
		if (StringUtils.isBlank(skuReq.getSkuId())) {
			throw new BusinessException(ErrorCodeConstants.PRODUCT_ID_NULL, "SKU标识不能为空");
		}
		ProductSKUConfigResponse configResponse = new ProductSKUConfigResponse();
		/**
		 * 查询ES缓存
		 */
		IProductSearch productSearch = new ProductSearchImpl();
		List<SearchCriteria> criterias = new ArrayList<>();
		criterias.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID,skuReq.getSkuId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		Result<SKUInfo> result = productSearch.search(criterias,1,30,null);
		List<SKUInfo> skuInfos = result.getContents();
		if (!CollectionUtil.isEmpty(skuInfos)) {
			SKUInfo skuInfo = skuInfos.get(0);
			configResponse = ConvertUtils.convertToProductSKUConfigResponse(skuInfo);
		} 
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		configResponse.setResponseHeader(responseHeader);
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
		} else {
			pictureList = pictureAtomSV.queryPicOfProd(product.getProdId());
		}
		for (ProdPicture picture : pictureList) {
			ProductImage productImage = new ProductImage();
			BeanUtils.copyProperties(productImage, picture);
			// 将主图放在第一个位置
			if (ProductConstants.ProdPicture.IsMainPic.YES.equals(picture.getIsMainPic())) {
				productImageList.add(0, productImage);
			} else {
				productImageList.add(productImage);
			}
		}
		return productImageList;
	}
}
