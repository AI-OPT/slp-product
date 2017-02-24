package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;

public class OldAttrValListComparator implements Comparator<StandedProdAttr> {

	/**
	 * 属性标识对比
	 */
	@Override
	public int compare(StandedProdAttr o1, StandedProdAttr o2) {
		if (null == o1 || null == o2) 
			return 0;
		
		return o1.getStandedProdAttrId().compareTo(o2.getStandedProdAttrId());
	}

}
