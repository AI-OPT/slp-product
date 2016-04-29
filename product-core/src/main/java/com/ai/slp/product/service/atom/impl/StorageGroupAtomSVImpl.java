package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.constants.StorageAndGroupConstants;
import com.ai.slp.product.dao.mapper.bo.StorageGroupCriteria;
import com.ai.slp.product.dao.mapper.interfaces.StorageGroupMapper;
import com.ai.slp.product.service.atom.interfaces.IStorageGroupAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存组原子操作
 * Created by jackieliu on 16/4/29.
 */
@Component
public class StorageGroupAtomSVImpl implements IStorageGroupAtomSV {
    @Autowired
    StorageGroupMapper groupMapper;

    /**
     * 没有废弃的库存组数量
     * @param tenantId
     * @param standedProdId
     * @return
     */
    @Override
    public int queryCountNoDiscard(String tenantId, String standedProdId) {
        StorageGroupCriteria example = new StorageGroupCriteria();
        List<String> discard = new ArrayList<>();
        discard.add(StorageAndGroupConstants.GROUP_STATE_DISCARD);
        discard.add(StorageAndGroupConstants.GROUP_STATE_AUTO_DISCARD);
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedProdId)
                .andStateNotIn(discard);
        return groupMapper.countByExample(example);
    }

    /**
     * 启用状态的库存组数量
     * @param tenantId
     * @param standedProdId
     * @return
     */
    @Override
    public int queryCountActive(String tenantId, String standedProdId) {
        StorageGroupCriteria example = new StorageGroupCriteria();
        List<String> stateList = new ArrayList<>();
        stateList.add(StorageAndGroupConstants.GROUP_STATE_ACTIVE);
        stateList.add(StorageAndGroupConstants.GROUP_STATE_AUTO_ACTIVE);
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedProdId)
                .andStateIn(stateList);
        return groupMapper.countByExample(example);
    }
}
