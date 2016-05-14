package com.ai.slp.product.service.atom.impl.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.dao.mapper.attach.ProductAttachMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAttachAtomSV;
import com.ai.slp.product.vo.ProductPageQueryVo;

public class ProductAttachAtomSVImpl implements IProductAttachAtomSV {
	@Autowired
	ProductAttachMapper productAttachMapper;

	@Override
	public PageInfo<ProductAttach> queryProductPageBySearch(ProductPageQueryVo productPageQueryVo) {
		return productAttachMapper.getProductPage(productPageQueryVo.getPageNo(), productPageQueryVo.getPageSize(),
					productPageQueryVo.getProductCatId(), productPageQueryVo.getProdId(), productPageQueryVo.getProdName(),
					productPageQueryVo.getProductType(), productPageQueryVo.getStorageGroupId(),
					productPageQueryVo.getStorageGroupName(), productPageQueryVo.getStandedProdId(),
					productPageQueryVo.getStandedProdName(), productPageQueryVo.getTenantId());

	}

}
