package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.sun.javafx.beans.annotations.NonNull;

import javax.validation.constraints.NotNull;

/**
 * 商城商品查询对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductListQuery extends BaseInfo {
    /**
     * 类目标识
     */
    @NotNull(message = "类目标识不能为空",groups = {IProductSV.QueryProductList.class})
    private String catId;
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
    @NotNull(message = "类目标识不能为空",groups = {IProductSV.QueryProductList.class})
    private String prodType;
    /**
     * 库存组id
     */
    private Long storageGroupId;
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

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
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

    public Long getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(Long storageGroupId) {
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
}
