package com.ai.slp.product.api.productcat.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;

import java.sql.Timestamp;

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
	 * 商品类目ID,更新时必填
	 */
    @NotNull(message = "类目ID不能为空", 
            groups = { IProductCatSV.DeleteProductCat.class,
                    IProductCatSV.UpdateProductCat.class })
	private String productCatId;

	/**
	 * 商品类目名称
	 */
    @NotNull(message = "名称不能为空", 
            groups = { IProductCatSV.AddProductCat.class,
                    IProductCatSV.UpdateProductCat.class})
	private String productCatName;

	/**
	 * 父类目
	 */
	private String parentProductCatId;

    /**
     * 是否有子分类,必填
     */
	@NotNull(message = "请确定是否有子分类",
            groups = { IProductCatSV.AddProductCat.class,
                    IProductCatSV.UpdateProductCat.class})
    private String isChild;

	/**
	 * 首字母
	 */
	private String firstLetter;

    /**
     * 序列号-用于排序
     */
    @Min(value = 0,message = "排序号不能小于0",groups = {
            IProductCatSV.AddProductCat.class,IProductCatSV.UpdateProductCat.class})
    @Max(value = 10000,message = "排序号不能大于10000",groups = {
            IProductCatSV.AddProductCat.class,IProductCatSV.UpdateProductCat.class})
    private short serialNumber;

    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空",
            groups = { IProductCatSV.AddProductCat.class,
                    IProductCatSV.UpdateProductCat.class})
    private long operId;

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
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

    public short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
