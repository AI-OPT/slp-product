package com.ai.slp.product.api.webfront.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;

/**
 * web商城--商品详情服务
 *
 * Date: 2016年5月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gucl
 */
public interface IProductDetailSV {
	
	/**
	 * 查询商品SKU详情
	 * @param productSKURequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @ApiDocMethod
     * @ApiCode 
	 */
	public ProductSKUResponse queryProducSKUById(ProductSKURequest productSKURequest) throws BusinessException,SystemException;;
	@interface QueryProducSKUById{}
}
