package com.ai.slp.product.service.business.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jackieliu on 16/5/26.
 */
@Service
@Transactional
public class StorageNumDbBusiSVImpl {
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IStorageLogAtomSV storageLogAtomSV;
    /**
     * 使用库存量
     *
     * @param skuNumMap
     * @return
     */
    @Async
    public void userStorageNum(Map<String,Integer> skuNumMap) {
        if (skuNumMap==null || skuNumMap.isEmpty())
            return;
        Iterator<String> iterator = skuNumMap.keySet().iterator();
        while (iterator.hasNext()){
            String skuStorageId = iterator.next();
            int skuNum = skuNumMap.get(skuStorageId);
            SkuStorage skuStorage = skuStorageAtomSV.queryById(skuStorageId);
            if (skuStorage ==null)
                continue;
            skuStorage.setUsableNum(skuStorage.getUsableNum()-skuNum);
            //若SKU库存小于等于零,则状态变更为"自动停用"
            if (skuStorage.getUsableNum()<=0){
                skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_STOP);
            }
            skuStorageAtomSV.updateById(skuStorage);
            //更新库存信息
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null)
                continue;
            storage.setUsableNum(skuStorage.getUsableNum()-skuNum);
            //若库存小于等于零,则状态变更为"自动停用"
            if (skuStorage.getUsableNum()<=0)
                storage.setState(StorageConstants.Storage.State.AUTO_STOP);
            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
        }
    }

    /**
     * 回退库存量
     *
     * @param tenantId   租户id
     * @param skuId      单品标识
     * @param storageNum 库存回退集合
     */
    @Async
    public void backStorageNum(String tenantId, String skuId, Map<String, Integer> storageNum) {

    }
}
