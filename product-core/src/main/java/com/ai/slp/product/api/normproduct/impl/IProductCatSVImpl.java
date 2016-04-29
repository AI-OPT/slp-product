package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/4/28.
 */
@Service
@Component
public class IProductCatSVImpl implements IProductCatSV {
    @Autowired
    IProductCatBusiSV productCatBusiSV;

    @Override
    public PageInfoWrapper<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery) throws BusinessException, SystemException {
        if (StringUtils.isBlank(pageQuery.getTenantId()))
            throw new BusinessException("","租户id不能为空");
        PageInfoWrapper<ProductCatInfo> catInfoPageInfoWrapper = productCatBusiSV.queryProductCat(pageQuery);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        catInfoPageInfoWrapper.setResponseHeader(responseHeader);
        return catInfoPageInfoWrapper;
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

    @Override
    public BaseResponse addAttrForCatAndType(AddCatAttrParam addCatAttrParam)
            throws BusinessException, SystemException {
        return null;
    }

}
