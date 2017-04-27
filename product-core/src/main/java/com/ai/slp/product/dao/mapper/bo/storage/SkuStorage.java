package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;

public class SkuStorage {
    private String skuStorageId;

    private String skuId;

    private String storageId;

    private Long salePrice;

    private Long totalNum;

    private Long usableNum;

    private String state;

    private Long operId;

    private Timestamp operTime;

    private String storageGroupId;

    private Short priorityNumber;

    public String getSkuStorageId() {
        return skuStorageId;
    }

    public void setSkuStorageId(String skuStorageId) {
        this.skuStorageId = skuStorageId == null ? null : skuStorageId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId == null ? null : storageId.trim();
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

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId == null ? null : storageGroupId.trim();
    }

    public Short getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(Short priorityNumber) {
        this.priorityNumber = priorityNumber;
    }
}