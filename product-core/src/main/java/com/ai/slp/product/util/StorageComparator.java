package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.dao.mapper.bo.storage.Storage;

public class StorageComparator implements Comparator<Storage> {
	/**
	 * 库存标识对比
	 */
	@Override
	public int compare(Storage o1, Storage o2) {
		if (null == o1 || null == o2)
			return 0;
		return o1.getStorageId().compareTo(o2.getStorageId());
	}

}
