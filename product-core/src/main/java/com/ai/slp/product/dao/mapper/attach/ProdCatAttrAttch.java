package com.ai.slp.product.dao.mapper.attach;

/**
 * 类目属性扩展
 * Created by jackieliu on 16/5/2.
 */
public class ProdCatAttrAttch {
    /**
     * 租户Id，必填
     */
    private String tenantId;
    /**
     * 类目与属性关系标识
     */
    private String catAttrId;
    /**
     * 商品类目标识
     */
    private String productCatId;
    /**
     * 属性ID
     */
    private long attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     *属性名称首字母大写
     */
    private String firstLetter;
    /**
     * 状态
     */
    private String state;

    /**
     * 值输入方式
     */
    private String valueWay;
    /**
     * 属性类型,包括关键属性 非关键属性 销售属性
     */
    private String attrType;
    /**
     * 是否上传图片,销售属性类型为必填
     */
    private String isPicture;
    /**
     * 是否必填
     */
    private String isNecessary;
    /**
     * 序列号
     */
    private Short serialNumber;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCatAttrId() {
        return catAttrId;
    }

    public void setCatAttrId(String catAttrId) {
        this.catAttrId = catAttrId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getValueWay() {
        return valueWay;
    }

    public void setValueWay(String valueWay) {
        this.valueWay = valueWay;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(String isPicture) {
        this.isPicture = isPicture;
    }

    public String getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(String isNecessary) {
        this.isNecessary = isNecessary;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
