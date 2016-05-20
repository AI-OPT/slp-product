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
     * @param tenantId
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
     * 查询指定标识的库存
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

}
