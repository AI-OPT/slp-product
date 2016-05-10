package com.ai.slp.product.api.productcat.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.productcat.param.MapForRes;
import com.ai.slp.product.api.common.param.PageInfoForRes;
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

/**
 * 属性及属性值管理接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IAttrAndValDefSV {
    
    /**
     * 属性分页查询
     * 
     * @param attrDefParam
     * @return 符合页数的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng1616
    *  @ApiCode ATTR_VAL_0200
     */
    public PageInfoForRes<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam)
            throws BusinessException, SystemException;
    @interface QueryAttrs {}
    
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
    public AttrInfo queryAttr(AttrPam attrPam)
            throws BusinessException, SystemException;
    @interface QueryAttr {}
    
    /**
     * 属性添加
     * 
     * @param attrParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0202
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
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0203
     */
    public BaseResponse updateAttr(AttrParam attrParam) 
            throws BusinessException, SystemException;
    @interface UpdateAttr{}
    
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
    public BaseResponse deleteAttr(AttrPam attrPam)
            throws BusinessException, SystemException;
    @interface DeleteAttr{}
    
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
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0206
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
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0207
     */
    public BaseResponse updateAttrVal(AttrValParam attrValParam)
            throws BusinessException, SystemException;
    @interface UpdateAttrVal {}
    
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
    public BaseResponse deleteAttrVal(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException;
    @interface DeleteAttrVal {}

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
    public AttrVal queryAttrVal(AttrValUniqueReq attrValUniqueReq)
            throws BusinessException, SystemException;
    @interface QueryAttrVal {}

    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode ATTR_VAL_0210
     */
    public MapForRes<AttrDef,List<AttrValDef>> queryAllAttrAndVal(BaseInfo baseInfo) 
            throws BusinessException, SystemException;
    @interface QueryAllAttrAndVal {}
}
