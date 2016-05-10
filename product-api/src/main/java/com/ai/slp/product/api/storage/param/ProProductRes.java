package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseResponse;

public class ProProductRes extends BaseResponse{

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
    
    /**
     * 所属类目名称
     */
    private String productCatName;
    
    /**
     * 商品类型
     */
    private String productType;
    
    /**
     * 库存量
     */
    private long totalNum;

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

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
    
    
}
