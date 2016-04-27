package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.interfaces.IProdAttrDefSV;
import com.ai.slp.product.api.normproduct.param.*;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
public class IProdAttrDefSVImpl implements IProdAttrDefSV {
    @Override
    public PageInfo<ProductAttrDef> queryProductAttrs(ProductAttrDefParam productAttrDefParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public ProductAttrDef queryProductAttr(ProductAttr productAttr) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addProductAttr(List<ProductAttr> productAttrList) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateProductAttr(ProductAttr productAttr) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteProductAttr(ProductAttr productAttr) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public PageInfo<ProductAttrVal> queryProductAttrValues(ProductAttr productAttr) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public ProductAttrVal queryProductAttrVal(ProductAttrValParam ProductAttrValParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addProductAttrVal(List<ProductAttrValParam> productAttrValParamList) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateProductAttrVal(ProductAttrValParam productAttrValParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteProductAttrVal(ProductAttrValParam productAttrValParam) throws BusinessException, SystemException {
        return null;
    }
}
