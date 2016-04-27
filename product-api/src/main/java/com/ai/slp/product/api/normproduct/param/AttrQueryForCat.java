package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 类目属性查询对象<br>
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrQueryForCat extends BaseInfo {
    /**
     * 类目标识
     */
    private String productCatId;
    /**
     * 属性类型
     */
    private String attrType;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }
}
