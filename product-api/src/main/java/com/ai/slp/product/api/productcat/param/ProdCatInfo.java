package com.ai.slp.product.api.productcat.param;

import java.io.Serializable;

/**
 * Created by jackieliu on 16/6/16.
 */
public class ProdCatInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品类目标识
     */
    private String productCatId;

    /**
     * 名称
     */
    private String productCatName;

    /**
     * 父类目
     */
    private String parentProductCatId;

    /**
     * 是否有子分类
     */
    private String isChild;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 类目的级别-用于判断是类目(一级)还是子类目
     */
    private short catLevel;
    /**
     * 序列号-用于排序
     */
    private short serialNumber;

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

    public String getParentProductCatId() {
        return parentProductCatId;
    }

    public void setParentProductCatId(String parentProductCatId) {
        this.parentProductCatId = parentProductCatId;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public short getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(short catLevel) {
        this.catLevel = catLevel;
    }

    public short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
