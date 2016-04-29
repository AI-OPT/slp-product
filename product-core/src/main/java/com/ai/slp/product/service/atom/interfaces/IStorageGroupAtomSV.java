package com.ai.slp.product.service.atom.interfaces;

/**
 * 库存组原子操作
 *
 * Created by jackieliu on 16/4/29.
 */
public interface IStorageGroupAtomSV {
    /**
     * 查询标准品下已经不是废弃状态的库存组
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public int queryCountNoDiscard(String tenantId,String standedProdId);

    /**
     * 查询标准品下启用状态的库存组
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public int queryCountActive(String tenantId,String standedProdId);
}
