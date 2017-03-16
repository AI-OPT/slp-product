package com.ai.slp.product.api.webfront.param;

import java.sql.Timestamp;
import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ProductSKUResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	/**
	 * 销售商(租户)标识
	 */
	private String supplierId;
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
	 * SKU单品的状态<br>
	 * SKU有效时,状态同商品状态 sku无效时,状态为无效
	 */
	private String state;
	/**
	 * 销售商品标识
	 */
	private String prodId;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品卖点
	 */
	private String productSellPoint;

	/**
	 * 充值类型
	 */
	private String rechargeType;

	/**
	 * 有效期类型
	 */
	private String activeType;
	/**
	 * 生效时间
	 */
	private Timestamp activeTime;
	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;
	/**
	 * 有效周期
	 */
	private Short activeCycle;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 商品类目
	 */
	private String productCatId;
	/**
	 * 商品属性集合
	 */
	private List<ProdAttrValue> productAttrList;
	/**
	 * 商品图片
	 */
	private List<ProductImage> productImageList;
	/**
	 * 库存可用量
	 */
	private Long usableNum;
	/**
	 * 销售价,单位:厘
	 */
	private Long salePrice;
	/**
	 * 销量
	 */
	private Long saleNum;
	/**
	 * 评价条数
	 */
	private Long commentNum;
	/**
	 * 商品详情
	 */
	private String proDetailContent;

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

	public String getProductSellPoint() {
		return productSellPoint;
	}

	public void setProductSellPoint(String productSellPoint) {
		this.productSellPoint = productSellPoint;
	}

	public Long getUsableNum() {
		return usableNum;
	}

	public List<ProdAttrValue> getProductAttrList() {
		return productAttrList;
	}

	public void setProductAttrList(List<ProdAttrValue> productAttrList) {
		this.productAttrList = productAttrList;
	}

	public void setUsableNum(Long usableNum) {
		this.usableNum = usableNum;
	}

	public Long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Long salePrice) {
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

	public Short getActiveCycle() {
		return activeCycle;
	}

	public void setActiveCycle(Short activeCycle) {
		this.activeCycle = activeCycle;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Timestamp getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}

	public Timestamp getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getProDetailContent() {
		return proDetailContent;
	}

	public void setProDetailContent(String proDetailContent) {
		this.proDetailContent = proDetailContent;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "ProductSKUResponse{" + "skuId='" + skuId + '\'' + ", skuName='" + skuName + '\'' + ", saleAttrs='"
				+ saleAttrs + '\'' + ", state='" + state + '\'' + ", prodId='" + prodId + '\'' + ", prodName='"
				+ prodName + '\'' + ", productSellPoint='" + productSellPoint + '\'' + ", activeType='" + activeType
				+ '\'' + ", activeTime=" + activeTime + ", inactiveTime=" + inactiveTime + ", activeCycle="
				+ activeCycle + ", unit='" + unit + '\'' + ", productAttrList=" + productAttrList
				+ ", productImageList=" + productImageList + ", usableNum=" + usableNum + ", salePrice=" + salePrice
				+ ", saleNum=" + saleNum + ", commentNum=" + commentNum + ", proDetailContent='" + proDetailContent
				+ '\'' + '}';
	}

	public String getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(String productCatId) {
		this.productCatId = productCatId;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
}
