package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductLog;

/**
 * Created by jackieliu on 16/5/5.
 */
public interface IProductLogAtomSV {
    /**
     * 添加商品日志
     * @param productLog
     * @return
     */
    public int install(ProductLog productLog);
}
