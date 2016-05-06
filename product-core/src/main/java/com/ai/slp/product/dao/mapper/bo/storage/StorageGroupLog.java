package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class StorageGroupLog {
    private String logId;

    private String tenantId;

    private String storageGroupId;

    private String standedProdId;

    private Long routeGroupId;

    private String storageGroupName;

    private Long lowSalePrice;

    private Long highSalePrice;

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

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId == null ? null : storageGroupId.trim();
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId == null ? null : standedProdId.trim();
    }

    public Long getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(Long routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName == null ? null : storageGroupName.trim();
    }

    public Long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(Long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public Long getHighSalePrice() {
        return highSalePrice;
    }

    public void setHighSalePrice(Long highSalePrice) {
        this.highSalePrice = highSalePrice;
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