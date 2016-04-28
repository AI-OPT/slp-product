package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrParam;
import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
import com.ai.slp.product.api.normproduct.param.AttrValInfo;
import com.ai.slp.product.api.normproduct.param.AttrValParam;

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
    *  @ApiCode PROCAT_0200
     */
    public PageInfo<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
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
    *  @ApiCode PROCAT_0208
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
    *  @ApiCode PROCAT_0201
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
    *  @ApiCode PROCAT_0202
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
    *  @ApiCode PROCAT_0203
     */
    public BaseResponse deleteAttr(AttrParam attrParam)
            throws BusinessException, SystemException;
    @interface DeleteAttr{}
    
    /**
     * 属性的属性值查询
     * 
     * @param attrParam
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0204
     */
    public PageInfo<AttrValInfo> queryAttrValues(AttrParam attrParam)
            throws BusinessException, SystemException;
    @interface QueryAttrValues {}
    
    /**
     * 单个属性值查询
     * 
     * @param attrValParam
     * @return 符合条件的单个属性值
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode
     */
    public AttrValInfo queryAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException;
    @interface QueryAttrVal {}
    
    /**
     * 属性值添加
     * 
     * @param attrValParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0205
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
    *  @ApiCode PROCAT_0206
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
    *  @ApiCode PROCAT_0207
     */
    public BaseResponse deleteAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException;
    @interface DeleteAttrVal {}

}
