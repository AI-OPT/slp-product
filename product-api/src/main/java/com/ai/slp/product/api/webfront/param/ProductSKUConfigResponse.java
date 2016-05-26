package com.ai.slp.product.api.webfront.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ProductSKUConfigResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品配置参数
	 */
	private List<ProductSKUConfigParamter> configParamterList;

	public List<ProductSKUConfigParamter> getConfigParamterList() {
		return configParamterList;
	}

	public void setConfigParamterList(List<ProductSKUConfigParamter> configParamterList) {
		this.configParamterList = configParamterList;
	}

}
