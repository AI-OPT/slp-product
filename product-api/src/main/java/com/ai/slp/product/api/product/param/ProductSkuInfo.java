package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 销售商品下SKU单品的信息
 * Created by jackieliu on 16/5/20.
 */
public class ProductSkuInfo extends BaseResponse {
    /**
     * sku单品标识
     */
    private String skuId;
    /**
     * 销售商品标识
     */
    private String prodId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 属性串
     */
    private String saleAttrs;
    /**
     * SKU单品的状态
     */
    private String state;
    /**
     * 库存量
     */
    private long totalNum;
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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSaleAttrs() {
        return saleAttrs;
    }

    public void setSaleAttrs(String saleAttrs) {
        this.saleAttrs = saleAttrs;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
