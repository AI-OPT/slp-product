package com.ai.slp.product.service.atom.impl;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.vo.StandedProdPageQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProductCatMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;

@Component
public class ProdCatDefAtomSVImpl implements IProdCatDefAtomSV{

    @Autowired
    ProductCatMapper productCatMapper;

    @Override
    public PageInfo<StandedProduct> queryForPage(StandedProdPageQueryVo request) {
        return null;
    }

    @Override
    public ProductCat selectById(String tenantId, String productCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        List<ProductCat> productCatList = productCatMapper.selectByExample(example);
        if(productCatList == null || productCatList.isEmpty())
            return null;
        return productCatList.get(0);
    }

    @Override
    public int insertProductCat(ProductCat productCat) {
        return productCatMapper.insertSelective(productCat);
    }

    @Override
    public int updateProductCat(ProductCat productCat) {
        return productCatMapper.updateByPrimaryKeySelective(productCat);
    }

    @Override
    public int deleteProductCat(String tenantId, String productCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        return productCatMapper.deleteByExample(example);
    }

}
