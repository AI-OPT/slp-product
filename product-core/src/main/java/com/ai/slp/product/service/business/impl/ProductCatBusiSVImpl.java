package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.PageInfoWrapper;
import com.ai.slp.product.api.normproduct.param.ProductCatInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jackieliu on 16/4/29.
 */
@Service
@Transactional
public class ProductCatBusiSVImpl implements IProductCatBusiSV {
    @Autowired
    IProdCatDefAtomSV prodCatDefAtomSV;
    @Override
    public PageInfoWrapper<ProductCatInfo> queryProductCat(ProductCatParam productCatParam) {
//        PageInfo<> productCatPage =  prodCatDefAtomSV.queryForPage(
//                productCatParam.(),);
        return null;
    }
}
