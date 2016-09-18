package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseInfo;

public class ProductSKURequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * SKU单品标识,与属性串不能都为空<br>
	 * 当单品标识不为空时,则不处理属性串
	 */
	private String skuId;

	/**
	 * SKU属性串,与单品标识不能都为空<br>
	 * 当单品标识不为空时,则不处理属性串<br>
	 * 格式:属性ID:属性值ID;属性ID:属性值ID
	 */
	private String skuAttrs;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuAttrs() {
		return skuAttrs;
	}

	public void setSkuAttrs(String skuAttrs) {
		this.skuAttrs = skuAttrs;
	}
}
