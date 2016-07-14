package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductStateLog;

/**
 * 添加商品状态类日志
 * @author lipeng16
 *
 */
public interface IProductStateLogAtomSV {
	/**
	 * 添加商品状态变更日志
	 * @param productStateLog
	 * @return
	 */
	public int insert(ProductStateLog productStateLog);
}
