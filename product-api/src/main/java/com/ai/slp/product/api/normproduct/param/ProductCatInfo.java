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
	private long productCatId;
	
	/**
	 * 名称
	 */
	private String productCatName;
	
	/**
	 * 父类目
	 */
	private long parentProductCatId;
	
	/**
	 * 是否叶子节点
	 */
	private String isLeaf;

    public long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(long productCatId) {
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
