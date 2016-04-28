package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 商品类目返回参数<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCatInfo extends BaseResponse{

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
	private long parentProductCatId;
	
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
    private long catLevel;
    /**
     * 序列号-用于排序
     */
    private long serialNumber;
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
    public long getParentProductCatId() {
        return parentProductCatId;
    }
    public void setParentProductCatId(long parentProductCatId) {
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
    public long getCatLevel() {
        return catLevel;
    }
    public void setCatLevel(long catLevel) {
        this.catLevel = catLevel;
    }
    public long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }
	

	
}
