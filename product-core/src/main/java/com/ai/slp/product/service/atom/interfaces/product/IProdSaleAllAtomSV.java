package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;

/**
 * 商品销量处理原子类
 * Created by jackieliu on 16/5/31.
 */
public interface IProdSaleAllAtomSV {

    /**
     * 插入SKU销量信息
     * @param prodSaleAll
     * @return
     */
    public int installSaleAll(ProdSaleAll prodSaleAll);
    /**
     * 查询某个SKU的销量
     * @param tenantId
     * @param skuId
     * @return
     */
    public ProdSaleAll querySaleAllOfSku(String tenantId, String skuId);

    /**
     * 更新商品销售信息
     * @param prodSaleAll
     * @return
     */
    public int updateById(ProdSaleAll prodSaleAll);

    /**
     * 查询指定销售商品的销量
     * @param tenantId
     * @param productId
     * @return
     */
    public long queryNumOfProduc(String tenantId,String productId);
}
