package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.ProductStorageSale;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;

/**
 * 商品管理售中与仓库商品接口
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductStorageSaleSV {
    
    /**
     * 查询仓库中的全部商品
     * 
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0200
     */
    public PageInfo<ProductStorageSale> queryStorageProduct(ProductStorageSaleParam productStorageSaleParam)throws BusinessException,SystemException;
    @interface QueryStorageProduct {}
    

    /**
     * 查询仓库中售罄下架商品
     * 
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0201
     */
    public PageInfo<ProductStorageSale> querySoldOutDownProduct(ProductStorageSaleParam productStorageSaleParam)throws BusinessException,SystemException;
    @interface QuerySoldOutDownProduct {}
    
    /**
     * 查询仓库中待上架商品
     * 
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0202
     */
    public PageInfo<ProductStorageSale> queryWaitUpProduct(ProductStorageSaleParam productStorageSaleParam)throws BusinessException,SystemException;
    @interface QueryWaitUpProduct {}
    
    /**
     * 查询仓库中废弃下架商品
     * 
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0203
     */
    public PageInfo<ProductStorageSale> queryDisDownProduct(ProductStorageSaleParam productStorageSaleParam)throws BusinessException,SystemException;
    @interface QueryDisDownProduct {}
    
    /**
     * 查询仓库中售中商品
     * 
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0203
     */
    public PageInfo<ProductStorageSale> querySaleingProduct(ProductStorageSaleParam productStorageSaleParam)throws BusinessException,SystemException;
    @interface QuerySaleingProduct {}
    
    
}
