package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.common.param.MapForRes;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Component
public class IAttrAndValDefSVImpl implements IAttrAndValDefSV {

    @Override
    public PageInfoForRes<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public AttrInfo queryAttr(AttrPam attrPam) throws BusinessException, SystemException {
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
    public BaseResponse deleteAttr(AttrPam attrPam) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public PageInfoForRes<AttrValInfo> queryAttrValues(AttrValPageQuery pageQuery)
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
    public BaseResponse deleteAttrVal(AttrValUniqueReq attrValParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public AttrVal queryAttrVal(AttrValUniqueReq attrValParam)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public MapForRes<AttrDef, List<AttrValDef>> queryAllAttrAndVal()
            throws BusinessException, SystemException {
        return null;
    }

}
