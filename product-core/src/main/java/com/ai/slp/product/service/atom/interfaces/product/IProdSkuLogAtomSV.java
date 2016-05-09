package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;

/**
 * 商品SKU日志的原子操作
 *
 * Created by jackieliu on 16/5/6.
 */
public interface IProdSkuLogAtomSV {
    /**
     * 插入SKU日志
     * @param skuLog
     * @return
     */
    public int install(ProdSkuLog skuLog);
}
