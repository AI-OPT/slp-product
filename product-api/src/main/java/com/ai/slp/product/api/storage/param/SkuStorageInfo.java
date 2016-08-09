package com.ai.slp.product.api.storage.param;

import java.io.Serializable;

/**
 *	sku库存和单品信息返回类
 *
 * Date: 2016年5月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class SkuStorageInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * sku库存标识
	 */
	private String skuStorageId;
	/**
	 * 单品sku标识
	 */
	private String skuId;
	/**
	 * sku库存量
	 */
	private long totalNum;
	
	/**
	 * sku属性串
	 */
	private String saleAttrs;

	public String getSkuStorageId() {
		return skuStorageId;
	}

	public void setSkuStorageId(String skuStorageId) {
		this.skuStorageId = skuStorageId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public String getSaleAttrs() {
		return saleAttrs;
	}

	public void setSaleAttrs(String saleAttrs) {
		this.saleAttrs = saleAttrs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
