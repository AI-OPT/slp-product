package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.dao.mapper.bo.storage.Storage;

/**
 * 库存业务操作
 * Created by jackieliu on 16/5/4.
 */

public interface IStorageBusiSV {

    /**
     * 废弃库存组
     * @param storage
     */
    public void discardStorage(Storage storage);

    /**
     * 自动停用库存
     * @param storage
     */
    public void autoStopStorage(Storage storage);

    /**
     * 更改库存状态
     * @param tenantId
     * @param storageId
     * @param state
     * @param operId
     */
    public void changeStorageStats(String tenantId,String storageId,String state,Long operId);
}
