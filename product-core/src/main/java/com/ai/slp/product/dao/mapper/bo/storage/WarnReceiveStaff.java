package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class WarnReceiveStaff {
    private Long warnReceiveStaffId;

    private String obiectType;

    private String objectId;

    private Long staffId;

    private String warningType;

    private String warningContent;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public Long getWarnReceiveStaffId() {
        return warnReceiveStaffId;
    }

    public void setWarnReceiveStaffId(Long warnReceiveStaffId) {
        this.warnReceiveStaffId = warnReceiveStaffId;
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