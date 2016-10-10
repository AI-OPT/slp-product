package com.ai.slp.product.api.productserver.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.productserver.param.ProdInfoQuery;
import com.ai.slp.product.api.productserver.param.ProductInfoOfSku;

/**
 * 商品服务提供接口<br>
 *
 * Date: 2016年10月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/newProductServer")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductServerSV {

    /**
     * 根据销售商品编码查询商品单品详情信息<br>
     *
     * @param query 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL newProductServer/searchProdInfoByCode
     * @ApiCode NEW_PROD_SERVER_0100
     */
    @POST
    @Path("/searchProdInfoByCode")
    public ProductInfoOfSku queryProductSkuByProdCode(ProdInfoQuery query)
            throws BusinessException,SystemException;
    @interface queryProductSkuByProdCode{}
}
