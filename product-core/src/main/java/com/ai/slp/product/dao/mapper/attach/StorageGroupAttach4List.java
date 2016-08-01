package com.ai.slp.product.dao.mapper.attach;

import java.sql.Timestamp;

/**
 * Created by jackieliu on 16/8/1.
 */
public class StorageGroupAttach4List {
    private String tenantId;
    /**
     * 库存组标识
     */
    private String storageGroupId;
    /**
     * 库存组名称
     */
    private String storageGroupName;
    /**
     * 状态 1:启用;2:停用;3:废弃
     */
    private String state;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 标准品名称
     */
    private String standedProductName;
    /**
     * 序列号
     */
    private Long serialNumber;
    /**
     * 最低销售价
     */
    private Long lowSalePrice;
    /**
     * 最高销售价
     */
    private Long highSalePrice;
    /**
     * 创建者ID
     */
    private Long createId;
    /**
     * 生成日期
     */
    private Timestamp createTime;
    /**
     * 操作者ID
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStandedProductName() {
        return standedProductName;
    }

    public void setStandedProductName(String standedProductName) {
        this.standedProductName = standedProductName;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
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
}
