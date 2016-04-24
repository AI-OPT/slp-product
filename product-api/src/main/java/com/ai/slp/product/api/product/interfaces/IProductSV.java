package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.*;

import java.util.List;

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

    /**
     * 批量更新SKU销售价<br>
     *
     * @param skuSalPrices sku销售价结婚
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0102
     */
    public BaseResponse updateMultSKUSalePrice(List<ProSkuSalPrice> skuSalPrices)
            throws BusinessException,SystemException;
    @interface UpdateMultSKUSalePrice{}
}
