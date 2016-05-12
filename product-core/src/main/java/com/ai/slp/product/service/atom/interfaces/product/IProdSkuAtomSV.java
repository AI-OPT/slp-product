package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSku;

import java.util.List;

/**
 * 商品SKU信息
 * Created by jackieliu on 16/5/6.
 */
public interface IProdSkuAtomSV {
    /**
     * 查询商品的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public List<ProdSku> querySkuOfProd(String tenantId,String prodId);

    /**
     * 废弃指定SKU单品
     * @return
     */
    public int updateSkuById(ProdSku prodSku);

    /**
     * 添加商品SKU信息
     * @param prodSku
     * @return
     */
    public int createObj(ProdSku prodSku);
}
