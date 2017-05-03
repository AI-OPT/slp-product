package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;

import java.util.List;
import java.util.Map;

/**
 * 库存组业务操作
 * Created by jackieliu on 16/5/4.
 */
public interface IStorageGroupBusiSV {

    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    public String addGroup(STOStorageGroup storageGroup);
    
    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    public StorageGroup addGroupObj(STOStorageGroup storageGroup);
    
    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    public StorageGroup addGroupObj4Service(StandedProduct standedProduct);

    /**
     * 更新库存组
     *
     * @param storageGroup
     * @return
     */
    public int updateGroupName(NameUpReq storageGroup);

    /**
     * 查询指定标准品下的库存组信息,包括库存信息
     *
     * @param tenantId
     * @param productId
     * @return
     */
    public List<StorageGroup> queryGroupInfoByNormProId(String tenantId,String supplierId, String productId);
//    public List<StorageGroupRes> queryGroupInfoByNormProId(String tenantId,String supplierId, String productId);

    /**
     * 查询单个库存组的信息
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public StorageGroupRes queryGroupInfoByGroupId(String tenantId,String supplierId, String groupId);
    
    /**
     * 更新库存组价格信息
     * @param salePrice
     * @return
     * @author lipeng16
     */
    public int updateStorageGroupPrice(StorageGroupSalePrice salePrice);

    /**
     * 变更库存组状态
     * @param tenantId 租户ID
     * @param groupId 库存组ID
     * @param state 要变更状态
     * @param operId 操作者ID
     */
    public void updateGroupState(String tenantId,String supplierId,String groupId,String state,Long operId);
    
    /**
     * 根据标准品标识分页查询库存组信息<br>
     * 库存组不包括废弃状态的
     * 
     * @param infoQuery
     * @return
     * @author lipeng16
     */
    public PageInfoResponse<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery);

    /**
     * 切换库存组优先级
     * @param tenantId
     * @param groupId
     * @param nowPriority
     */
    public void changeUsePriority(String tenantId,String groupId,int nowPriority);

    /**
     * 刷新库存组缓存
     * @param storageGroup
     */
    public void flushStorageCache(StorageGroup storageGroup);

    /**
     * 刷新库存组缓存
     * @param tenantId
     * @param groupId 
     */
    public void flushStorageCache(String tenantId,String groupId);

    /**
     * 分页查询指定条件的库存组
     * @return
     */
    public PageInfoResponse<StorageGroup4List> queryPageForGroupList(StorageGroupQueryPage groupQuery);

    /**
     * 清空库存组下的缓存信息
     * @param tenantId
     * @param groupId
     */
    public void cleanGroupCache(String tenantId,String groupId);

    /**
     * 库存组自动启用
     * @param tenantId
     * @param groupId
     */
    public boolean changeGroupAutoStart(String tenantId, String groupId);

    /**
     * 库存组自动停用
     * @param tenantId
     * @param groupId
     */
    public boolean changeGroupAutoStop(String tenantId, String groupId);

    /**
     * 查询库存组下某个优先级中sku的价格
     *
     * @param tenantId
     * @param supplierId
     * @param pn
     * @return
     */
    public Map<String,Long> querySkuPriceOfGroupPn(
            String tenantId, String supplierId,String groupId, Short pn);

    /**
     * 设置库存组的关联路由组的标识
     *
     * @param tenantId
     * @param groupId
     * @param routeGroupId
     * @param operId
     */
    public void changeRouteGroupId(String tenantId,String groupId,String routeGroupId,Long operId);
    
    /**
     * 查询单个库存组的信息
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public StorageGroupRestwo queryGroupInfoAllByGroupId(String tenantId,String supplierId, String groupId);
    
    /**
     * 获取库存组信息
     * @param group
     * @return
     * @author Gavin
     * @UCUSER
     */
    public StorageGroupRes genStorageGroupInfo(StorageGroup group);

    /**
     * 添加库存组
     * @param storageGroup
     * @param storageGroupId
     * @return
     * @author Gavin
     * @UCUSER
     */
	StorageGroup addGroupObj(STOStorageGroup storageGroup, String storageGroupId);
}
