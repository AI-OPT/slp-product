package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.api.storage.param.StoNoSkuSalePrice;

public class StoNoSkuSalePriceComparator implements Comparator<StoNoSkuSalePrice> {

	/**
	 * 库存组标识对比
	 */
	@Override
	public int compare(StoNoSkuSalePrice o1, StoNoSkuSalePrice o2) {
		if (null == o1 || null == o2) 
			return 0;
		
		return o1.getGroupId().compareTo(o2.getGroupId());
	}

}
