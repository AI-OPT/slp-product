package com.ai.slp.product.api.productcat.impl;

import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.productcat.param.MapForRes;
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
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;

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
    *  @ApiCode ATTR_VAL_0200
     */
    @Override
    public PageInfoResponse<AttrDefInfo> queryPageAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrDefParam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0201
     */
    @Override
    public AttrInfo queryAttr(AttrPam attrPam) throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrPam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0202
     */
    @Override
    public BaseResponse createAttrs(List<AttrParam> attrParamList)
            throws BusinessException, SystemException {
    	if(attrParamList != null && !attrParamList.isEmpty()){
	    	for(AttrParam attrParam : attrParamList){
	    		CommonCheckUtils.checkTenantId(attrParam.getTenantId(),"");
	    	}
	        attrAndAttrvalBusiSV.addAttr(attrParamList);
    	}
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
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
    *  @ApiCode ATTR_VAL_0203
     */
    @Override
    public BaseResponse updateAttr(AttrParam attrParam) throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrParam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0204
     */
    @Override
    public BaseResponse deleteAttr(AttrPam attrPam) throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrPam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0205
     */
    @Override
    public PageInfoResponse<AttrValInfo> queryPageAttrvalue(AttrValPageQuery pageQuery)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(pageQuery.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0206
     */
    @Override
    public BaseResponse createAttrvalue(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException {
	    if(attrValParamList != null && !attrValParamList.isEmpty()){	
	    	for(AttrValParam attrValParam : attrValParamList){
	    		CommonCheckUtils.checkTenantId(attrValParam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0207
     */
    @Override
    public BaseResponse updateAttrvalue(AttrValParam attrValParam)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrValParam.getTenantId(),"");
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
    *  @ApiCode ATTR_VAL_0208
     */
    @Override
    public BaseResponse deleteAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrValUniqueReq.getTenantId(),"");
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
     * @ApiCode ATTR_VAL_0209
     */
    @Override
    public AttrVal queryAttrvalue(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(attrValUniqueReq.getTenantId(),"");
        return attrAndAttrvalBusiSV.queryAttrVal(attrValUniqueReq);
    }

    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0210
     */
    @Override
    public MapForRes<AttrDef, List<AttrValDef>> queryAllAttrAndVal(BaseInfo baseInfo)
            throws BusinessException, SystemException {
    	CommonCheckUtils.checkTenantId(baseInfo.getTenantId(),"");
        return attrAndAttrvalBusiSV.queryAllAttrAndVals(baseInfo.getTenantId());
    }

}
