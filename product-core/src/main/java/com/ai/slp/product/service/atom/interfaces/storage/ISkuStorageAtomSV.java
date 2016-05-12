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

    public int discardById(String skuStorageId,Long operId);
}
