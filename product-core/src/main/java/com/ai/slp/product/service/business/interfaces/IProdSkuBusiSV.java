package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;

/**
 * 商品SKU业务操作
 * Created by jackieliu on 16/5/12.
 */
public interface IProdSkuBusiSV {

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
    public SkuSetForProduct querySkuByProdId(String tenantId, String prodId);
}
