package com.ai.slp.product.api.storage.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.param.*;

/**
 * 商城商品库存管理
 * 
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Path("/prodstorage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IStoreProductSV {
    
    /**
     * 商城商品库存查询
     * 
     * @param proProductParam
     * @return 符合条件的一类商品的集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode PROPRO_0100
     */
    public PageInfoResponse<ProProductRes> queryProProducts(ProProductParam proProductParam)
            throws BusinessException, SystemException;
    @interface QueryProProducts {}
    
}
