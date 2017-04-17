package com.ai.slp.product.api.flushdata.params;

import java.io.Serializable;

public class FlushDataRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pageNo;

	private Integer pageSize;

	private String prodName;

	public Integer getPageNo() {
		return pageNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
