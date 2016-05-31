package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;
import java.util.List;

public class ProductSKUAttr implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 属性标识
	 */
	private Long attrId;
	/**
	 * 属性名称
	 */
	private String attrName;
	/**
	 * 属性值集合
	 */
	private List<ProductSKUAttrValue> attrValueList;

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
	public List<ProductSKUAttrValue> getAttrValueList() {
		return attrValueList;
	}
	public void setAttrValueList(List<ProductSKUAttrValue> attrValueList) {
		this.attrValueList = attrValueList;
	}
}
