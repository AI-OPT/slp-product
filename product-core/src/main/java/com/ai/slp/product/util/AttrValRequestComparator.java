package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.api.normproduct.param.AttrValRequest;

public class AttrValRequestComparator implements Comparator<AttrValRequest> {
	/**
	 * 属性值对比
	 */
	@Override
	public int compare(AttrValRequest o1, AttrValRequest o2) {
		if (null == o1 || null == o2) 
			return 0;
			
		return o1.getAttrValId().compareTo(o2.getAttrValId());
	}

}
