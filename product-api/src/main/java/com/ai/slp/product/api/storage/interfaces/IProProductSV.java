package com.ai.slp.product.api.storage.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.storage.param.ProProdAttr;
import com.ai.slp.product.api.storage.param.ProProduct;
import com.ai.slp.product.api.storage.param.ProProductParam;
import com.ai.slp.product.api.storage.param.StorageTotal;

/**
 * 商城商品库存管理
 * 
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProProductSV {
    
    /**
     * 商城商品库存查询
     * 
     * @param proProductParam
     * @return 符合条件的一类商品的集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROPRO_0100
     */
    public PageInfo<ProProduct> queryProProducts(ProProductParam proProductParam)
            throws BusinessException, SystemException;
    @interface QueryProProducts {}
    
    /**
     * 单种商品属性等信息查询
     * 
     * @param proProductParam
     * @return 符合条件的商品属性
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROPRO_0101
     */
    public ProProdAttr queryProProduct(ProProductParam proProductParam)
            throws BusinessException, SystemException;
    @interface QueryProProduct {}
    
    /**
     * 查询库存量
     * 
     * @param storageTotal
     * @return 返回库存数量
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROPRO_0102
     */
    public long queryTotalNum(StorageTotal storageTotal)
            throws BusinessException, SystemException;
    @interface QueryTotalNum {}
    
    /**
     * 保存库存量
     * 
     * @param storageTotal
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROPRO_0103
     */
    public BaseResponse saveTotalNum(StorageTotal storageTotal)
            throws BusinessException, SystemException;
    @interface SaveTotalNum {}
    
}
