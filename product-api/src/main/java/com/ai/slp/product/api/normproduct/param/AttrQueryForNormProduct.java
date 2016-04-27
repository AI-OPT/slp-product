package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 标准品属性查询对象<br>
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrQueryForNormProduct extends BaseInfo {
    /**
     * 标准品标识
     */
    private String productId;
    /**
     * 属性类型
     */
    private String attrType;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }
}
