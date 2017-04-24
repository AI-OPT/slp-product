package com.ai.slp.product.api.flushdata.params;

import java.io.Serializable;

/**
 * 批量制造商品入参 Date: 2017年4月20日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class CreateDataRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 类目下需要商品数量
	 */
	private Integer number;
	/**
	 * 类目起始
	 */
	private String productCatIdStartNum;
	/**
	 * 类目结束
	 */
	private String productCatIdEndNum;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品ID
	 */
	private String productIdStart;

	public String getProductIdStart() {
		return productIdStart;
	}

	public void setProductIdStart(String productIdStart) {
		this.productIdStart = productIdStart;
	}

	public String getProductCatIdStartNum() {
		return productCatIdStartNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductCatIdStartNum(String productCatIdStartNum) {
		this.productCatIdStartNum = productCatIdStartNum;
	}

	public String getProductCatIdEndNum() {
		return productCatIdEndNum;
	}

	public void setProductCatIdEndNum(String productCatIdEndNum) {
		this.productCatIdEndNum = productCatIdEndNum;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
