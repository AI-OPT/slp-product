package com.ai.slp.product.service.atom.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.dao.mapper.attach.ProductAttachMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAttachAtomSV;
import com.ai.slp.product.vo.ProductPageQueryVo;
import org.springframework.stereotype.Component;

@Component
public class ProductAttachAtomSVImpl implements IProductAttachAtomSV {
	@Autowired
	ProductAttachMapper productAttachMapper;

	@Override
	public List<ProductAttach> queryProductPageBySearch(ProductPageQueryVo productPageQueryVo) {
		return productAttachMapper.getProductPage(productPageQueryVo.getPageNo(), productPageQueryVo.getPageSize(),
					productPageQueryVo.getProductCatId(), productPageQueryVo.getProdId(), productPageQueryVo.getProdName(),
					productPageQueryVo.getProductType(), productPageQueryVo.getStorageGroupId(),
					productPageQueryVo.getStorageGroupName(), productPageQueryVo.getStandedProdId(),
					productPageQueryVo.getStandedProdName(), productPageQueryVo.getTenantId());

	}

}
