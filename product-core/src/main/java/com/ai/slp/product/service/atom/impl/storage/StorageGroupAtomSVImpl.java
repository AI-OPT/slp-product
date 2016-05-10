package com.ai.slp.product.service.atom.impl.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageGroupMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

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
        group.setStorageGroupId(SequenceUtil.genStorageGroupId());
        if (group.getCreateTime()==null)
            group.setCreateTime(DateUtils.currTimeStamp());
        group.setOperTime(group.getCreateTime());
        group.setOperId(group.getCreateId());
        return groupMapper.insert(group);
    }

    /**
     * 查询指定标识的库存组
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    @Override
    public StorageGroup queryByGroupId(String tenantId, String groupId) {
        StorageGroupCriteria example = new StorageGroupCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andStorageGroupIdEqualTo(groupId);
        List<StorageGroup> groupList = groupMapper.selectByExample(example);
        return groupList==null || groupList.isEmpty()?null:groupList.get(0);
    }

    /**
     * 更新指定库存组标识的库存组信息
     *
     * @param group
     * @return
     */
    @Override
    public int updateById(StorageGroup group) {
        group.setOperTime(DateUtils.currTimeStamp());
        return groupMapper.updateByPrimaryKey(group);
    }

    /**
     * 查询某个标准品下的库存组列表,创建时间倒序显示
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    @Override
    public List<StorageGroup> queryOfStandedProd(String tenantId, String standedProdId) {
        StorageGroupCriteria example = new StorageGroupCriteria();
        example.setOrderByClause(" CREATE_TIME desc");
        example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId);
        return groupMapper.selectByExample(example);
    }
    
    /**
     * 修改库存组价格信息
     */
    @Override
    public int updateStorGroupPrice(StorageGroup storageGroup) {
        storageGroup.setOperTime(DateUtils.currTimeStamp());
        return groupMapper.updateByPrimaryKeySelective(storageGroup);
    }

    @Override
    public int countStorGroupByProdID(String tenantId, String standedProdId) {
        StorageGroupCriteria example = new StorageGroupCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(tenantId).andStateNotEqualTo("3").andStateNotEqualTo("31");
        return groupMapper.countByExample(example);
    }
}
