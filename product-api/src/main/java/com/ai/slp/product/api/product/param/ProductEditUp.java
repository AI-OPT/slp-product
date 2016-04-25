package com.ai.slp.product.api.product.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseResponse;
/**
 * 商品编辑上架返回类
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductEditUp extends BaseResponse{
    
    /**
     * 商品ID
     */
    private String prodId;
    
    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 商品类目名称
     */
    private String productCatName;
    
    /**
     * 商品类型
     */
    private String productType;
    
    /**
     * 商品图ID
     */
    private long proPictureId;
    
    /**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 价格
     */
    private double salePrice;
    
    /**
     * 总库存
     */
    private long totalNum;
    
    /**
     * 状态
     * 1未编辑2已编辑3审核中4审核未通过
     * 5在售6仓库中（审核通过放入）61售罄下架62自动下架7停用8废弃
     */
    private String state;
    
    /**
     * 生成时间-待编辑
     */
    private Date creatTime;
    
    /**
     * 拒绝时间-被拒绝
     */
    private Date refuseTime;
    
    /**
     * 提交时间-审核中即提交审核的时间
     */
    private Date checkTime;
    
    /**
     * 申请优先
     */
    private String precedence;
    
    /**
     * 拒绝原因-被拒绝参数
     */
    private String refuseReason;
    
    /**
     * 拒绝描述-被拒绝参数
     */
    private String refuseDes;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(Date refuseTime) {
        this.refuseTime = refuseTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getPrecedence() {
        return precedence;
    }

    public void setPrecedence(String precedence) {
        this.precedence = precedence;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseDes() {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes) {
        this.refuseDes = refuseDes;
    }
    
    
}
