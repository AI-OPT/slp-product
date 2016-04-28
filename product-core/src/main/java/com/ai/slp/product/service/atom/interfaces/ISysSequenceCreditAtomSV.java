package com.ai.slp.product.service.atom.interfaces;

/**
 * 序列号获取
 * Created by jackieliu on 16/4/28.
 */
public interface ISysSequenceCreditAtomSV {
    //6位长度序列号
    public static final String SEQ_LEN_6_ID = "LEN_6_SEQ_ID";
    //12位长度序列号
    public static final String SEQ_LEN_12_ID = "LEN_12_SEQ_ID";
    //递增长度序列号
    public static final String SEQ_LEN_DEF_ID = "LEN_DEF_SEQ_ID";

    /**
     * 获取指定序列号,长度从1开始
     * @return
     */
    public long getSeqByName();

    /**
     * 获取指定序列号,长度6位开始
     * @return
     */
    public long get6SeqByName();

    /**
     * 获取指定序列号,长度12位开始
     * @return
     */
    public long gen12SeqByName();
}
