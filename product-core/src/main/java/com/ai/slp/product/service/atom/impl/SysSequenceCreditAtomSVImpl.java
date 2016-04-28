package com.ai.slp.product.service.atom.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.dao.mapper.bo.SysSequenceCredit;
import com.ai.slp.product.dao.mapper.bo.SysSequenceCreditCriteria;
import com.ai.slp.product.dao.mapper.interfaces.SysSequenceCreditMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/28.
 */
@Component
public class SysSequenceCreditAtomSVImpl implements ISysSequenceCreditAtomSV {
    @Autowired
    SysSequenceCreditMapper mapper;

    private static final long DEF_LENGTH_VAL = 100000;

    @Override
    public long getSeqByName(String name) {
        SysSequenceCredit sequenceCredit = mapper.selectByPrimaryKey(name);
        if (sequenceCredit==null)
            throw new BusinessException("","");
        Long seq = sequenceCredit.getCurrentValue();
        if (seq==null)
            seq = 0l;
        sequenceCredit.setCurrentValue(++seq);
        mapper.updateByPrimaryKey(sequenceCredit);

        return seq>DEF_LENGTH_VAL?seq:(DEF_LENGTH_VAL+seq);
    }
}
