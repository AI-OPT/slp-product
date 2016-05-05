package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class WarnReceiveStaff {
    private String warnReceiveStaffId;

    private String tenantId;

    private String obiectType;

    private String objectId;

    private Long staffId;

    private String warningType;

    private String warningContent;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public String getWarnReceiveStaffId() {
        return warnReceiveStaffId;
    }

    public void setWarnReceiveStaffId(String warnReceiveStaffId) {
        this.warnReceiveStaffId = warnReceiveStaffId == null ? null : warnReceiveStaffId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getObiectType() {
        return obiectType;
    }

    public void setObiectType(String obiectType) {
        this.obiectType = obiectType == null ? null : obiectType.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType == null ? null : warningType.trim();
    }

    public String getWarningContent() {
        return warningContent;
    }

    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent == null ? null : warningContent.trim();
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