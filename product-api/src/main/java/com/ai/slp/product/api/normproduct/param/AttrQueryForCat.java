package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "类目标识不能为空",groups = {IProductCatSV.QueryAttrByCatAndType.class})
    private String productCatId;
    /**
     * 属性类型<br>
     * 1关键属性;2销售属性;3非关键属性
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
