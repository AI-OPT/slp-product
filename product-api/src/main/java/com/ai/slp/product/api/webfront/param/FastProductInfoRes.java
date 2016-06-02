package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;
import java.util.List;

/**
 * 快充返回信息
 * Created by jackieliu on 16/6/2.
 */
public class FastProductInfoRes implements Serializable {
    private static final long serialVersionUID = 1l;
    /**
     * SkuId
     */
    private String skuId;
    /**
     * 销售价
     */
    private Long salePrice;
    /**
     * 属性集合
     */
    private List<ProductSKUAttr> skuAttrList;

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

    public List<ProductSKUAttr> getSkuAttrList() {
        return skuAttrList;
    }

    public void setSkuAttrList(List<ProductSKUAttr> skuAttrList) {
        this.skuAttrList = skuAttrList;
    }
}
