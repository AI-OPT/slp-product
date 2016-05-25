package com.ai.slp.product.service.atom.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;

import java.util.List;

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
     * 查询指定库存集合中没有销售价格的SKU库存数量
     * @param storageIdList
     * @return
     */
    public int queryNoPriceOfStorageByIdList(List<String> storageIdList);
}
