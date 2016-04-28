package com.ai.slp.product.service.atom.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.dao.mapper.bo.SysSequenceCredit;
import com.ai.slp.product.dao.mapper.interfaces.SysSequenceCreditMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/28.
 */
@Component
public class SysSequenceCreditAtomSVImpl implements ISysSequenceCreditAtomSV {
    @Autowired
    SysSequenceCreditMapper mapper;

    private static final long DEF_6_LEN_VAL = 100000;
    private static final long DEF_12_LEN_VAL = 100000000000l;


    @Override
    public long getSeqByName() {
        return genSeqByName(SEQ_LEN_DEF_ID);
    }

    @Override
    public long get6SeqByName() {
        long seq = genSeqByName(SEQ_LEN_6_ID);
        return seq>DEF_6_LEN_VAL?seq:(DEF_6_LEN_VAL+seq);
    }

    @Override
    public long gen12SeqByName() {
        long seq = genSeqByName(SEQ_LEN_12_ID);
        return seq>DEF_12_LEN_VAL?seq:(DEF_12_LEN_VAL+seq);
    }

    private long genSeqByName(String name){
        SysSequenceCredit sequenceCredit = mapper.selectByPrimaryKey(name);
        if (sequenceCredit==null)
            throw new BusinessException("","");
        Long seq = sequenceCredit.getCurrentValue();
        if (seq==null)
            seq = 0l;
        sequenceCredit.setCurrentValue(++seq);
        mapper.updateByPrimaryKey(sequenceCredit);
        return seq;
    }
}
