package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商城商品详情对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfoQuery extends BaseInfo {
    /**
     * 商品标识
     */
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
