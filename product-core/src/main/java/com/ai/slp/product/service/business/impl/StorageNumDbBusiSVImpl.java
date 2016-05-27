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
     * 库存量变更操作
     *
     * @param skuNumMap
     * @param isUser true:库存使用量;false:库存回退量
     * @return
     */
    @Async
    public void userStorageNum(Map<String,Integer> skuNumMap,boolean isUser) {
        if (skuNumMap==null || skuNumMap.isEmpty())
            return;
        Iterator<String> iterator = skuNumMap.keySet().iterator();
        while (iterator.hasNext()){
            String skuStorageId = iterator.next();
            int skuNum = skuNumMap.get(skuStorageId);
            //若为使用量,则为减少量
            if (isUser)
                skuNum = 0-skuNum;
            SkuStorage skuStorage = skuStorageAtomSV.queryById(skuStorageId,true);
            if (skuStorage ==null)
                continue;
            skuStorage.setUsableNum(skuStorage.getUsableNum()+skuNum);
            //若SKU库存小于等于零,且状态不为"废弃",则设置为"自动停用
            if (skuStorage.getUsableNum()<=0
                    && !StorageConstants.SkuStorage.State.AUTO_DISCARD.equals(skuStorage.getState())){
                skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_STOP);
            //若SKU库存大于零,且状态为"自动停用",则设置为"启用".
            } else if (skuStorage.getUsableNum()>0
                    && StorageConstants.SkuStorage.State.AUTO_STOP.equals(skuStorage.getState())){
                skuStorage.setState(StorageConstants.SkuStorage.State.ACTIVE);
            }
            skuStorageAtomSV.updateById(skuStorage);

            //更新库存信息
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null)
                continue;
            storage.setUsableNum(skuStorage.getUsableNum()+skuNum);
            //若库存小于等于零,且状态不为"废弃",则状态变更为"自动停用"
            if (skuStorage.getUsableNum()<=0
                    && !StorageConstants.Storage.State.DISCARD.equals(storage.getState())
                    && !StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())) {
                storage.setState(StorageConstants.Storage.State.AUTO_STOP);
            }

            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
        }
    }

}
