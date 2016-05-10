package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProductCat {
    private String productCatId;

    private String tenantId;

    private String productCatName;

    private String parentProductCatId;

    private String firstLetter;

    private Short catLevel;

    private String isChild;

    private Short serialNumber;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId == null ? null : productCatId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName == null ? null : productCatName.trim();
    }

    public String getParentProductCatId() {
        return parentProductCatId;
    }

    public void setParentProductCatId(String parentProductCatId) {
        this.parentProductCatId = parentProductCatId == null ? null : parentProductCatId.trim();
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    public Short getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Short catLevel) {
        this.catLevel = catLevel;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild == null ? null : isChild.trim();
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