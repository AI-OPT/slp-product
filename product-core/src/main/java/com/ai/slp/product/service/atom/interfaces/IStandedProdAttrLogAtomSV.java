package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;

/**
 * 标准品属性值操作
 *
 * Created by liutong5 on 16/4/27.
 */
public interface IStandedProdAttrLogAtomSV {
    /**
     * 添加标准品属性日志
     * @param prodAttrLog
     * @return
     */
    public int installObj(StandedProdAttrLog prodAttrLog);
}
