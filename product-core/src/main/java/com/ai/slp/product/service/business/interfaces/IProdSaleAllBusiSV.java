package com.ai.slp.product.service.business.interfaces;

/**
 * 商品销量处理
 * Created by jackieliu on 16/5/31.
 */
public interface IProdSaleAllBusiSV {
    /**
     * 变更商品销量
     * @param tenantId
     * @param skuId
     * @param saleNum 正数:增加销量;负数:减少销量
     */
    public void updateSaleNum(String tenantId, String skuId, int saleNum);
}
