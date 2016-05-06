package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<Storage> queryOfGroup(String tenantId, Long groupId) {
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
}
