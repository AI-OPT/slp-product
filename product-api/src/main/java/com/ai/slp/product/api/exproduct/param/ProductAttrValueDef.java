package com.ai.slp.product.api.exproduct.param;

import java.io.Serializable;

public class ProductAttrValueDef implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性值ID
	 */
	private String attrValueId;

	/**
	 * 属性值名称
	 */
	private String attrValueName;

	public String getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(String attrValueId) {
		this.attrValueId = attrValueId;
	}

	public String getAttrValueName() {
		return attrValueName;
	}

	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}

}
