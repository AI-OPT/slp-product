package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseResponse;

public class ProductHomeResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * sku单品标识
     */
    private String skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 商品ID
     */
    private String prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 商品卖点
     */
    private String productSellPoint;

    /**
     * 销售价
     */
    private float salePrice;

    /**
     * 商品图
     */
    private ProductImage productImage;

   

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductSellPoint() {
        return productSellPoint;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public void setProductSellPoint(String productSellPoint) {
        this.productSellPoint = productSellPoint;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }


}
