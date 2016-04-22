package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.ProductAttr;
import com.ai.slp.product.api.normproduct.param.ProductAttrDef;
import com.ai.slp.product.api.normproduct.param.ProductAttrDefParam;
import com.ai.slp.product.api.normproduct.param.ProductAttrVal;
import com.ai.slp.product.api.normproduct.param.ProductAttrValParam;

/**
 * 属性及属性值管理接口
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductAttrDefSV {
    
    /**
     * 属性查询
     * 
     * @param productAttrDefParam
     * @return 符合页数的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0200
     */
    public PageInfo<ProductAttrDef> queryProductAttr(ProductAttrDefParam productAttrDefParam)
            throws BusinessException, SystemException;
    @interface QueryProductAttr{}
    
    /**
     * 属性添加
     * 
     * @param productAttrList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0201
     */
    public BaseResponse addProductAttr(List<ProductAttr> productAttrList) 
            throws BusinessException, SystemException;
    @interface AddProductAttr{}
    
    /**
     * 属性修改
     * 
     * @param productAttr
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0202
     */
    public BaseResponse updateProductAttr(ProductAttr productAttr) 
            throws BusinessException, SystemException;
    @interface UpdateProductAttr{}
    
    /**
     * 属性删除
     * 
     * @param productAttrDefParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0203
     */
    public BaseResponse deleteProductAttr(ProductAttr productAttr)
            throws BusinessException, SystemException;
    @interface DeleteProductAttr{}
    
    /**
     * 属性的属性值查询
     * 
     * @param productAttr
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0204
     */
    public PageInfo<ProductAttrVal> queryProductAttrVal(ProductAttr productAttr)
            throws BusinessException, SystemException;
    @interface QueryProductAttrVal{}
    
    /**
     * 属性值添加
     * 
     * @param productAttrValParamList
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0205
     */
    public BaseResponse addProductAttrVal(List<ProductAttrValParam> productAttrValParamList)
            throws BusinessException, SystemException;
    @interface AddProductAttrVal{}
    
    /**
     * 属性值修改
     * 
     * @param productAttrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0206
     */
    public BaseResponse updateProductAttrVal(ProductAttrValParam productAttrValParam)
            throws BusinessException, SystemException;
    @interface UpdateProductAttrVal{}
    
    /**
     * 属性值删除
     * 
     * @param productAttrValParam
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0207
     */
    public BaseResponse deleteProductAttrVal(ProductAttrValParam productAttrValParam)
            throws BusinessException, SystemException;
    @interface DeleteProductAttrVal{}
    
}
