package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;


public class ProductSKUAttrValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 商品属性值标识
	 */
	private String attrvalueDefId;
	/**
	 * 属性值编码
	 */
	private String attrValueId;
	/**
	 * 属性值名称
	 */
	private String attrValueName;
	/**
     * 商品图片标识
     */
    private String vfsId;
	public String getAttrvalueDefId() {
		return attrvalueDefId;
	}
	public void setAttrvalueDefId(String attrvalueDefId) {
		this.attrvalueDefId = attrvalueDefId;
	}
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
	public String getVfsId() {
		return vfsId;
	}
	public void setVfsId(String vfsId) {
		this.vfsId = vfsId;
	}
}
