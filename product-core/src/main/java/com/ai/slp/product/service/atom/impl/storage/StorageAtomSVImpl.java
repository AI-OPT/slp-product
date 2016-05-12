package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class StorageAtomSVImpl implements IStorageAtomSV {
    @Autowired
    StorageMapper storageMapper;

    /**
     * 查询指定库存组下的库存信息,按照优先级正序排序
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    @Override
    public List<Storage> queryOfGroup(String tenantId, String groupId) {
        StorageCriteria example = new StorageCriteria();
        example.setOrderByClause("PRIORITY_NUMBER");
        example.createCriteria().andStorageGroupIdEqualTo(groupId);
        return storageMapper.selectByExample(example);
    }

    @Override
    public int findStorage(String tenantId, String storageId) {
        StorageCriteria example = new StorageCriteria();
        example.createCriteria().andStorageIdEqualTo(storageId)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return storageMapper.countByExample(example);
    }

    /**
     * 查询启用状态的库存信息
     *
     * @param tenantId
     * @param groupId
     * @param hasUsable
     * @return
     */
    @Override
    public List<Storage> queryActive(String tenantId, String groupId,boolean hasUsable) {
        StorageCriteria example = new StorageCriteria();
        example.setOrderByClause("PRIORITY_NUMBER");
        List<String> activeList = new ArrayList<>();
        activeList.add(StorageConstants.Storage.State.ACTIVE);
        activeList.add(StorageConstants.Storage.State.AUTO_ACTIVE);
        StorageCriteria.Criteria criteria = example.createCriteria();
        criteria.andStorageGroupIdEqualTo(groupId).andStateIn(activeList);
        //查询可用量大于0的
        if (hasUsable)
            criteria.andUsableNumGreaterThan(0l);
        return storageMapper.selectByExample(example);
    }

    /**
     * 更新库存信息
     *
     * @param storage
     * @return
     */
    @Override
    public int updateById(Storage storage) {
        storage.setOperTime(DateUtils.currTimeStamp());
        return storageMapper.updateByPrimaryKey(storage);
    }

    /**
     * 查询指定标识的库存
     *
     * @param storageId
     * @return
     */
    @Override
    public Storage queryById(String storageId) {
        Storage storage = storageMapper.selectByPrimaryKey(storageId);
        if (StorageConstants.Storage.State.DISCARD.equals(storage.getState())
                || StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState()))
            return null;
        return storage;
    }
}
