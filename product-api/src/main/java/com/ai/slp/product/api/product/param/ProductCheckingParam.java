package com.ai.slp.product.api.product.param;

import java.sql.Date;

/**
 * 商品管理审核中查询参数
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCheckingParam extends ProductBase {
    
    /**
     * 上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架
     */
    private String upshelfType;
    
    /**
     * 上架时间
     */
    private Date upTime;
    
    /**
     * 下架时间
     */
    private Date downTime;

    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 商品类目名称
     */
    private String productCatName;
    
    /**
     * 商品类型
     */
    private String productType;

    /**
     * 最高销售价
     */
    private double highSalePrice;
    
    /**
     * 最低销售价
     */
    private double lowSalePrice;
    
    /**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 商品ID
     */
    private String prodId;

    public String getUpshelfType() {
        return upshelfType;
    }

    public void setUpshelfType(String upshelfType) {
        this.upshelfType = upshelfType;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getHighSalePrice() {
        return highSalePrice;
    }

    public void setHighSalePrice(double highSalePrice) {
        this.highSalePrice = highSalePrice;
    }

    public double getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(double lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

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
    
    
}
