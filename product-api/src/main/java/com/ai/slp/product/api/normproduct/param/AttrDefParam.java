package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 属性定义请求参数<br>
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrDefParam extends BaseInfo {
	
	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize =20;

	/**
	 * 属性ID
	 */
	private long attrId;
	
	/**
	 * 属性名称
	 */
	private String attrName;

	/**
	 * 值输入方式
	 */
	private String valueWay;

    /**
     * 状态
     * 1有效0无效
     */
    private String state;
    
    /**
     * 操作人ID
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

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getValueWay() {
		return valueWay;
	}

	public void setValueWay(String valueWay) {
		this.valueWay = valueWay;
	}
	
	
}
