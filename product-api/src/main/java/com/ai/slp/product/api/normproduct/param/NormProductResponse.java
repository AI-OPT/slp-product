package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.Date;

/**
 * 标准品查询返回信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductResponse extends BaseResponse {

    /**
     * 类目ID
     */
    private Long catId;

    /**
     * 标准品ID
     */
    private String productId;

    /**
     * 标准品名称
     */
    private String productName;

    /**
     * 标准品状态
     * 0:全部;1可上架;2不可上架;3待处理;4废弃
     */
    private String productStatus;

    /**
     * 标准品类型
     * 0:全部;1实物;2虚拟
     */
    private String productType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createUserId;
    /**
     * 类目名称
     */
    private String catName;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 创建者名称
     */
    private String createUserName;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
