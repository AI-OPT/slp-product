package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;

import java.util.List;
import java.util.Map;


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
     * 对停用下架的商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    public void changeToSaleForStop(String tenantId,String prodId,Long operId);

    /**
     * 进行停用下架
     * @param tenantId
     * @param prodId
     */
    public void stopProduct(String tenantId,String prodId,Long operId);

    /**
     * 废弃商品
     * @param tenantId
     * @param prodId
     */
    public void discardProduct(String tenantId,String prodId,Long operId);

}
