package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;


public class ProductSKUAttrValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 商品属性值标识
	 */
	private Long attrvalueDefId;
	/**
	 * 属性值编码
	 */
	private String attrValueId;
	/**
	 * 属性值名称
	 */
	private String attrValueName;
	/**
     * 图片
     */
    private ProductImage image;
    /**
     * 图片url
     */
    private String imageUrl;
    
	public Long getAttrvalueDefId() {
		return attrvalueDefId;
	}
	public void setAttrvalueDefId(Long attrvalueDefId) {
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
}
