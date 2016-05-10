package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;

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
    IProductAtomSV productAtomSV;
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IStorageLogAtomSV storageLogAtomSV;
    @Autowired
    IProdPriceLogAtomSV prodPriceLogAtomSV;
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
        productBusiSV.addProductWithStorageGroup(group,storageGroup.getCreateId());
        return installNum;
    }

    /**
     * 更新库存组
     *
     * @param storageGroup
     * @return
     */
    @Override
    public int updateGroup(StorageGroupUpdate storageGroup) {
        //查询库存组是否存在
        StorageGroup group = storageGroupAtomSV.queryByGroupId(
                storageGroup.getTenantId(),storageGroup.getStorageGroupId());
        if (group == null)
            throw new BusinessException("","要更新库存组信息不存在,租户ID:"+storageGroup.getTenantId()
            +",库存组标识:"+storageGroup.getStorageGroupId());
        //已废弃,不允许变更
        if (StorageConstants.GROUP_STATE_DISCARD.equals(group.getState())
                || StorageConstants.GROUP_STATE_AUTO_DISCARD.equals(group.getState())){
            throw new BusinessException("","库存组已经废弃,不允许更新信息");
        }
        //设置可更新信息
        group.setStorageGroupName(storageGroup.getStorageGroupName());
//        group.setSerialNumber(storageGroup.get());
        group.setOperId(storageGroup.getOperId());
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
    public StorageGroupInfo queryGroupInfoByGroupId(String tenantId, String groupId) {
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
//        groupInfo.setProdId(group.getStandedProdId());
//        groupInfo.setProdName(standedProduct.getStandedProductName());
        //库存总量
        Map<Short,List<StorageInfo>> storageMap = new HashMap<>();
        //====填充库存集合信息
        //当前启用的优先级
        Short activePriority = null;
        //库存组库存总量
        long storageTotal = 0;
        //查询库存组的库存集合
        List<Storage> storageList = storageAtomSV.queryOfGroup(group.getTenantId(),group.getStorageGroupId());
        for (Storage storage:storageList){
            List<StorageInfo> stoStorageList = storageMap.get(storage.getPriorityNumber());
            if (stoStorageList==null){
                stoStorageList = new ArrayList<>();
                storageMap.put(storage.getPriorityNumber(),stoStorageList);
            }
            StorageInfo stoStorage = new StorageInfo();
            BeanUtils.copyProperties(stoStorage,storage);
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

    @Override
    public int updateStorageGroupPrice(StorageGroupSalePrice salePrice) {
        if(salePrice == null)
            return 0;
        if(salePrice.getGroupId() == null || salePrice.getOperId() == null)
            throw new BusinessException("", "找不到指定的库存组="+salePrice.getGroupId()+",和操作人="+salePrice.getOperId());
        //判断库存是否废弃
        if(storageGroupAtomSV.queryByGroupId(salePrice.getTenantId(), salePrice.getGroupId()).getState().equals("3"))
            throw new BusinessException("", "废弃状态的库存组不能更新价格信息");
        //填充价格等基本信息
        StorageGroup storageGroup = new StorageGroup();
        BeanUtils.copyProperties(storageGroup, salePrice);
        int updateNum = storageGroupAtomSV.updateStorGroupPrice(storageGroup);
        //写入日志信息
        if(updateNum > 0){
            ProdPriceLog prodPriceLog = new ProdPriceLog();
            BeanUtils.copyProperties(prodPriceLog, storageGroup);
            prodPriceLog.setObjId(storageGroup.getStorageGroupId());
            //设置类型为库存组
            prodPriceLog.setObjType("SG");
            prodPriceLog.setUpdatePrice(storageGroup.getLowSalePrice());
            if(storageGroup.getHighSalePrice() != null)
                prodPriceLog.setUpdatePeice2(storageGroup.getHighSalePrice());
            prodPriceLogAtomSV.insert(prodPriceLog);
        }
        return storageGroupAtomSV.updateStorGroupPrice(storageGroup);
    }

    /**
     * 变更库存组状态
     *
     * @param tenantId 租户ID
     * @param groupId  库存组ID
     * @param state    要变更状态
     * @param operId   操作者ID
     */
    @Override
    public void updateGroupState(String tenantId, String groupId, String state, Long operId) {
        //查询库存组是否存在
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId,groupId);
        if (storageGroup == null){
            logger.warn("要查询库存组不存在,租户ID:"+tenantId+",库存组标识:"+groupId);
            throw new BusinessException("","库存组不存在,租户ID:"+tenantId+",库存组标识:"+groupId);
        }
        String oldState = storageGroup.getState();
        if (oldState.equals(state)){
            throw new BusinessException("","状态已经变更,不需要重复变更");
        }
        //查看是否为废弃状态
        if (StorageConstants.GROUP_STATE_DISCARD.equals(oldState)
                || StorageConstants.GROUP_STATE_AUTO_DISCARD.equals(oldState)){
            throw new BusinessException("",groupId+"库存组已废弃,不允许变更状态");
        }
        //启用
        if (StorageConstants.GROUP_STATE_ACTIVE.equals(state)){
            startGroup(storageGroup,operId);
        //停用
        }else if (StorageConstants.GROUP_STATE_STOP.equals(state)){
            stopGroup(storageGroup,operId);
        //废弃处理
        }else if(StorageConstants.GROUP_STATE_DISCARD.equals(state)){
            discardGroup(storageGroup,operId);
        }
    }

    /**
     * 库存组启用
     * @param storageGroup
     */
    private void startGroup(StorageGroup storageGroup,Long operId){
        //检查是否已配置路由
        if (storageGroup.getRouteGroupId()==null){
            throw new BusinessException("","库存组没有配置路由信息,不能启用");
        }
        //检查路由是否已为启用状态
        //TODO...
        //库存组设置为启用状态
        storageGroup.setState(StorageConstants.GROUP_STATE_ACTIVE);
        storageGroup.setOperId(operId);
        if (storageGroupAtomSV.updateById(storageGroup)>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
        //若对应商品为"62停用下架",则进行自动上架.
        Product product = productAtomSV.selectByGroupId(
                storageGroup.getTenantId(),storageGroup.getStorageGroupId());
        if (product!=null && ProductConstants.STATE_STOP.equals(product.getState())){
            productBusiSV.changeToSaleForStop(product.getTenantId(),product.getProdId(),operId);
        }
    }

    /**
     * 库存组停用
     * @param storageGroup
     * @param operId
     */
    private void stopGroup(StorageGroup storageGroup,Long operId){
        //库存组变更为停用
        storageGroup.setState(StorageConstants.GROUP_STATE_STOP);
        storageGroup.setOperId(operId);
        if (storageGroupAtomSV.updateById(storageGroup)>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
        //商品进行停用下架
        Product product = productAtomSV.selectByGroupId(
                storageGroup.getTenantId(),storageGroup.getStorageGroupId());
        if (product!=null && ProductConstants.STATE_IN_SALE.equals(product.getState())){
            productBusiSV.stopProduct(product.getTenantId(),product.getProdId(),operId);
        }
    }

    /**
     * 库存组废弃
     * @param storageGroup
     * @param operId
     */
    private void discardGroup(StorageGroup storageGroup,Long operId){
        //商品废弃
        Product product = productAtomSV.selectByGroupId(
                storageGroup.getTenantId(),storageGroup.getStorageGroupId());
        if (product!=null){
            productBusiSV.discardProduct(product.getTenantId(),product.getProdId(),operId);
        }
        //查询该库存组下所有库存
        List<Storage> storageList = storageAtomSV.queryOfGroup(
                storageGroup.getTenantId(),storageGroup.getStorageGroupId());
        //库存废弃
        for (Storage storage:storageList){
            //若已废弃,则不处理
            if (StorageConstants.STATE_DISCARD.equals(storage.getState())
                    ||StorageConstants.STATE_AUTO_DISCARD.equals(storage.getState()))
                continue;
            storage.setState(StorageConstants.STATE_DISCARD);
            storage.setOperId(operId);
            if (storageAtomSV.updateById(storage)>0){
                StorageLog storageLog = new StorageLog();
                BeanUtils.copyProperties(storageLog,storage);
                storageLogAtomSV.installLog(storageLog);
            }
        }
        //库存组废弃
        storageGroup.setState(StorageConstants.GROUP_STATE_DISCARD);
        storageGroup.setOperId(operId);
        if (storageGroupAtomSV.updateById(storageGroup)>0){
            StorageGroupLog groupLog = new StorageGroupLog();
            BeanUtils.copyProperties(groupLog,groupLog);
            storageGroupLogAtomSV.install(groupLog);
        }
    }
}
