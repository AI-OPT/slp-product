package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 属性分页查询(搜索)请求参数<br>
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrDefParam extends BaseInfo {
	
	private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     * 默认为20
     */
    private Integer pageSize =20;

	/**
	 * 属性ID
	 */
	private Long attrId;
	
	/**
	 * 属性名称
	 */
	private String attrName;

	/**
	 * 属性值输入方式
	 */
	private String valueWay;

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
