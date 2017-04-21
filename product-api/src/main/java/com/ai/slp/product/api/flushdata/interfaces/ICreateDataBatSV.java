package com.ai.slp.product.api.flushdata.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.flushdata.params.CreateDataRequest;

@Path("/createproduct")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICreateDataBatSV {
	/**
     * 批量制造商品
     * @RestRelativeURL createproduct/createProductBat
     */
    @POST
    @Path("/createProductBat")
    public void createProductBat(CreateDataRequest request)
            throws BusinessException,SystemException;
    @interface flushProductData {}
    
}
