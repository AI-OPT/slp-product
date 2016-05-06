package com.ai.slp.product.api.storage.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseResponse;

public class ProProdAttr extends BaseResponse{
    
    /**
     * 库存组ID
     */
    private String storageGroupId;
    
    /**
     * 库存组名称
     */
    private String storageGroupName;
    /**
     * 总库存量
     */
    private long totalNum;
    /**
     * 商城商品名称
     */
    private String prodName;
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 有效期
     */
    private Date activeTime;
    /**
     * 属性类型
     */
    private String attrType;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 详细地址
     */
    private String address;
    
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
    public long getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public Date getActiveTime() {
        return activeTime;
    }
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
    public String getAttrType() {
        return attrType;
    }
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }
    public String getAttrName() {
        return attrName;
    }
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    public String getAttrValueName() {
        return attrValueName;
    }
    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
