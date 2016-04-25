package com.ai.slp.product.api.product.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品管理售中与仓库商品
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductStorageSaleParam extends BaseInfo{
    

    /**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 商品ID
     */
    private String prodId;

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
     * 最高销量
     */
    private long highSaleNum;
    
    /**
     * 最低销量
     */
    private long lowSaleNum;
    
    /**
     * 剩余最大库存量
     */
    private long highStorageNum;
    
    /**
     * 剩余最小库存量
     */
    private long lowStorageNum;
    
    /**
     * 下架状态
     */
    private String downState;

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

    public long getHighSaleNum() {
        return highSaleNum;
    }

    public void setHighSaleNum(long highSaleNum) {
        this.highSaleNum = highSaleNum;
    }

    public long getLowSaleNum() {
        return lowSaleNum;
    }

    public void setLowSaleNum(long lowSaleNum) {
        this.lowSaleNum = lowSaleNum;
    }

    public long getHighStorageNum() {
        return highStorageNum;
    }

    public void setHighStorageNum(long highStorageNum) {
        this.highStorageNum = highStorageNum;
    }

    public long getLowStorageNum() {
        return lowStorageNum;
    }

    public void setLowStorageNum(long lowStorageNum) {
        this.lowStorageNum = lowStorageNum;
    }

    public String getDownState() {
        return downState;
    }

    public void setDownState(String downState) {
        this.downState = downState;
    }
    
}
