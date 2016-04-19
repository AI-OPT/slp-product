package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品类目基础信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author lipeng
 */
public class ProductCatBase extends BaseInfo {

	/**
	 * 状态:STATE
	 */
	private String state;
	
	/**
	 * 操作人：OPER_ID
	 */
	private Long operId;
	
	/**
	 * 操作时间：OPER_TIME
	 */
	private Date operTime;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	
}
