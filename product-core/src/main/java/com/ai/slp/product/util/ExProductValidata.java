package com.ai.slp.product.util;

import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.constants.ProductExceptCode;

/**
 *校验工具类 
 *
 */
public final class ExProductValidata {
    private ExProductValidata() {
    }

    public static void validateSearch(QueryProductRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if(StringUtil.isBlank(request.getUserId())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "用户ID不能为空");
        }
        if(StringUtil.isBlank(request.getUserType())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "用户类型不能为空");
        }
        if(StringUtil.isBlank(request.getProductCatId())){
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "类目ID不能为空");
        }
        if (request.getPageNo() == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "页码不能为空");
        } else if (request.getPageSize() == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "分页大小不能为空");
        }
    }
}
