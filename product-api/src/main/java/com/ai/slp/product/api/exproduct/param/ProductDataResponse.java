package com.ai.slp.product.api.exproduct.param;

import java.io.Serializable;
import java.util.List;

public class ProductDataResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * sku名称
	 */
	private String skuName;

	/**
	 * sku单品标识
	 */
	private String skuId;
	/**
	 * 受众用户ID
	 */
	private String prodRangeType;
	/**
	 * 有效期类型，1固定有效期；2灵活有效期
	 */
	private String activeType;
	/**
	 * 生效时间
	 */
	private String activeTime;
	/**
	 * 失效时间
	 */
	private String inactiveTime;
	/**
	 * 有效周期
	 */
	private String activeCycle;
	/**
	 * 有效周期单位
	 */
	private String activeUnit;

	/**
	 * 销售价格
	 */
	private float salePrice;
	/**
	 * 代理商
	 */
	private String basicOrgId;
	/**
	 * 是否全国销售
	 */
	private String saleNationWide;
	/**
	 * 销售地域列表
	 */
	private List<SaleArea> saleAreaInfos;
	/**
	 * 属性列表
	 */
	private List<ProductAttrDef> attrList;
	/**
	 * 属性值列表
	 */
	private List<ProductAttrValueDef> attrValueList;

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getProdRangeType() {
		return prodRangeType;
	}

	public void setProdRangeType(String prodRangeType) {
		this.prodRangeType = prodRangeType;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(String inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getActiveCycle() {
		return activeCycle;
	}

	public void setActiveCycle(String activeCycle) {
		this.activeCycle = activeCycle;
	}

	public String getActiveUnit() {
		return activeUnit;
	}

	public void setActiveUnit(String activeUnit) {
		this.activeUnit = activeUnit;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public String getBasicOrgId() {
		return basicOrgId;
	}

	public void setBasicOrgId(String basicOrgId) {
		this.basicOrgId = basicOrgId;
	}

	public String getSaleNationWide() {
		return saleNationWide;
	}

	public void setSaleNationWide(String saleNationWide) {
		this.saleNationWide = saleNationWide;
	}

	public List<SaleArea> getSaleAreaInfos() {
		return saleAreaInfos;
	}

	public void setSaleAreaInfos(List<SaleArea> saleAreaInfos) {
		this.saleAreaInfos = saleAreaInfos;
	}

	public List<ProductAttrDef> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<ProductAttrDef> attrList) {
		this.attrList = attrList;
	}

	public List<ProductAttrValueDef> getAttrValueList() {
		return attrValueList;
	}

	public void setAttrValueList(List<ProductAttrValueDef> attrValueList) {
		this.attrValueList = attrValueList;
	}

}
