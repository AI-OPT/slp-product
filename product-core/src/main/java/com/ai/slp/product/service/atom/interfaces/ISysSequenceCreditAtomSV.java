package com.ai.slp.product.service.atom.interfaces;

/**
 * 序列号获取
 * Created by jackieliu on 16/4/28.
 */
public interface ISysSequenceCreditAtomSV {
    //商品属性序列标识
    public static final String PROD_ATTR_ID = "SLP_PROD_ATTR_SEQ";
    /**
     * 获取指定序列号
     * @param name
     * @return
     */
    public long getSeqByName(String name);
}
