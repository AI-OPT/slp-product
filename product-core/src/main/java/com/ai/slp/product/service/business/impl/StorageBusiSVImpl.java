package com.ai.slp.product.service.business.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存业务操作
 *
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageBusiSVImpl implements IStorageBusiSV {
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IStorageLogAtomSV storageLogAtomSV;
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;

    /**
     * 废弃库存
     *
     * @param storage
     */
    @Override
    public void discardStorage(Storage storage) {
        //废弃库存
        if (storageAtomSV.updateById(storage)>0){
            StorageLog storageLog = new StorageLog();
            BeanUtils.copyProperties(storageLog,storage);
            storageLogAtomSV.installLog(storageLog);
        }
        //废弃SKU库存
        skuStorageAtomSV.discardSkuStorageOfStorage(storage.getStorageId(),storage.getOperId());
    }

    /**
     * 自动停用库存
     *
     * @param storage
     */
    @Override
    public void autoStopStorage(Storage storage) {
        //TODO...
    }


}
