package com.ai.slp.product.api.webfront.param;

import java.util.Date;
import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ProductSKUResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	/**
     * sku单品标识
     */
	private String skuId;
	/**
     * sku名称
     */
    private String skuName;
    /**
     * 属性串
     */
    private String saleAttrs;
    /**
     * SKU单品的状态
     */
    private String state;
    /**
     * 销售商品标识
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品卖点
     */
    private String productSellPoint;
    /**
     * 有效期类型
     */
    private String activeType;
    /**
     * 生效时间
     */
    private Date activeTime;
    /**
     * 失效时间
     */
    private Date inactiveTime;
    /**
     * 有效周期
     */
    private Integer activeCycle;
    /**
     * 单位
     */
    private String unit;
    /**
     * 商品属性集合
     */
    private List<ProductSKUAttr> productAttrList;
    /**
     * 商品图片
     */
    private List<ProductImage> productImageList;
    /**
     * 库存可用量
     */
    private Long usableNum;
    /**
     * 销售价
     */
    private Double salePrice;
    /**
     * 销量
     */
    private Long saleNum;
    /**
     * 评价条数
     */
    private Long commentNum;
    
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSaleAttrs() {
		return saleAttrs;
	}
	public void setSaleAttrs(String saleAttrs) {
		this.saleAttrs = saleAttrs;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getProductSellPoint() {
		return productSellPoint;
	}
	public void setProductSellPoint(String productSellPoint) {
		this.productSellPoint = productSellPoint;
	}
	public List<ProductSKUAttr> getProductAttrList() {
		return productAttrList;
	}
	public void setProductAttrList(List<ProductSKUAttr> productAttrList) {
		this.productAttrList = productAttrList;
	}
	public Long getUsableNum() {
		return usableNum;
	}
	public void setUsableNum(Long usableNum) {
		this.usableNum = usableNum;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Long getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}
	public Long getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}
	public List<ProductImage> getProductImageList() {
		return productImageList;
	}
	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}
	public String getActiveType() {
		return activeType;
	}
	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	public Date getInactiveTime() {
		return inactiveTime;
	}
	public void setInactiveTime(Date inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	public Integer getActiveCycle() {
		return activeCycle;
	}
	public void setActiveCycle(Integer activeCycle) {
		this.activeCycle = activeCycle;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
