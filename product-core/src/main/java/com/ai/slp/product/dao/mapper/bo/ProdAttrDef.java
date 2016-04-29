package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProdAttrDef {
    private Long attrId;

    private String tenantId;

    private String attrName;

    private String firstLetter;

    private String valueWay;

    private String state;

    private Long operId;

    private Timestamp operTime;

    private String isAllowCustom;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    public String getValueWay() {
        return valueWay;
    }

    public void setValueWay(String valueWay) {
        this.valueWay = valueWay == null ? null : valueWay.trim();
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

    public String getIsAllowCustom() {
        return isAllowCustom;
    }

    public void setIsAllowCustom(String isAllowCustom) {
        this.isAllowCustom = isAllowCustom == null ? null : isAllowCustom.trim();
    }
}