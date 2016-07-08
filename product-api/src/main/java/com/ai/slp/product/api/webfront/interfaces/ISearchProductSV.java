package com.ai.slp.product.api.webfront.interfaces;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;

/**
 * web商城---查询商品结果页面
 * Date: 2016年5月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
@Path("/commonsearch")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ISearchProductSV {
    /**
     * 首页跳转查询商品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0111
     * @RestRelativeURL commonsearch/jumpsearch
     */
	@POST
	@Path("/jumpsearch")
    ProductQueryResponse queryProductPage(ProductQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 查询热销推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0112
     * @RestRelativeURL commonsearch/hot
     */
	@POST
	@Path("/hot")
    List<ProductData> queryHotSellProduct(ProductQueryRequest request) throws BusinessException, SystemException;
    /**
     * 商品搜索查询
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0113
     * @RestRelativeURL commonsearch/search
     */
	@POST
	@Path("/search")
    ProductQueryResponse searchProduct(ProductQueryRequest request) throws BusinessException, SystemException;
}
