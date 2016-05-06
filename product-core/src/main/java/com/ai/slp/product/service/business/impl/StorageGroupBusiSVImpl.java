package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StorageGroupInfo;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存组操作
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageGroupBusiSVImpl implements IStorageGroupBusiSV {
    private static Logger logger = LoggerFactory.getLogger(StorageGroupBusiSVImpl.class);
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageGroupLogAtomSV storageGroupLogAtomSV;
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    @Override
    public int addGroup(STOStorageGroup storageGroup) {
        //查询标准品是否有销售属性
        String tenantId = storageGroup.getTenantId();
        String standedProdId = storageGroup.getProdId();
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,standedProdId);
        if (standedProduct==null) {
            throw new BusinessException("","未找到对应标准品信息,租户id:"+tenantId+",标准品标识:"+standedProdId);
        }
        //添加库存组信息,状态默认为停用
        StorageGroup group = new StorageGroup();
        BeanUtils.copyProperties(group,storageGroup);
        //默认为停用状态
        group.setState(StorageConstants.GROUP_STATE_STOP);
        //添加库存组日志
        int installNum = storageGroupAtomSV.installGroup(group);
        if (installNum>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
        //添加商品
        productBusiSV.addProductWithStorageGroup(group,storageGroup.getOperId(),storageGroup.getCreateTime());
        return installNum;
    }

    /**
     * 更新库存组
     *
     * @param storageGroup
     * @return
     */
    @Override
    public int updateGroup(STOStorageGroup storageGroup) {
        //查询库存组是否存在
        StorageGroup group = storageGroupAtomSV.queryByGroupId(
                storageGroup.getTenantId(),storageGroup.getGroupId());
        if (group == null)
            throw new BusinessException("","要更新库存组信息不存在,租户ID:"+storageGroup.getTenantId()
            +",库存组标识:"+storageGroup.getGroupId());
        //已废弃,不允许变更
        if (StorageConstants.GROUP_STATE_DISCARD.equals(group.getState())
                || StorageConstants.GROUP_STATE_AUTO_DISCARD.equals(group.getState())){
            throw new BusinessException("","库存组已经废弃,不允许更新信息");
        }
        //设置可更新信息
        group.setStorageGroupName(storageGroup.getGroupName());
        group.setSerialNumber(storageGroup.getSerialNumber());
        group.setOperId(storageGroup.getOperId());
        group.setOperTime(storageGroup.getOperTime());
        int updateNum = storageGroupAtomSV.updateById(group);
        //添加库存组日志
        if (updateNum>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
        return updateNum;
    }

    /**
     * 查询指定标准品下的库存组信息,包括库存信息
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public List<StorageGroupInfo> queryGroupInfoByNormProId(String tenantId, String productId) {
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,productId);
        if (standedProduct==null)
            throw new BusinessException("","未找到对应的标准品信息,租户ID:"+tenantId+",标准品标识:"+productId);
        //查询出标准品下的所有库存组,创建时间倒序
        List<StorageGroupInfo> groupInfoList = new ArrayList<>();
        List<StorageGroup> groupList = storageGroupAtomSV.queryOfStandedProd(tenantId,productId);
        for (StorageGroup storageGroup: groupList){
            groupInfoList.add(genStorageGroupInfo(storageGroup,standedProduct));
        }
        return groupInfoList;
    }

    /**
     * 查询单个库存组的信息
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    @Override
    public StorageGroupInfo queryGroupInfoByGroupId(String tenantId, Long groupId) {
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId,groupId);
        if (storageGroup == null) {
            logger.warn("租户ID:" + tenantId + ",库存组标识:" + groupId);
            throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
        }
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,storageGroup.getStandedProdId());
        if (standedProduct==null) {
            logger.warn("租户ID:" + tenantId + ",库存组标识:" + groupId);
            throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
        }
        return genStorageGroupInfo(storageGroup,standedProduct);
    }

    /**
     * 根据库存组信息产生接口返回库存组信息,包括库存组下的库存信息
     * @param group
     * @return
     */
    private StorageGroupInfo genStorageGroupInfo(StorageGroup group,StandedProduct standedProduct){
        StorageGroupInfo groupInfo = new StorageGroupInfo();
        BeanUtils.copyProperties(groupInfo,group);
        //填充库存组信息
        groupInfo.setGroupId(group.getStorageGroupId());
        groupInfo.setGroupName(group.getStorageGroupName());
        groupInfo.setProdId(group.getStandedProdId());
        groupInfo.setProdName(standedProduct.getStandedProductName());
        //库存总量
        Map<Short,List<STOStorage>> storageMap = new HashMap<>();
        //====填充库存集合信息
        //当前启用的优先级
        Short activePriority = null;
        //库存组库存总量
        long storageTotal = 0;
        //查询库存组的库存集合
        List<Storage> storageList = storageAtomSV.queryOfGroup(group.getTenantId(),group.getStorageGroupId());
        for (Storage storage:storageList){
            List<STOStorage> stoStorageList = storageMap.get(storage.getPriorityNumber());
            if (stoStorageList==null){
                stoStorageList = new ArrayList<>();
                storageMap.put(storage.getPriorityNumber(),stoStorageList);
            }
            STOStorage stoStorage = new STOStorage();
            BeanUtils.copyProperties(stoStorage,storage);
            stoStorage.setGroupId(storage.getStorageGroupId());
            stoStorageList.add(stoStorage);
            //如果库存为启用状态
            if (StorageConstants.GROUP_STATE_ACTIVE.equals(storage.getState())
                    || StorageConstants.GROUP_STATE_AUTO_ACTIVE.equals(storage.getState())){
                //若为设置启用优先级,则设置第一个启用库存的优先级为启用优先级
                if (activePriority==null)
                    activePriority = storage.getPriorityNumber();
                //若库存优先级与启用优先级不一致,则直接跳过
                if(activePriority != storage.getPriorityNumber())
                    continue;
                //添加库存总量
                storageTotal += storage.getTotalNum();
            }
        }
        groupInfo.setStorageTotal(storageTotal);
        groupInfo.setStorageList(storageMap);
        return groupInfo;
    }
}
