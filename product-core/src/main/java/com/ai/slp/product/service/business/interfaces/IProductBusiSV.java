package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;


/**
 * 商城商品业务操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductBusiSV {

    /**
     * 添加商城商品
     * @param group
     * @return
     */
    public int addProductWithStorageGroup(StorageGroup group, Long operId);

    /**
     * 查询指定商品下的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public SkuSetForProduct querySkuByProdId(String tenantId,String prodId);
}
