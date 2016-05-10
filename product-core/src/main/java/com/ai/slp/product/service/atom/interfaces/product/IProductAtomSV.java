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

    /**
     * 查询指定库存组下的销售商品
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public Product selectByGroupId(String tenantId,String groupId);

    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public Product selectByProductId(String tenantId,String prodId);

    /**
     * 根据标识更新商品信息
     *
     * @param product
     * @return
     */
    public int updateById(Product product);
}
