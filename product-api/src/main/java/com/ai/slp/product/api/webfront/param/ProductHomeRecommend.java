package com.ai.slp.product.api.webfront.param;

public class ProductHomeRecommend {

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * SKU标识
     */
    private String skuId;

    /**
     * SKU名称
     */
    private String skuName;

    /**
     * 商品卖点
     */
    private String productSellPoint;

    /**
     * 销售价
     */
    private Long salePrice;

    /**
     * 商品图
     */
    private ProductImage productImage;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

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

    public String getProductSellPoint() {
        return productSellPoint;
    }

    public void setProductSellPoint(String productSellPoint) {
        this.productSellPoint = productSellPoint;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

}
