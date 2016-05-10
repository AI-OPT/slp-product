package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * SKU单品的属性信息
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuAttrInfo implements Serializable {
    /**
     * 属性标识
     */
    private Long attrId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 序列号
     */
    private Short serialNumber;
    /**
     * SKU数量
     */
    private int skuNum;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(int skuNum) {
        this.skuNum = skuNum;
    }
}
