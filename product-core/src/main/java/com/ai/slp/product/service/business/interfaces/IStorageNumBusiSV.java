package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.storageserver.param.StorageNumRes;

import java.util.Map;

/**
 * SKU库存组操作
 * Created by jackieliu on 16/5/25.
 */
public interface IStorageNumBusiSV {
    /**
     * 使用库存量
     * @param tenantId 租户ID
     * @param skuId 单品标识ID
     * @param skuNum 单品数量
     * @return
     */
    public StorageNumRes userStorageNum(String tenantId,String skuId,int skuNum);

    /**
     * 回退库存量
     * @param tenantId 租户id
     * @param skuId 单品标识
     * @param storageNum 库存回退集合
     */
    public void backStorageNum(String tenantId,String skuId,Map<String,Integer> storageNum);
}
