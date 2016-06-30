package com.ai.slp.product.api.exproduct.param;

import java.io.Serializable;

public class SaleArea implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 省份编码
	 */
	private String provCode;

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

}
