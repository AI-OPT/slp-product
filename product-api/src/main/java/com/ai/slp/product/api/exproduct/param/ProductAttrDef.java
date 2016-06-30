package com.ai.slp.product.api.exproduct.param;

import java.io.Serializable;

public class ProductAttrDef implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 属性编码
	 */
	private String attrId;
	/**
	 * 属性名称
	 */
	private String attrName;

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

}
