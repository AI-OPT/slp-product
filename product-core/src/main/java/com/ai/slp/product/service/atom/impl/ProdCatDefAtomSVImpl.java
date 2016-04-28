package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;

public class ProdCatDefAtomSVImpl implements IProdCatDefAtomSV{

    @Override
    public ProductCat selectById(String tenantId, String productCatId) {
        return null;
    }

    @Override
    public int insertProductCat(ProductCat productCat) {
        return 0;
    }

    @Override
    public int updateProductCat(ProductCat productCat) {
        return 0;
    }

    @Override
    public int deleteProductCat(String tenantId, String productCatId) {
        return 0;
    }

}
