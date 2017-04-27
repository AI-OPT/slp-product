package com.ai.slp.product.service.atom.interfaces.storage;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;

/**
 * Created by jackieliu on 16/5/12.
 */
public interface ISkuStorageAtomSV {

    /**
     * 废弃指定库存的SKU库存
     *
     * @param storageId
     * @param operId
     * @return
     */
    public int discardSkuStorageOfStorage(String storageId, Long operId);

    /**
     * 查询某个SKU单品的所有非废弃的库存
     *
     * @return
     */
    public List<SkuStorage> queryOfSku(String skuId);

    /**
     * 废弃指定SKU库存
     * @param skuStorageId
     * @param operId
     * @return
     */
    public int discardById(String skuStorageId,Long operId);

    /**
     * 添加指定SKU库存
     * @param skuStorage
     * @return
     */
    public int install(SkuStorage skuStorage);
    
    /**
     * 根据库存ID查询SKU库存
     *
     * @param storageId
     * @return
     * @author lipeng16
     */
    public List<SkuStorage> queryByStorageId(String storageId);

    /**
     * 根据库存ID查询SKU库存
     * @param storageId
     * @param hasDiscard 是否包含已废弃数据
     * @return
     */
    public List<SkuStorage> queryByStorageId(String storageId,boolean hasDiscard);

    /**
     * 查询指定库存集合中没有销售价格的SKU库存数量
     * @param storageIdList
     * @return
     */
    public int queryNoPriceOfStorageByIdList(List<String> storageIdList);

    /**
     * 查询指定库存中没有销售价格的SKU库存数量
     * @param storageId
     * @return
     */
    public int queryNoPriceOfStorageById(String storageId);

    /**
     * 查询指定标识的SKU库存
     * @param skuStorageId
     * @param hasDiscard 是否查询废弃库存, true:包含;false:不包含
     * @return
     */
    public SkuStorage queryById(String skuStorageId,boolean hasDiscard);

    /**
     * 根据标识符更新SKU库存
     * @param skuStorage
     * @return
     */
    public int updateById(SkuStorage skuStorage); 
    
    /**
     * 根据标识符更新SKU库存
     * @param skuStorage
     * @return
     */
    public int updateById4Service(String groupId,Short priorityNum,Long price,Long operId); 

    /**
     * 查询指定库存集合下的SKU库存
     *
     * @param priorityNum
     * @return
     */
    public List<SkuStorage> queryPriorityOfGroup(String groupId, String skuId, Short priorityNum);

    /**
     * 更新指定库存下指定SKU的价格
     *
     * @param storageId
     * @param price
     * @return
     */
    public int updatePrice(String storageId,String skuId,Long price,Long operId);

    /**
     * 查询库存组下已启用库存下未设置价格的SKU库存数量
     * @param tenantId
     * @param groupId
     * @return
     */
    public int countOfNoPrice(String tenantId,String groupId);

    /**
     * 查询sku价格
     * @param tenantId
     * @param prodId
     * @param skuId
     * @return
     */
    public Long queryPriceOfSku(String tenantId,String prodId,String skuId);
    
    /**
     * 根据条件修改SKU库存
     * @param skuStorage
     * @return
     */
    public int updateByCondtion(SkuStorage skuStorage,SkuStorage cond); 
}
