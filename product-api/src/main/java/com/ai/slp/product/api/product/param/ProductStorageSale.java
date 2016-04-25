package com.ai.slp.product.api.product.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 商品管理售中与仓库商品返回类
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductStorageSale extends BaseResponse{

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
     * 商品类目名称
     */
    private String productCatName;

    /**
     * 商品类型
     */
    private String productType;
    
    /**
     * 商品图ID
     */
    private long proPictureId;
    
    /**
     * 价格
     */
    private double salePrice;
    
    /**
     * 剩余库存量
     */
    private long storageNum;

    /**
     * 总销量
     */
    private long saleNum;

    /**
     * 下架状态
     */
    private String downState;
    
    /**
     * 下架时间
     */
    private Date downTime;
    
    /**
     * 上架时间
     */
    private Date upTime;
    
    /**
     * 废弃时间
     */
    private Date disTime;

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

    public long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public long getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(long storageNum) {
        this.storageNum = storageNum;
    }

    public long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(long saleNum) {
        this.saleNum = saleNum;
    }

    public String getDownState() {
        return downState;
    }

    public void setDownState(String downState) {
        this.downState = downState;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDisTime() {
        return disTime;
    }

    public void setDisTime(Date disTime) {
        this.disTime = disTime;
    }
    
    
    
}
