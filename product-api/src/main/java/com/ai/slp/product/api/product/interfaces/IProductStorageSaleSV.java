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
     * 查询仓库中的全部商品<br>
     * 不判断状态返回所有商品<br>
     * 根据状态不同返回不同类型的集合：<br>
     * 5在售61售罄下架62废弃下架<br>
     * 待上架:6仓库中（审核通过放入） 61售罄下架 定时上架-通过上架类型判断<br>
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
    
    
}
