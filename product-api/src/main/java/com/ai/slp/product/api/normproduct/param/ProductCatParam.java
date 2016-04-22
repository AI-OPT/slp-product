package com.ai.slp.product.api.normproduct.param;

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
                    IProductCatSV.QsueryProductCatAttr.class })
	private String productCatId;

	/**
	 * 商品类目名称
	 */
	private String productCatName;
	
	/**
	 * 父类目
	 */
	private long parentProductCatId;
	
	/**
	 * 是否叶子节点:Y是N否
	 */
	private String isLeaf;

	/**
	 * 首字母
	 */
	private String firstLetter;
	
	/**
	 * 类目的级别-标识是几级类目
	 */
	private long catLevel;
	
	/**
	 * 序列号-用于移动排序
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

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }


}
