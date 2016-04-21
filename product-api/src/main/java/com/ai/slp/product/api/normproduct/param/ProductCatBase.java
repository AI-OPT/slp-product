package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品类目基础信息<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCatBase extends BaseInfo {

	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 操作人
	 */
	private long operId;
	
	/**
	 * 操作时间
	 */
	private Date operTime;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getOperId() {
		return operId;
	}

	public void setOperId(long operId) {
		this.operId = operId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	
}
