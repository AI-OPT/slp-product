package com.ai.slp.product.service.atom.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.interfaces.ProdPriceLogMapper;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;

public class ProdPriceLogAtomImpl implements IProdPriceLogAtomSV {

    @Autowired
    ProdPriceLogMapper prodPriceLogMapper;
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;
    
    @Override
    public int insert(ProdPriceLog prodPriceLog) {
        if(prodPriceLog == null)
            return 0;
        prodPriceLog.setLogId(sequenceCreditAtomSV.getSeqByName()+"");
        return prodPriceLogMapper.insert(prodPriceLog);
    }

}
