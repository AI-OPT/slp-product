package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;


public class ProdAttrValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 商品属性值标识<br>
	 * 值输入方式为1下拉单选、2多选时存在
	 */
	private String attrvalueDefId;
	/**
	 * 属性值名称
	 */
	private String attrValueName;

	/**
	 * 属性值名称2
	 */
	private String attrValueName2;
	/**
	 * 是否自有属性
	 */
	private boolean isOwn;
	/**
     * 图片
     */
    private ProductImage image;
    /**
     * 图片url,由调用方组装
     */
    private String imageUrl;

	public String getAttrvalueDefId() {
		return attrvalueDefId;
	}
	public void setAttrvalueDefId(String attrvalueDefId) {
		this.attrvalueDefId = attrvalueDefId;
	}
	public String getAttrValueName() {
		return attrValueName;
	}
	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}
	public ProductImage getImage() {
		return image;
	}
	public void setImage(ProductImage image) {
		this.image = image;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(boolean isOwn) {
		this.isOwn = isOwn;
	}

	public String getAttrValueName2() {
		return attrValueName2;
	}

	public void setAttrValueName2(String attrValueName2) {
		this.attrValueName2 = attrValueName2;
	}
}
