package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * SKU单品的属性值信息
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuAttrValInfo implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
     * 属性值ID
     */
    private String attrvalueDefId;
    /**
     * 属性值名称
     */
    private String attrValueName;
    /**
     * 序列号
     */
    private Short serialNumber;

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
