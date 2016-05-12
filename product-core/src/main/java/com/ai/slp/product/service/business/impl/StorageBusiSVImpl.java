package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(StorageBusiSVImpl.class);
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

    /**
     * 更改库存状态
     *
     * @param tenantId
     * @param storageId
     * @param state
     * @param operId
     */
    @Override
    public void changeStorageStats(String tenantId, String storageId, String state, Long operId) {
        //查询库存是否存在
        Storage storage = storageAtomSV.queryById(storageId);
        if (storage==null){
            throw new BusinessException("","要查询库存不存在,库存标识:"+storageId);
        }
        //查询状态是否变更
        if (storage.getState().equals(state)){
            throw new BusinessException("","库存状态已变更,不需重复变更");
        }
        //废弃状态不允许变更
        if (StorageConstants.Storage.State.DISCARD.equals(storage.getState())
                ||StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())){
            throw new BusinessException("","库存已废弃,不允许变更状态");
        }

        switch (state){
            case StorageConstants.Storage.State.ACTIVE://转启用

                break;
            case StorageConstants.Storage.State.STOP://转停用

                break;
            case StorageConstants.Storage.State.DISCARD://转废弃
                discardStorage(storage);
                break;
            default:
                throw new BusinessException("","无法识别的状态:"+state);
        }
    }


}
