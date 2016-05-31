package com.ai.slp.product.service.business.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品销量处理
 * Created by jackieliu on 16/5/31.
 */
public interface IProdSaleAllBusiSV {
    /**
     * 增加商品销量
     * @param tenantId
     * @param skuId
     * @param saleNum
     */
    public void addSaleNum(String tenantId, String skuId, int saleNum);
}
