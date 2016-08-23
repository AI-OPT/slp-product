package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * Created by jackieliu on 16/8/23.
 */
public class SkuAttrVal implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 属性标识
     */
    private Long attrId;
    /**
     * 属性值标识
     */
    private String valId;
    /**
     * 属性名称
     */
    private String valName;
    /**
     * 属性序列号
     */
    private Short serialNumber;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getValId() {
        return valId;
    }

    public void setValId(String valId) {
        this.valId = valId;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
