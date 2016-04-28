package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProductAtomSVImpl implements IStandedProductAtomSV {
    @Autowired
    StandedProductMapper productMapper;

    @Override
    public int installObj(StandedProduct standedProduct) {
        return 0;
    }

    @Override
    public int updateObj(StandedProduct standedProduct) {
        return 0;
    }

    @Override
    public int deleteObj(String tenantId, String standedProdId) {
        return 0;
    }

    @Override
    public StandedProduct selectById(String tenantId, String standedProdId) {
        return null;
    }
}
