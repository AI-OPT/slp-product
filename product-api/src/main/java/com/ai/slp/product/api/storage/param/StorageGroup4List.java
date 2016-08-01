package com.ai.slp.product.api.storage.param;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 虚拟库存组列表信息<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroup4List implements Serializable {
    private static final long serialVersionUID = 1L;
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
     *虚拟库存量
    */
    private Long storageTotal;
    /**
     * 组内库存数量
     */
    private Integer storageNum;
    /**
     * 生成日期
     */
    private Timestamp createTime;
    /**
     * 最低销售价
     */
    private Long lowSalePrice;
    /**
     * 操作者ID
     */
    private Long operId;
    /**
     * 操作人
     */
    private String operName;
    /**
     * 操作时间
     */
    private Timestamp operTime;

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

    public Long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public Integer getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(Integer storageNum) {
        this.storageNum = storageNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(Long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}
