package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

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
	
	/**
	 * 查询商品   拒绝原因   拒绝描述
	 */
	public ProductStateLog selectRefuseById(String prodId);
	
	/**
	 * 查询商品   拒绝原因   拒绝描述 --- 按操作时间倒序
	 */
	public ProductStateLog selectStateLogByProdId(String prodId);
}
