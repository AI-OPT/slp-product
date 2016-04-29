package com.ai.slp.product.api.storage.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.storage.param.PageInfoWrapper;
import com.ai.slp.product.api.storage.param.ProProduct;
import com.ai.slp.product.api.storage.param.ProProductParam;

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
    public PageInfoWrapper<ProProduct> queryProProducts(ProProductParam proProductParam)
            throws BusinessException, SystemException;
    @interface QueryProProducts {}
    
}
