package com.ai.slp.product.service.business.impl;

import com.ai.slp.product.api.normproduct.param.NormProductSaveRequest;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Transactional
public class NormProductBusiSVImpl implements INormProductBusiSV {
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;

    @Override
    public void installNormProd(NormProductSaveRequest normProdct) {

    }
}
