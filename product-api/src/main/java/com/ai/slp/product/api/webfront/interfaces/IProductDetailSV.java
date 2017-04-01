package com.ai.slp.product.api.webfront.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;

/**
 * web商城--商品详情服务
 *
 * Date: 2016年5月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gucl
 */
@Path("/proddetail")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductDetailSV {
	
	/**
	 * 查询商品SKU详情
	 * @param productSKURequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @ApiDocMethod
     * @ApiCode PRODUCTWEB_001
     * @RestRelativeURL proddetail/searchsku
	 */
	@POST
	@Path("/searchsku")
	public ProductSKUResponse queryProducSKUById(ProductSKURequest productSKURequest) throws BusinessException,SystemException;
	@interface QueryProducSKUById{ }
	
	/**
	 * 查询商品SKU配置参数
	 * @param productSKURequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @ApiDocMethod
     * @ApiCode PRODUCTWEB_002
     * @RestRelativeURL proddetail/searchskuconfig
	 */
	@POST
	@Path("/searchskuconfig")
	public ProductSKUConfigResponse queryProductSKUConfig(ProductSKURequest productSKURequest) throws BusinessException,SystemException;
	@interface QueryProductSKUConfig{}
}
