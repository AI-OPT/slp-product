package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class StorageGroup {
    private String storageGroupId;

    private String tenantId;

    private String standedProdId;

    private String routeGroupId;

    private String storageGroupName;

    private String isSaleAttr;

    private Long lowSalePrice;

    private Long highSalePrice;

    private Short serialNumber;

    private String state;

    private Long createId;

    private Timestamp createTime;

    private Long operId;

    private Timestamp operTime;

    private String supplierId;

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId == null ? null : storageGroupId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId == null ? null : standedProdId.trim();
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId == null ? null : routeGroupId.trim();
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName == null ? null : storageGroupName.trim();
    }

    public String getIsSaleAttr() {
        return isSaleAttr;
    }

    public void setIsSaleAttr(String isSaleAttr) {
        this.isSaleAttr = isSaleAttr == null ? null : isSaleAttr.trim();
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

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }
}