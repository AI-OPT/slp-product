package com.ai.slp.product.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;

public class ProdCatAttrAtomSVImpl implements IProdCatAttrAtomSV{

    @Autowired
    ProdCatAttrMapper prodCatAttrMapper;
    
    @Override
    public ProdCatAttr selectById(String tenantId, String productCatId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        List<ProdCatAttr> prodCatAttrList = prodCatAttrMapper.selectByExample(example);
        if(prodCatAttrList == null || prodCatAttrList.isEmpty())
            return null;
        return prodCatAttrList.get(0);
    }

    @Override
    public int insertProdCatAttr(ProdCatAttr prodCatAttr) {
        return prodCatAttrMapper.insertSelective(prodCatAttr);
    }

}
