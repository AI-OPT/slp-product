package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;
import com.ai.slp.product.dao.mapper.interfaces.StandedProdAttrLogMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrLogAtomSV;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 标准品属性日志操作
 * Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProdAttrLogAtomSVImpl implements IStandedProdAttrLogAtomSV {
    @Autowired
    StandedProdAttrLogMapper prodAttrLogMapper;

    @Override
    public int installObj(StandedProdAttrLog prodAttrLog) {
        if (prodAttrLog==null){
        	return 0;
        }
        prodAttrLog.setLogId(SequenceUtil.genStandedProdAttrLogId());
        return prodAttrLogMapper.insert(prodAttrLog);
    }
}
