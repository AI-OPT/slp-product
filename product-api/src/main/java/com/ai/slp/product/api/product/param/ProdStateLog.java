package com.ai.slp.product.api.product.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 商品状态日志<br>
 *
 * Date: 2016年9月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author jiawen
 */
public class ProdStateLog extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	//日志标识
	private String logId;
	//商品标识
    private String prodId;
    //优先级
    private Short priorityNumber;
    //优先理由
    private String priorityReason;
    //拒绝原因
    private String refuseReason;
    //拒绝理由
    private String refuseDes;
    //状态值
    private String state;
    //操作人标识
    private Long operId;
    //操作是时间
    private Timestamp operTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public Short getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(Short priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getPriorityReason() {
		return priorityReason;
	}

	public void setPriorityReason(String priorityReason) {
		this.priorityReason = priorityReason;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getRefuseDes() {
		return refuseDes;
	}

	public void setRefuseDes(String refuseDes) {
		this.refuseDes = refuseDes;
	}

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

	public Timestamp getOperTime() {
		return operTime;
	}

	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}
    
    
}
