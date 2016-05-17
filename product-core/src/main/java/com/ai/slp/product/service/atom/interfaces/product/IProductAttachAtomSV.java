package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.vo.ProductPageQueryVo;

/**
 *多表查询商城商品信息
 *
 * Date: 2016年5月16日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IProductAttachAtomSV {
	/**
	 * 根据条件搜索商品相关信息
	 * 
	 * @return
	 * @author lipeng16
	 */
	public List<ProductAttach> queryProductPageBySearch(ProductPageQueryVo productPageQueryVo);
}
