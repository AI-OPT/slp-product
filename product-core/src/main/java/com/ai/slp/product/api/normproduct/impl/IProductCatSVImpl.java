package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/4/28.
 */
@Service
@Component
public class IProductCatSVImpl implements IProductCatSV {

    @Override
    public List<ProductCatInfo> queryProductCat(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addProductCat(List<ProductCatParam> pcpList)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateProductCat(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteProductCat(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public List<AttrDefInfo> queryProductCatAttr(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public List<AttrValInfo> queryProductCatAttrVal() throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addProductCatAttr(List<AttrDefResponse> lspad)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteProductCatAttrVal(AttrValParam productAttrValParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public Map<AttrDefInfo, List<AttrValInfo>> queryAttrByCatAndType(AttrQueryForCat attrQuery)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public Map<AttrDefInfo, List<AttrValInfo>> queryAttrByNormProduct(
            AttrQueryForNormProduct attrQuery) throws BusinessException, SystemException {
        return null;
    }

}
