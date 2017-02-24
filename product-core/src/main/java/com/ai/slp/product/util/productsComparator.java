package com.ai.slp.product.util;

import java.util.Comparator;

import com.ai.slp.product.dao.mapper.bo.product.Product;

public class productsComparator implements Comparator<Product> {

	/**
	 * 商品创建时间对比
	 */
	@Override
	public int compare(Product o1, Product o2) {
		if (null == o1 || null == o2) 
			return 0;
		return o2.getCreateTime().compareTo(o1.getCreateTime());
	}

}
