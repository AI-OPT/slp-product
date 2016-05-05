package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.Product;

/**
 * 销售商品原子操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductAtomSV {

    /**
     * 添加销售商品信息
     *
     * @param product
     * @return
     */
    public int installProduct(Product product);

}
