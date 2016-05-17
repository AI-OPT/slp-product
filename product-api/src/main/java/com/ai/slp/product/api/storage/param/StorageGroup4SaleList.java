package com.ai.slp.product.api.storage.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 虚拟库存组列表信息<br>
 * 用于显示商城商品销售价列表,包括非废弃的库存组个数
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroup4SaleList implements Serializable {
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
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 标准品名称
     */
    private String standedProductName;
    /**
     * 最低销售价
     */
    private Long lowSalePrice;
    /**
     * 生成日期
     */
    private Timestamp createTime;
    
    
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
	public Long getLowSalePrice() {
		return lowSalePrice;
	}
	public void setLowSalePrice(Long lowSalePrice) {
		this.lowSalePrice = lowSalePrice;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
