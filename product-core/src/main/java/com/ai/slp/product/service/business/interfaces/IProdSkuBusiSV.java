package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;

/**
 * 商品SKU业务操作
 * Created by jackieliu on 16/5/12.
 */
public interface IProdSkuBusiSV {

    /**
     * 产生库存组对应商品的SKU
     * 完全使用配置到标准品的销售属性
     *
     * @param group
     */
    public void createSkuOfProduct(StorageGroup group);

    /**
     * 更新商品SKU信息
     * @param saveInfo
     */
    public void updateSkuOfProduct(SkuInfoMultSave saveInfo);

    /**
     * 查询指定商品下的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public SkuSetForProduct querySkuByProdId(String tenantId,String supplierId, String prodId);

    /**
     * 根据SKU标识或SKU属性串查询SKU的信息
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public ProductSKUResponse querySkuDetail(String tenantId,String skuId,String skuAttrs);

    /**
     * 根据SKU标识或SKU属性串查询SKU的所有属性信息
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public ProductSKUConfigResponse querySkuAttr(String tenantId,String skuId,String skuAttrs);
}
