package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductListQuery;

/**
 * 商城商品操作<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IProductSV {
    /**
     * 查询商品列表<br>
     *
     * @param productQuery 查询对象
     * @return 商品信息列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0100
     */
    public PageInfo<Product4List> queryProductList(ProductListQuery productQuery)
        throws BusinessException,SystemException;
    @interface QueryProductList{}

    /**
     * 根据商品标识查询商品详情<br>
     *
     * @param productInfoQuery 查询对象
     * @return 商品信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0101
     */
    public ProductInfo queryProductById(ProductInfoQuery productInfoQuery)
        throws BusinessException,SystemException;
    @interface QueryProductById{}

    //批量更新库存价格和SKU价格

}
