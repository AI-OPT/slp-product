package com.ai.slp.product.service.atom.impl.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.util.DateUtils;

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
    public int findStorage(String tenantId, String objectId) {
        StorageCriteria example = new StorageCriteria();
        example.createCriteria().andStorageIdEqualTo(objectId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
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
        activeList.add(StorageConstants.STATE_ACTIVE);
        activeList.add(StorageConstants.STATE_AUTO_ACTIVE);
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

	@Override
	public int updateSaleById(Storage storage) {
		storage.setOperTime(DateUtils.currTimeStamp());
		return storageMapper.updateByPrimaryKeySelective(storage);
	}
}
