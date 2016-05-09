package com.ai.slp.product.service.atom.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;

/**
 * 库存日志
 * Created by jackieliu on 16/5/9.
 */
public interface IStorageLogAtomSV {
    /**
     * 添加库存日志
     * @param storageLog
     * @return
     */
    public int installLog(StorageLog storageLog);
}
