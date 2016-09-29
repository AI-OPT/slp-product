package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductLogMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductLogAtomSV;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 标准品日志
 * Created by jackieliu on 16/4/27.
 */
@Component
public class StandedProductLogAtomSVImpl implements IStandedProductLogAtomSV {
    @Autowired
    StandedProductLogMapper productLogMapper;

    @Override
    public int insert(StandedProductLog productLog) {
        if (productLog==null){
        	return 0;
        }
        //日志id
        productLog.setLogId(SequenceUtil.genStandedProductLogId());
        return productLogMapper.insert(productLog);
    }
}
