package com.ai.slp.product.api.flushdata.params;

import java.io.Serializable;

public class FlushDataRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pageNo;

	private Integer pageSize;

	public Integer getPageNo() {
		return pageNo;
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
