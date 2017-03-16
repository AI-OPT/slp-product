package com.ai.slp.product.api.webfront.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class IProductDetailSVImpl implements IProductDetailSV {
	private static final Logger logger = LoggerFactory.getLogger(IProductDetailSVImpl.class);
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;

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
		/*if (StringUtils.isBlank(skuReq.getSkuId())&& StringUtils.isBlank(skuReq.getSkuAttrs())){
			throw new BusinessException("","SKU标识和SKU属性为空,无法处理");
		}*/
		if (StringUtils.isBlank(skuReq.getSkuId())){
			throw new BusinessException("","SKU标识,无法处理");
		}
		ProductSKUConfigResponse skuAttr = prodSkuBusiSV.querySkuAttr(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		if(skuAttr == null){
			skuAttr = new ProductSKUConfigResponse();
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "无数据");
		}
		skuAttr.setResponseHeader(responseHeader);
		return skuAttr;
	}
}
