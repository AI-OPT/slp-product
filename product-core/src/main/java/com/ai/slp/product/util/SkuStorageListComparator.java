package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;

public class SkuStorageListComparator implements Comparator<SkuStorage> {

	/**
	 * sku库存标识对比
	 */
	@Override
	public int compare(SkuStorage o1, SkuStorage o2) {
		if (null == o1 || null == o2) 
			return 0;
		
		return o1.getSkuStorageId().compareTo(o2.getSkuStorageId());
	}

}
