package com.ai.slp.product.service.atom.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.Storage;

import java.util.List;

/**
 * Created by jackieliu on 16/5/5.
 */
public interface IStorageAtomSV {

    /**
     * 查询指定库存组下的库存信息,按照优先级正序排序
     * @param tenantId
     * @param groupId
     * @return
     */
    public List<Storage> queryOfGroup(String tenantId,String groupId);
    
    /**
     * 查询库存是否存在(通过预警对象标识) 
     * @param storageId
     * @return
     * @author lipeng16
     */
    public int findStorage(String storageId);

    /**
     * 查询启用状态的库存信息
     *
     * @param tenantId
     * @param groupId
     * @param hasUsable 是否需要查询可用量大于零
     * @return
     */
    public List<Storage> queryActive(String tenantId,String groupId,boolean hasUsable);

    /**
     * 更新库存信息
     * @param storage
     * @return
     */
    public int updateById(Storage storage);

    /**
     * 查询指定标识的库存(不包括废弃的)
     * @param storageId
     * @return
     */
    public Storage queryById(String storageId);
    
    /**
     * 更新库存销售价
     * 
     * @param storage
     * @return
     * @author lipeng16
     */
    public int updateSaleById(Storage storage);
    
    /**
     * 新增库存信息
     *
     * @param storage
     * @return
     * @author lipeng16
     */
    public int insertStorage(Storage storage);
    
    /**
     * 通过库存标识查询库存(所有状态,包括启用\停用\废弃等)
     *
     * @param storageId
     * @return
     * @author lipeng16
     */
    public Storage queryAllStateStorage(String storageId);
    
    /**
     * 通过库存组标识和库存标识查找库存信息(所有状态)
     *
     * @return
     * @author lipeng16
     */
    public Storage queryStorageByGroupAndId(String storageGroupId, String storageId);


    /**
     * 查询指定库存组下指定优先级库存列表
     *
     * @param groupId
     * @param priorityNum
     * @param hasDestory
     * @return
     */
    public List<Storage> queryStorageByGroupIdAndPriority(String groupId,short priorityNum,boolean hasDestory);

    /**
     * 查询除指定优先级外,非促销,可用量大于零的库存,按照优先级正序排列
     *
     * @param groupId  库存组表示
     * @param priority 指定优先级
     * @param after null:优先级之前和之后;true:指定优先级之后,false:指定优先级之前
     * @param hasDestory 是否查询废弃库存,包括手动废弃和自动废弃
     * @return
     */
    public List<Storage> queryNoTimeNoPrioritySelf(String groupId,Short priority,Boolean after,boolean hasDestory);

    /**
     * 查询现在正促销优先级的库存,可用量大于零
     * @param groupId
     * @param hasDestory 是否包括废弃的库存
     * @return
     */
    public List<Storage> queryTimeActiveOfNow(String groupId,boolean hasDestory);
}
