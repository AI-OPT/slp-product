package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class Storage {
    private String storageId;

    private String prodId;

    private String storageGroupId;

    private String storageName;

    private String isSaleAttr;

    private Long salePrice;

    private Long totalNum;

    private Long usableNum;

    private Long warnNum;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private Short priorityNumber;

    private Short serialNumber;

    private String state;

    private Long operId;

    private Timestamp operTime;

    private Long createId;

    private Timestamp createTime;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId == null ? null : storageId.trim();
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

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName == null ? null : storageName.trim();
    }

    public String getIsSaleAttr() {
        return isSaleAttr;
    }

    public void setIsSaleAttr(String isSaleAttr) {
        this.isSaleAttr = isSaleAttr == null ? null : isSaleAttr.trim();
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(Long usableNum) {
        this.usableNum = usableNum;
    }

    public Long getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(Long warnNum) {
        this.warnNum = warnNum;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public Short getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(Short priorityNumber) {
        this.priorityNumber = priorityNumber;
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
}