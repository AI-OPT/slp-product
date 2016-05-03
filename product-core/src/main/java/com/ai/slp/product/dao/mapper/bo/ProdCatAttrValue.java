package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProdCatAttrValue {
    private String catAttrValueId;

    private String tenantId;

    private String attrvalueDefId;

    private String catAttrId;

    private String state;

    private Long operId;

    private Timestamp operTime;

    private Short serialNumber;

    public String getCatAttrValueId() {
        return catAttrValueId;
    }

    public void setCatAttrValueId(String catAttrValueId) {
        this.catAttrValueId = catAttrValueId == null ? null : catAttrValueId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId == null ? null : attrvalueDefId.trim();
    }

    public String getCatAttrId() {
        return catAttrId;
    }

    public void setCatAttrId(String catAttrId) {
        this.catAttrId = catAttrId == null ? null : catAttrId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }
}