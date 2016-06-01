package com.ai.slp.product.api.webfront.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ProductSKUConfigResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;

	/**
	 * 商品属性集合
	 */
	private List<ProductSKUAttr> productAttrList;

	public List<ProductSKUAttr> getProductAttrList() {
		return productAttrList;
	}

	public void setProductAttrList(List<ProductSKUAttr> productAttrList) {
		this.productAttrList = productAttrList;
	}
}
