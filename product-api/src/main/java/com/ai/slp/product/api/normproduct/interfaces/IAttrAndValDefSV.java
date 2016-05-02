package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.*;

/**
 * 属性及属性值管理接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IAttrAndValDefSV {
    
    /**
     * 属性查询
     * 
     * @param attrDefParam
     * @return 符合页数的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0200
     */
    public PageInfoForRes<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException;
    @interface QueryAttrs {}
    
    /**
     * 单个属性查询
     * 
     * @param attrParam
     * @return 通过ID查询单个属性
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0208
     */
    public AttrDefInfo queryAttr(AttrParam attrParam)
            throws BusinessException, SystemException;
    @interface QueryAttr {}
    
    /**
     * 属性添加
     * 
     * @param attrParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0201
     */
    public BaseResponse addAttr(List<AttrParam> attrParamList) 
            throws BusinessException, SystemException;
    @interface AddAttr{}
    
    /**
     * 属性修改
     * 
     * @param attrParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0202
     */
    public BaseResponse updateAttr(AttrParam attrParam) 
            throws BusinessException, SystemException;
    @interface UpdateAttr{}
    
    /**
     * 属性删除
     * 
     * @param attrParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0203
     */
    public BaseResponse deleteAttr(AttrParam attrParam)
            throws BusinessException, SystemException;
    @interface DeleteAttr{}
    
    /**
     * 属性的属性值查询
     * 
     * @param pageQuery
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0204
     */
    public PageInfoForRes<AttrValInfo> queryAttrValues(AttrValPageQuery pageQuery)
            throws BusinessException, SystemException;
    @interface QueryAttrValues {}
    
    /**
     * 属性值添加
     * 
     * @param attrValParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0205
     */
    public BaseResponse addAttrVal(List<AttrValParam> attrValParamList)
            throws BusinessException, SystemException;
    @interface AddAttrVal{}
    
    /**
     * 属性值修改
     * 
     * @param attrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0206
     */
    public BaseResponse updateAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException;
    @interface UpdateAttrVal {}
    
    /**
     * 属性值删除
     * 
     * @param attrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_VAL_0207
     */
    public BaseResponse deleteAttrVal(AttrValUniqueReq attrValParam)
            throws BusinessException, SystemException;
    @interface DeleteAttrVal {}

    /**
     * 单个属性值查询
     *
     * @param attrValParam
     * @return 符合条件的单个属性值
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
     * @ApiCode ATTR_VAL_0208
     */
    public AttrValInfo queryAttrVal(AttrValUniqueReq attrValParam)
            throws BusinessException, SystemException;
    @interface QueryAttrVal {}

}
