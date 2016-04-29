package com.ai.slp.product.api.normproduct.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

/**
 * 商品类目请求参数<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCatParam extends ProductCatBase {
	
	/**
	 * 商品类目ID
	 */
    @NotNull(message = "类目ID不能为空", 
            groups = { IProductCatSV.DeleteProductCat.class,
                    IProductCatSV.UpdateProductCat.class })
	private String productCatId;

	/**
	 * 商品类目名称
	 */
    @NotNull(message = "名称不能为空", 
            groups = { IProductCatSV.AddProductCat.class})
	private String productCatName;
	
	/**
	 * 父类目
	 */
	private long parentProductCatId;

    /**
     * 是否有子分类
     */
	@NotNull(message = "名称不能为空", 
            groups = { IProductCatSV.AddProductCat.class})
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
    @Min(value = 0,message = "排序号不能小于0",groups = {IProductCatSV.AddProductCat.class})
    @Max(value = 10000,message = "排序号不能大于10000",groups = {IProductCatSV.AddProductCat.class})
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
