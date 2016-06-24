package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttrLog;

/**
 * 销售品属性日志原子操作
 * Created by jackieliu on 16/6/24.
 */
public interface IProdAttrLogAtomSV {
    /**
     * 添加销售属性日志
     * @param attrLog
     * @return
     */
    public int installLog(ProdAttrLog attrLog);
}
