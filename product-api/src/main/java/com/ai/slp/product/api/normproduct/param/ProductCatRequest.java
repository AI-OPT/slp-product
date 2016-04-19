package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品类目搜索请求参数<br>
 * 
 * Date: 2016年4月19日<br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author lipeng
 */
public class ProductCatRequest extends ProductCatBase {
	
	/**
	 * 商品类目标识： PRODUCT_CAT_ID
	 */
	private Long productCatId;

	/**
	 * 名称:PRODUCT_CAT_NAME
	 */
	private String productCatName;
	
	/**
	 * 父类目:PARENT_PRODUCT_CAT_ID
	 */
	private Long parentProductCatId;
	
	/**
	 * 是否叶子节点:IS_LEAF
	 */
	private String isLeaf;
	
	public String getProductCatName() {
		return productCatName;
	}

	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}

	public Long getParentProductCatId() {
		return parentProductCatId;
	}

	public void setParentProductCatId(Long parentProductCatId) {
		this.parentProductCatId = parentProductCatId;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(Long productCatId) {
		this.productCatId = productCatId;
	}

}
