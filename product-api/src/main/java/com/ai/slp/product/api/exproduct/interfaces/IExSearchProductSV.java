package com.ai.slp.product.api.exproduct.interfaces;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;

/**
 * 提供给下游用户的查询商品服务
 * Date: 2016年6月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
@Path("/productsearch")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IExSearchProductSV {
    /**
     * 查询商品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode EX_PROD_0001
     * @RestRelativeURL productsearch/search
     */
	@POST
	@Path("/search")
    QueryProductResponse queryProductPage(QueryProductRequest request) throws BusinessException, SystemException;
    
}
