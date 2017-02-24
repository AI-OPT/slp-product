package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;

public class StorageGroupComparator implements Comparator<StorageGroup> {

	/**
	 * 库存组标识对比
	 */
	@Override
	public int compare(StorageGroup o1, StorageGroup o2) {
		if (null == o1 || null == o2) 
			return 0;		
		return o1.getStorageGroupId().compareTo(o2.getStorageGroupId());
	}

}
