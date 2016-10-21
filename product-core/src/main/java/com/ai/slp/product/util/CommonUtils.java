package com.ai.slp.product.util;

import org.apache.commons.lang.StringUtils;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.slp.product.constants.ErrorCodeConstants;

/**
 * 通用工具类
 * Created by jackieliu on 16/5/2.
 */
public final class CommonUtils {
    private CommonUtils() {
    }

    /**
     * 检查租户id是否为空
     * @param tenantId
     */
    public static void checkTenantId(String tenantId){
        checkTenantId(tenantId,ErrorCodeConstants.TENANT_ID_NULL);
    }
    /**
     * 检查租户id是否为空
     */
    public static void checkTenantId(String tenantId,String errCode){
    	String nullErrorCode = StringUtils.isBlank(errCode)? ErrorCodeConstants.TENANT_ID_NULL:errCode;
    	if (StringUtils.isBlank(tenantId)){
    		throw new BusinessException(nullErrorCode,"租户标识不能为空");
    	}
    }
    
    /**
     * 检查销售商（商户）id是否为空，错误码为2000
     */
    public static void checkSupplierId(String supplierId){
    	checkSupplierId(supplierId,ErrorCodeConstants.SUPPLIER_ID_NULL);
    }
    /**
     * 检查销售商（商户）id是否为空
     */
    public static void checkSupplierId(String supplierId,String errCode){
    	String nullErrorCode = StringUtils.isBlank(errCode)? ErrorCodeConstants.SUPPLIER_ID_NULL:errCode;
        if (StringUtils.isBlank(supplierId)){
        	throw new BusinessException(nullErrorCode,"销售商（商户）标识不能为空");
        }
    }

    /**
     * 添加操作成功的responseHeader
     * @param baseResponse
     * @param resultMsg
     */
    public static BaseResponse addSuccessResHeader(BaseResponse baseResponse,String resultMsg){
        baseResponse.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,resultMsg));
        return baseResponse;
    }

    /**
     * 返回包含成功标识的response
     * @param resultMsg
     * @return
     */
    public static BaseResponse genSuccessResponse(String resultMsg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,resultMsg));
        return baseResponse;
    }
}
