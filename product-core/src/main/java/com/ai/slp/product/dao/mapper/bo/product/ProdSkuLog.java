package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;

public class ProdSkuLog {
    private String logId;

    private String tenantId;

    private String skuId;

    private String prodId;

    private String storageGroupId;

    private String skuName;

    private String isSaleAttr;

    private String saleAttrs;

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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId == null ? null : storageGroupId.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getIsSaleAttr() {
        return isSaleAttr;
    }

    public void setIsSaleAttr(String isSaleAttr) {
        this.isSaleAttr = isSaleAttr == null ? null : isSaleAttr.trim();
    }

    public String getSaleAttrs() {
        return saleAttrs;
    }

    public void setSaleAttrs(String saleAttrs) {
        this.saleAttrs = saleAttrs == null ? null : saleAttrs.trim();
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