package com.ai.slp.product.search.bo;

import java.util.List;

import com.google.gson.annotations.Expose;

public class ProductAttr {

	/**
	 * 属性标识
	 */
	@Expose
	private Long attrid;
	/**
	 * 属性名称
	 */
	@Expose
	private String attrname;
	/**
	 * 属性类型
	 */
	@Expose
	private String attrtype;
	/**
	 * 属性值集合
	 */
	@Expose
	private List<ProductAttrValue> attrvaluelist;

	public Long getAttrid() {
		return attrid;
	}

	public void setAttrid(Long attrid) {
		this.attrid = attrid;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrtype() {
		return attrtype;
	}

	public void setAttrtype(String attrtype) {
		this.attrtype = attrtype;
	}

	public List<ProductAttrValue> getAttrvaluelist() {
		return attrvaluelist;
	}

	public void setAttrvaluelist(List<ProductAttrValue> attrvaluelist) {
		this.attrvaluelist = attrvaluelist;
	}

}
