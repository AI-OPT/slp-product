package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.interfaces.ProdPriceLogMapper;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import org.springframework.stereotype.Component;

@Component
public class ProdPriceLogAtomImpl implements IProdPriceLogAtomSV {

    @Autowired
    ProdPriceLogMapper prodPriceLogMapper;
    
    @Override
    public int insert(ProdPriceLog prodPriceLog) {
        if(prodPriceLog == null)
            return 0;
        prodPriceLog.setLogId(SequenceUtil.genProdPriceLogId());
        return prodPriceLogMapper.insert(prodPriceLog);
    }

}
