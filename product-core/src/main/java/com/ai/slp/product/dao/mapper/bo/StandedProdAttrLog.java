package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class StandedProdAttrLog {
    private String logId;

    private String tenantId;

    private Long standedProdAttrId;

    private String standedProdId;

    private Long attrId;

    private String attrvalueDefId;

    private String attrValueName;

    private String attrValueName2;

    private Short serialNumber;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Long getStandedProdAttrId() {
        return standedProdAttrId;
    }

    public void setStandedProdAttrId(Long standedProdAttrId) {
        this.standedProdAttrId = standedProdAttrId;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId == null ? null : standedProdId.trim();
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId == null ? null : attrvalueDefId.trim();
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName == null ? null : attrValueName.trim();
    }

    public String getAttrValueName2() {
        return attrValueName2;
    }

    public void setAttrValueName2(String attrValueName2) {
        this.attrValueName2 = attrValueName2 == null ? null : attrValueName2.trim();
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
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
}