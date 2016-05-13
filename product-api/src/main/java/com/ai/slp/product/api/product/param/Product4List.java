package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商城商品列表中对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class Product4List extends BaseInfo{
    private static final long serialVersionUID = 1L;
	/**
     * 类目标识
     */
    private String productCatId;
    /**
     * 类目名称
     */
    private String catName;
    /**
     * 商城商品标识
     */
    private String prodId;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 商品类型
     * 0:全部
     */
    private String prodType;
    /**
     * 库存组id
     */
    private String storageGroupId;
    /**
     * 库存组名称
     */
    private String storageGroupName;
    /**
     *标准品标识
     */
    private Long normProdId;
    /**
     * 标准品名称
     */
    private String normProdName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName;
    }

    public Long getNormProdId() {
        return normProdId;
    }

    public void setNormProdId(Long normProdId) {
        this.normProdId = normProdId;
    }

    public String getNormProdName() {
        return normProdName;
    }

    public void setNormProdName(String normProdName) {
        this.normProdName = normProdName;
    }

	public String getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(String productCatId) {
		this.productCatId = productCatId;
	}
}
