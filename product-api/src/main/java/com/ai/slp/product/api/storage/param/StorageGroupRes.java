package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseResponse;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 单个虚拟库存组信息<br>
 * 包括下属库存集合
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupRes extends BaseResponse {
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
     * 序列号
     */
    private Short serialNumber;
    /**
     * 商城商品标识
     */
    private String prodId;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 是否有销售属性
     */
    private String isSaleAttr;
    /**
     * 总库存量
     */
    private long storageTotal;
    /**
     * 库存组下库存的个数
     */
    private int storageNum;
    /**
     * 操作人名称
     */
    private String operName;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 最低销售价
     */
    private Long lowSalePrice;
    /**
     * 最高销售价
     */
    private Long highSalePrice;
    /**
     * 库存组状态
     */
    private String state;
    /**
     * 状态名称
     */
    private String stateName;
    /**
     * 创建者ID
     */
    private Long createId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * 优先级顺序库存集合
     */
    private Map<Short,List<StorageRes>> storageList;
    

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

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Map<Short, List<StorageRes>> getStorageList() {
        return storageList;
    }

    public void setStorageList(Map<Short, List<StorageRes>> storageList) {
        this.storageList = storageList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getIsSaleAttr() {
        return isSaleAttr;
    }

    public void setIsSaleAttr(String isSaleAttr) {
        this.isSaleAttr = isSaleAttr;
    }

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
    }
}
