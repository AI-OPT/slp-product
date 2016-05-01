package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.normproduct.param.*;

/**
 * 类目属性组接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface ICatAttrGroupSV {
    
    /**
     * 查询类目分组
     * 
     * @param proAttrGroupParam
     * @return 符合条件的集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0301
     */
    public PageInfoWrapper<ProAttrGroup> queryProAttrGroup(ProAttrGroupParam proAttrGroupParam)
            throws BusinessException, SystemException;
    @interface QueryProAttrGroup{}
    
    /**
     * 添加类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0302
     */
    public BaseResponse addProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface AddProAttrGroup {}
    
    /**
     * 修改类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0303
     */
    public BaseResponse updateProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface UpdateProAttrGroup{}
    
    /**
     * 删除类目分组
     * 
     * @param proAttrGroupParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0304
     */
    public BaseResponse deleteProAttrGroup(ProAttrGroupParam proAttrGroupParam) 
            throws BusinessException, SystemException;
    @interface DeleteProAttrGroup{}
    
    /**
     * 类目组内属性查询：仅在本类目组内查询，加类目属性组ID为必须条件
     * 
     * @param proAttrGroupParam
     * @return 符合条件的商品属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0305
     */
    public List<AttrDefInfo> queryGroupAttr(ProAttrGroupParam proAttrGroupParam)
            throws BusinessException, SystemException; 
    @interface QueryGroupAttr {}
    
    /**
     * 类目组内属性添加
     * 
     * @param listProAttrVal
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0306
     */
    public BaseResponse addGroupAttr(List<AttrValParam> listProAttrVal)
            throws BusinessException, SystemException; 
    @interface AddGroupAttr {}
    
    /**
     * 类目组内属性删除
     * 
     * @param listProAttrVal
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode ATTR_GROUP_0307
     */
    public BaseResponse deleteGroupAttr(List<AttrValParam> listProAttrVal)
            throws BusinessException, SystemException; 
    @interface DeleteGroupAttr {}
    
    
}
