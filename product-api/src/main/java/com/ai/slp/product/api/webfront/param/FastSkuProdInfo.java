package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;

/**
 * Created by jackieliu on 16/6/3.
 */
public class FastSkuProdInfo implements Serializable {
    private static final long serialVersionUID = 1l;
    /**
     * SkuId
     */
    private String skuId;
    /**
     * 销售价
     */
    private Long salePrice;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
