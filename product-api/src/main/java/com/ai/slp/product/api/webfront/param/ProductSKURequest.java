package com.ai.slp.product.api.webfront.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;

public class ProductSKURequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * SKU 单品标识
	 */
	@NotBlank(message = "SKU单品标识不能为空",groups = {IProductDetailSV.QueryProducSKUById.class})
	private String skuId;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
}
