package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.SkuStorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SKU库存原子操作
 * Created by jackieliu on 16/5/12.
 */
@Component
public class SkuStorageAtomSVImpl implements ISkuStorageAtomSV {
    @Autowired
    SkuStorageMapper skuStorageMapper;

    /**
     * 废弃指定库存的SKU库存
     *
     * @param storageId
     * @param operId
     * @return
     */
    @Override
    public int discardSkuStorageOfStorage(String storageId, Long operId) {
        SkuStorage skuStorage = new SkuStorage();
        skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_DISCARD);
        skuStorage.setOperTime(DateUtils.currTimeStamp());
        skuStorage.setOperId(operId);
        //废弃状态
        SkuStorageCriteria example = new SkuStorageCriteria();
        example.createCriteria().andStorageIdEqualTo(storageId)
            .andStateNotEqualTo(StorageConstants.SkuStorage.State.AUTO_DISCARD);
        return skuStorageMapper.updateByExampleSelective(skuStorage,example);
    }

    /**
     * 查询某个SKU单品的所有非废弃的库存
     *
     * @param skuId
     * @return
     */
    @Override
    public List<SkuStorage> queryOfSku(String skuId) {
        SkuStorageCriteria example = new SkuStorageCriteria();
        example.createCriteria().andSkuIdEqualTo(skuId)
                .andStateNotEqualTo(StorageConstants.SkuStorage.State.AUTO_DISCARD);
        return skuStorageMapper.selectByExample(example);
    }

    @Override
    public int discardById(String skuStorageId, Long operId) {
        SkuStorage skuStorage = new SkuStorage();
        skuStorage.setSkuStorageId(skuStorageId);
        skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_DISCARD);
        skuStorage.setOperId(operId);
        skuStorage.setOperTime(DateUtils.currTimeStamp());
        return skuStorageMapper.updateByPrimaryKeySelective(skuStorage);
    }

    /**
     * 添加指定SKU库存
     *
     * @param skuStorage
     * @return
     */
    @Override
    public int install(SkuStorage skuStorage) {
    	skuStorage.setSkuStorageId(SequenceUtil.genskuStorageId());
        skuStorage.setOperTime(DateUtils.currTimeStamp());
        return skuStorageMapper.insert(skuStorage);
    }
}
