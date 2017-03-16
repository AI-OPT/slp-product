package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductSKUAttr;
import com.ai.slp.product.api.webfront.param.ProductSKUAttrValue;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class IProductDetailSVImpl implements IProductDetailSV {
	private static final Logger logger = LoggerFactory.getLogger(IProductDetailSVImpl.class);
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;
	
	@Autowired
	IProdAttrAtomSV prodAttrAtomSV;

	@Override
	public ProductSKUResponse queryProducSKUById(ProductSKURequest skuReq) throws BusinessException, SystemException {
		logger.debug("---= queryProducSKUById start time:"+System.currentTimeMillis());
		CommonUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())&& StringUtils.isBlank(skuReq.getSkuAttrs())){
			throw new BusinessException("","SKU标识和SKU属性为空,无法处理");
		}
		ProductSKUResponse skuDetail = prodSkuBusiSV.querySkuDetail(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		if(skuDetail==null){
			skuDetail = new ProductSKUResponse();
			responseHeader = new ResponseHeader(true, ResultCodeConstants.FAIL_CODE, "查询失败");
		}
		skuDetail.setResponseHeader(responseHeader);
		return skuDetail;
	}

	@Override
	public ProductSKUConfigResponse queryProductSKUConfig(ProductSKURequest skuReq)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())){
			throw new BusinessException("","SKU标识,无法处理");
		}
		List<AttrInfo> skuAttr = prodSkuBusiSV.querySkuAttr(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		ProductSKUConfigResponse configResponse = new ProductSKUConfigResponse();
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
		return configResponse;
	}
}
