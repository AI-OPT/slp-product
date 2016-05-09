package com.ai.slp.product.api.productcat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.productcat.param.MapForRes;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.productcat.param.AttrDef;
import com.ai.slp.product.api.productcat.param.AttrDefInfo;
import com.ai.slp.product.api.productcat.param.AttrDefParam;
import com.ai.slp.product.api.productcat.param.AttrInfo;
import com.ai.slp.product.api.productcat.param.AttrPam;
import com.ai.slp.product.api.productcat.param.AttrParam;
import com.ai.slp.product.api.productcat.param.AttrVal;
import com.ai.slp.product.api.productcat.param.AttrValDef;
import com.ai.slp.product.api.productcat.param.AttrValInfo;
import com.ai.slp.product.api.productcat.param.AttrValPageQuery;
import com.ai.slp.product.api.productcat.param.AttrValParam;
import com.ai.slp.product.api.productcat.param.AttrValUniqueReq;
import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Component
public class IAttrAndValDefSVImpl implements IAttrAndValDefSV {
    @Autowired
    IAttrAndAttrvalBusiSV attrAndAttrvalBusiSV;

    @Override
    public PageInfoForRes<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException {
        return attrAndAttrvalBusiSV.queryAttrs(attrDefParam);
    }

    @Override
    public AttrInfo queryAttr(AttrPam attrPam) throws BusinessException, SystemException {
        return attrAndAttrvalBusiSV.queryAttrById(attrPam.getTenantId(), attrPam.getAttrId());
    }

    @Override
    public BaseResponse addAttr(List<AttrParam> attrParamList)
            throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.addAttr(attrParamList);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateAttr(AttrParam attrParam) throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.updateAttr(attrParam);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteAttr(AttrPam attrPam) throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.deleteAttr(attrPam);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public PageInfoForRes<AttrValInfo> queryAttrValues(AttrValPageQuery pageQuery)
            throws BusinessException, SystemException {
        return attrAndAttrvalBusiSV.queryAttrvals(pageQuery);
    }

    @Override
    public BaseResponse addAttrVal(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.addAttrVal(attrValParamList);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.updateAttrVal(attrValParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteAttrVal(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
        attrAndAttrvalBusiSV.deleteAttrVal(attrValUniqueReq);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public AttrVal queryAttrVal(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
        return attrAndAttrvalBusiSV.queryAttrVal(attrValUniqueReq);
    }

    @Override
    public MapForRes<AttrDef, List<AttrValDef>> queryAllAttrAndVal(BaseInfo baseInfo)
            throws BusinessException, SystemException {
        return attrAndAttrvalBusiSV.queryAllAttrAndVals(baseInfo.getTenantId());
    }

}
