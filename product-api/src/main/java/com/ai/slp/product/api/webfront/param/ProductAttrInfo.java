package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;

public class ProductAttrInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    private String attrDefId;

    /**
     * 属性值
     */
    private String attrDefValue;

    public String getAttrDefId() {
        return attrDefId;
    }

    public void setAttrDefId(String attrDefId) {
        this.attrDefId = attrDefId;
    }

    public String getAttrDefValue() {
        return attrDefValue;
    }

    public void setAttrDefValue(String attrDefValue) {
        this.attrDefValue = attrDefValue;
    }

}
