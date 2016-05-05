package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupCriteria;
import com.ai.slp.product.dao.mapper.interfaces.SysSequenceCreditMapper;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageGroupMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.util.DateUtils;
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
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;

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
        discard.add(StorageConstants.GROUP_STATE_DISCARD);
        discard.add(StorageConstants.GROUP_STATE_AUTO_DISCARD);
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
        stateList.add(StorageConstants.GROUP_STATE_ACTIVE);
        stateList.add(StorageConstants.GROUP_STATE_AUTO_ACTIVE);
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedProdId)
                .andStateIn(stateList);
        return groupMapper.countByExample(example);
    }

    /**
     * 添加库存组信息
     *
     * @param group
     * @return
     */
    @Override
    public int installGroup(StorageGroup group) {
        group.setStorageGroupId(sequenceCreditAtomSV.gen12SeqByName());
        if (group.getCreateTime()==null)
            group.setCreateTime(DateUtils.currTimeStamp());
        group.setOperTime(group.getCreateTime());
        group.setOperId(group.getCreateId());
        return groupMapper.insert(group);
    }
}
