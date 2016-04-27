package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductLogMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductLogAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/27.
 */
@Component
public class StandedProductLogAtomSVImpl implements IStandedProductLogAtomSV {
    @Autowired
    transient StandedProductLogMapper productLogMapper;

    @Override
    public int insert(StandedProductLog productLog) {
        return productLogMapper.insert(productLog);
    }
}
