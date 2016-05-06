package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商城商品虚拟库存管理参数
 * 
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProProductParam extends BaseInfo{
    
    /**
     * 商城商品ID
     */
    private String prodId;
    
    /**
     * 商城商品名称
     */
    private String prodName;
    
    /**
     * 库存组ID
     */
    private String storageGroupId;
    
    /**
     * 库存组名称
     */
    private String storageGroupName;
    
    /**
     * 标准品ID
     */
    private String standedProdId;
    
    /**
     * 标准品名称
     */
    private String standedProductName;
    
    /**
     * 商品类目ID
     */
    private long productCatId;

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

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStandedProductName() {
        return standedProductName;
    }

    public void setStandedProductName(String standedProductName) {
        this.standedProductName = standedProductName;
    }

    public long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(long productCatId) {
        this.productCatId = productCatId;
    }
    
    
}
