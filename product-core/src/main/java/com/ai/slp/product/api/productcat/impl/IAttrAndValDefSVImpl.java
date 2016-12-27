package com.ai.slp.product.api.productcat.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.*;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class IAttrAndValDefSVImpl implements IAttrAndValDefSV {
    @Autowired
    IAttrAndAttrvalBusiSV attrAndAttrvalBusiSV;

    /**
     * 属性分页查询
     * 
     * @param attrDefParam
     * @return 符合页数的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<AttrDefInfo> queryPageAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrDefParam.getTenantId(),"");
        return attrAndAttrvalBusiSV.queryAttrs(attrDefParam);
    }
    
    /**
     * 单个属性查询
     * 
     * @param attrPam
     * @return 通过ID查询到的单个属性
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public AttrInfo queryAttr(AttrPam attrPam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrPam.getTenantId(),"");
    	return attrAndAttrvalBusiSV.queryAttrById(attrPam.getTenantId(), attrPam.getAttrId());
    }

    /**
     * 属性批量添加
     * 
     * @param attrParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse createAttrs(List<AttrParam> attrParamList)
            throws BusinessException, SystemException {
    	BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
    	if(attrParamList != null && !attrParamList.isEmpty()){
	    	for(AttrParam attrParam : attrParamList){
	    		CommonUtils.checkTenantId(attrParam.getTenantId(),"");
	    	}
	        attrAndAttrvalBusiSV.addAttr(attrParamList);
	        responseHeader.setIsSuccess(true);
	        responseHeader.setResultCode("");
    	}else {
    		responseHeader.setIsSuccess(false);
            responseHeader.setResultCode("");
		}
        
        
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 属性修改
     * 
     * @param attrParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse updateAttr(AttrParam attrParam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrParam.getTenantId(),"");
        attrAndAttrvalBusiSV.updateAttr(attrParam);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }
    
    /**
     * 单个属性删除
     * 
     * @param attrPam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse deleteAttr(AttrPam attrPam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrPam.getTenantId(),"");
        attrAndAttrvalBusiSV.deleteAttr(attrPam);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 属性的属性值分页查询
     * 
     * @param pageQuery
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<AttrValInfo> queryPageAttrvalue(AttrValPageQuery pageQuery)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(pageQuery.getTenantId(),"");
        return attrAndAttrvalBusiSV.queryAttrvals(pageQuery);
    }
    
    /**
     * 属性值添加
     * 
     * @param attrValParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse createAttrvalue(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException {
	    if(attrValParamList != null && !attrValParamList.isEmpty()){	
	    	for(AttrValParam attrValParam : attrValParamList){
	    		CommonUtils.checkTenantId(attrValParam.getTenantId(),"");
	    	}
	    	attrAndAttrvalBusiSV.addAttrVal(attrValParamList);
	    }
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }
    
    /**
     * 属性值修改
     * 
     * @param attrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse updateAttrvalue(AttrValParam attrValParam)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrValParam.getTenantId(),"");
        attrAndAttrvalBusiSV.updateAttrVal(attrValParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 属性值删除
     * 
     * @param attrValUniqueReq
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse deleteAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrValUniqueReq.getTenantId(),"");
        attrAndAttrvalBusiSV.deleteAttrVal(attrValUniqueReq);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 单个属性值查询
     *
     * @param attrValUniqueReq
     * @return 符合条件的单个属性值信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public AttrVal queryAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(attrValUniqueReq.getTenantId(),"");
        return attrAndAttrvalBusiSV.queryAttrVal(attrValUniqueReq);
    }

    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseListResponse<AttrDef> queryAllAttrAndVal(BaseInfo baseInfo)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(baseInfo.getTenantId());
        List<AttrDef> attrDefList = attrAndAttrvalBusiSV.queryAllAttrAndVals(baseInfo.getTenantId());
        BaseListResponse<AttrDef> response = new BaseListResponse<>();
        response.setResult(attrDefList);
        CommonUtils.addSuccessResHeader(response,"OK");
        return response;
    }

}
