package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Component
public class IAttrAndValDefSVImpl implements IAttrAndValDefSV {

    @Override
    public PageInfoWrapper<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public AttrDefInfo queryAttr(AttrParam attrParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addAttr(List<AttrParam> attrParamList)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateAttr(AttrParam attrParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteAttr(AttrParam attrParam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public PageInfoWrapper<AttrValInfo> queryAttrValues(AttrParam attrParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public AttrValInfo queryAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse addAttrVal(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException {
        return null;
    }
}
