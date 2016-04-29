package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

/**
 * 商品类目请求参数<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCatParam extends BaseInfo {
	
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
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize =20;
	
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
    private long serialNumber;

    /**
     * 状态
     * 1有效0无效
     */
    private String state;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
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
