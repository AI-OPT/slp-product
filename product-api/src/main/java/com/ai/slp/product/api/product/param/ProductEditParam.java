package com.ai.slp.product.api.product.param;

/**
 * 商品管理待编辑查询参数
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductEditParam extends ProductBase{
    
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 商品ID
     */
    private String prodId;
    /**
     * 商品类目ID
     */
    private String productCatId;
    /**
     * 商品类型
     */
    private String productType;

    /**
     * 最高销售价
     */
    private long highSalePrice;
    
    /**
     * 最低销售价
     */
    private long lowSalePrice;
    
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public String getProductCatId() {
        return productCatId;
    }
    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public long getHighSalePrice() {
        return highSalePrice;
    }
    public void setHighSalePrice(long highSalePrice) {
        this.highSalePrice = highSalePrice;
    }
    public long getLowSalePrice() {
        return lowSalePrice;
    }
    public void setLowSalePrice(long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }
    
    
}
