package com.ai.slp.product.service.atom.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;

/**
 * 库存组日志原子操作
 *
 * Created by jackieliu on 16/5/5.
 */
public interface IStorageGroupLogAtomSV {

    /**
     * 添加库存组日志
     *
     * @param groupLog
     * @return
     */
    public int install(StorageGroupLog groupLog);
}
