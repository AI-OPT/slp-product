package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductSkuInfo;
import com.ai.slp.product.api.product.param.SkuInfoQuery;

/**
 * 商城商品服务提供接口<br>
 *
 * Date: 2016年5月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IProductServerSV {
    /**
     * 根据销售商品sku标识查询商品单品详情信息<br>
     *
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_SERVER_0100
     */
    public ProductSkuInfo queryProductSkuById(SkuInfoQuery skuInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryProducSkutById{}
}
