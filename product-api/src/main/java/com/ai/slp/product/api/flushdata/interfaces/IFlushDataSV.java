package com.ai.slp.product.api.flushdata.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.flushdata.params.FlushDataRequest;

@Path("/flushdata")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IFlushDataSV {
	/**
     * 填充ES数据接口
     * @RestRelativeURL flushdata/flushProductData
     */
    @POST
    @Path("/flushProductData")
    public BaseResponse flushProductData(FlushDataRequest request)
            throws BusinessException,SystemException;
    @interface flushProductData {}
    
    
    /**
     * 填充ES数据接口
     * @RestRelativeURL flushdata/flushCommentData
     */
    @POST
    @Path("/flushCommentData")
    public BaseResponse flushCommentData(FlushDataRequest request)
    		throws BusinessException,SystemException;
    @interface flushCommentData {}
}
