package com.ai.slp.product.api.product.interfaces;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.product.param.FlushDataRequest;

public interface IFlushDataSV {
	/**
     * 填充ES数据接口
     * @RestRelativeURL product/flushProductData
     */
    @POST
    @Path("/flushProductData")
    public BaseResponse flushProductData(FlushDataRequest request)
            throws BusinessException,SystemException;
    
    
    /**
     * 填充ES数据接口
     * @RestRelativeURL product/flushCommentData
     */
    @POST
    @Path("/flushCommentData")
    public BaseResponse flushCommentData(FlushDataRequest request)
    		throws BusinessException,SystemException;
}
