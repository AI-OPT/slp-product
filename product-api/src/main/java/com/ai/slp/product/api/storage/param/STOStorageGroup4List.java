package com.ai.slp.product.api.storage.param;

import java.util.Date;

/**
 * 虚拟库存组列表信息<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorageGroup4List extends StorageGroupStatus {

    /**
     * 标准品标识
     */
    private String prodId;
    /**
     * 标准品名称
     */
    private String prodName;
    /**
     * 库存组名称
     */
    private String groupName;
    /**
     * 序列号
     */
    private Long serialNumber;
    /**
     * 操作人名称
     */
    private String operName;
    /**
     * 组内库存量
     */
    private long storageTotal;
    /**
     * 组内库存数量
     */
    private int storageNum;
    /**
     * 生成日期
     */
    private Date createTime;
    /**
     * 最低销售价
     */
    private Long lowSalePrice;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(Long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }
}
