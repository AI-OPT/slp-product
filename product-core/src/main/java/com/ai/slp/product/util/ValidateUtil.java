package com.ai.slp.product.util;

import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.constants.ProductExceptCode;

/**
 * 校验工具类
 *
 *
 */
public final class ValidateUtil {
    private ValidateUtil() {
    }

    /**
     * 商品参数校验
     * @param request
     * @throws BusinessException
     * @UCUSER
     */
    public static void validateHomeProduct(ProductHomeRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAreaCode())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
        if (StringUtil.isBlank(request.getBasicOrgIdIs())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "代理商不能为空");
        }
        if (StringUtil.isBlank(request.getProductCatId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "类目不能为空");
        }
    }

    /**
     * 商品参数校验
     * @param request
     * @throws BusinessException
     * @UCUSER
     */
    public static void validateHotProduct(ProductQueryRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAreaCode())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
    }

    /**
     * 商品的参数检验的规则
     * @param request
     * @throws BusinessException
     * @author Gavin
     * @UCUSER
     */
    public static void validateHomeHotProduct(ProductHomeRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAreaCode())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
    }

    /**
     * 商品参数属性校验
     * @param request
     * @throws BusinessException
     * @UCUSER
     */
    public static void validateQueryProduct(ProductQueryRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAreaCode())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
        /*if (StringUtil.isBlank(request.getBasicOrgIdIs())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "代理商不能为空");
        }*/
        if (StringUtil.isBlank(request.getProductCatId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "类目不能为空");
        }
       /* if (StringUtil.isBlank(request.getAttrDefId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "属性定义值ID不能为空");
        }*/
        if (request.getPageInfo() == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "分页信息不能为空");
        } else {
            if (request.getPageInfo().getPageNo() == null) {
                throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "页码不能为空");
            } else if (request.getPageInfo().getPageSize() == null) {
                throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                        "分页大小不能为空");
            }
        }
    }
    
    /**
     * 搜索参数校验
     * @param request
     * @throws BusinessException
     * @UCUSER
     */
    public static void validateSearch(ProductQueryRequest request) throws BusinessException {
        if (request == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                    "入参不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "租户ID不能为空");
        }
        if (StringUtil.isBlank(request.getAreaCode())) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "地区入参不能为空");
        }
        if (request.getPageInfo() == null) {
            throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "分页信息不能为空");
        } else {
            if (request.getPageInfo().getPageNo() == null) {
                throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR, "页码不能为空");
            } else if (request.getPageInfo().getPageSize() == null) {
                throw new BusinessException(ProductExceptCode.ErrorCode.PARAM_NULL_ERROR,
                        "分页大小不能为空");
            }
        }
    }
}
