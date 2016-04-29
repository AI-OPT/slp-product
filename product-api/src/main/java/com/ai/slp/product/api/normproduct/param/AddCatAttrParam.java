package com.ai.slp.product.api.normproduct.param;

/**
 * 增加类目的类目属性参数
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AddCatAttrParam extends ProductCatBase{

    /**
     * 属性ID
     */
    private long attrId;
    
    /**
     * 租户ID
     */
    private String tenantId;
    
    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 是否上传图片
     */
    private String isPicture;
    
    /**
     * 序列号-用于排序
     */
    private long serialNumber;

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(String isPicture) {
        this.isPicture = isPicture;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    
}
