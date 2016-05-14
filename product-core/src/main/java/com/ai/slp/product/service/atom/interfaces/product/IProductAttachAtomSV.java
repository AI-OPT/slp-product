package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.vo.ProductPageQueryVo;

public interface IProductAttachAtomSV {
	/**
	 * 根据条件搜索商品相关信息
	 * 
	 * @return
	 * @author lipeng16
	 */
	public PageInfo<ProductAttach> queryProductPageBySearch(ProductPageQueryVo productPageQueryVo);
}
