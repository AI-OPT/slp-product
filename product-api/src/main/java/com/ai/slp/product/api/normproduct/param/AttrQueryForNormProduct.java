package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "标准品标识不能为空",groups = {INormProductSV.QueryAttrByNormProduct.class})
    private String productId;
    /**
     * 属性类型
     */
    @NotNull(message = "属性类型不能为空",groups = {INormProductSV.QueryAttrByNormProduct.class})
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
