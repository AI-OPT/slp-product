package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;

import java.util.List;

/**
 * 类目属性对象<br>
 * 用于类目,标准品,商品的属性
 *
 * Date: 2016年5月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProdCatAttrDef extends BaseInfo {
    private static final long serialVersionUID = 1L;
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
     * 值输入方式<br>
     * 1:下拉单选
     * 2:多选
     * 3:可输入文本框（单行）
     * 4:可输入文本框（多行）
     * 5:日期时间
     * 6:日期时间段
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
     * 是否已关联标准品
     */
    private Boolean hasProduct;
    /**
     * 是否必填
     */
    private String isNecessary;
    /**
     * 序列号
     */
    private Short serialNumber;

    /**
     * 对应属性值
     */
    private List<AttrValInfo> attrValList;

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

    public Boolean getHasProduct() {
        return hasProduct;
    }

    public void setHasProduct(Boolean hasProduct) {
        this.hasProduct = hasProduct;
    }

    public List<AttrValInfo> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<AttrValInfo> attrValList) {
        this.attrValList = attrValList;
    }
}
