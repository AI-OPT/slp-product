package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 查询商品路由返回信息
 *
 * Date: 2016年5月31日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProductRoute extends BaseResponse {
    /**
     * 销售商品标识
     */
    public String productId;

    /**
     * 商品管理路由组ID,若未关联,则为空
     */
    public String routeGroupId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId;
    }
}
