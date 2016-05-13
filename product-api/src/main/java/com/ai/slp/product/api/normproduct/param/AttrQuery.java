package com.ai.slp.product.api.normproduct.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

/**
 * 标准品属性查询对象<br>
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 标准品标识,必填
     */
    @NotBlank(message = "标准品标识不能为空",groups = {INormProductSV.QueryAttrByNormProduct.class})
    private String productId;
    /**
     * 属性类型,必填<br>
     *     1:关键属性;2:销售属性;3:非关键属性
     */
    @NotBlank(message = "属性类型不能为空",groups = {INormProductSV.QueryAttrByNormProduct.class})
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
