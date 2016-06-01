package com.ai.slp.product.util;


import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.constants.ProductExceptCode;


public  final class ValidateUtil {
    
    public static void validateHomeProduct(ProductHomeRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "商品首页查询入参不能为空");
        }
        if(StringUtil.isBlank(request.getAreaCode())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
        if(StringUtil.isBlank(request.getBasicOrgIdIs())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "代理商不能为空");
        }
        if(StringUtil.isBlank(request.getProductCatId())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "类目不能为空");
        }
    }
    public static void validateHotProduct(ProductQueryRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "商品首页查询入参不能为空");
        }
        if(StringUtil.isBlank(request.getAreaCode())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
        if(StringUtil.isBlank(request.getProductCatId())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "类目不能为空");
        }
    }
}
