package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProductLog;

/**
 * 标准品日志操作
 *
 * Created by liutong5 on 16/4/27.
 */
public interface IStandedProductLogAtomSV {
    /**
     * 添加标准品日志
     * @param productLog
     */
    public int insert(StandedProductLog productLog);

}
