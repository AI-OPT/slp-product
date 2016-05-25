package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;
import java.util.List;

public class ProductSKUImage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 商品属性值标识
	 */
	private Long attrvalueDefId;
	
	/**
	 * 商品图片List
	 */
	private List<ProductImage> imageList;

	public Long getAttrvalueDefId() {
		return attrvalueDefId;
	}

	public void setAttrvalueDefId(Long attrvalueDefId) {
		this.attrvalueDefId = attrvalueDefId;
	}

	public List<ProductImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<ProductImage> imageList) {
		this.imageList = imageList;
	}
}
