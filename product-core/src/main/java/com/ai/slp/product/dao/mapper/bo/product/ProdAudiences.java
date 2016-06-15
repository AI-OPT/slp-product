package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;

public class ProdAudiences {
    private Long prodAudiencesId;

    private String tenantId;

    private String prodId;

    private String userType;

    private String userId;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public Long getProdAudiencesId() {
        return prodAudiencesId;
    }

    public void setProdAudiencesId(Long prodAudiencesId) {
        this.prodAudiencesId = prodAudiencesId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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