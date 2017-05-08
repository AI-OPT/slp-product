package com.ai.slp.product.api.storageserver.param;

import java.sql.Timestamp;
import java.util.Map;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 库存量变化操作结果 <br>
 *
 * Date: 2016年5月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author liutong5
 */
public class StorageNumRes extends BaseResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 单品SKU标识
	 */
	private String skuId;
	/**
	 * SKU名称
	 */
	private String skuName;
	/**
	 * 标准品标识
	 */
	private String standedProdId;
	/**
	 * 销售商品标识
	 */
	private String prodId;
	/**
	 * 商品类目标识
	 */
	private String productCatId;
	/**
	 * 销售价,单位:厘
	 */
	private long salePrice;
	/**
	 * 有效期类型<br>
	 * 1固定有效期【生效时间、失效时间必填】<br>
	 * 2灵活有效期（购买后一定时间有效）【有效周期、单位必填】
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
	 * 周期,D:天;M:月;Y:年
	 */
	private String unit;
	/**
	 * 运营商标识<br>
	 * 10：中国移动; 11：中国电信; 12：中国联通
	 */
	private String basicOrgId;
	/**
	 * 是否提供发票<br>
	 * Y:是;N:否
	 */
	private String isInvoice;
	/**
	 * 上架类型<br>
	 * 1审核通过后立即上架 2审核通过后放入仓库 4预售
	 */
	private String upshelfType;
	/**
	 * 使用数量库存组成,K:SKU库存标识;V:库存提供量
	 */
	private Map<String, Integer> storageNum;

	/**
	 * 图片类型
	 */
	private String imagetype;
	/**
	 * 文件附件模块ID
	 */
	private String vfsid;
	
	/**
	 * 库存ID
	 */
	private String storageGroupId;

	public String getSkuId() {
		return skuId;
	}

	public String getStorageGroupId() {
		return storageGroupId;
	}



	public void setStorageGroupId(String storageGroupId) {
		this.storageGroupId = storageGroupId;
	}



	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public String getVfsid() {
		return vfsid;
	}

	public void setVfsid(String vfsid) {
		this.vfsid = vfsid;
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

	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
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

	public Map<String, Integer> getStorageNum() {
		return storageNum;
	}

	public void setStorageNum(Map<String, Integer> storageNum) {
		this.storageNum = storageNum;
	}

	public String getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(String productCatId) {
		this.productCatId = productCatId;
	}

	public String getBasicOrgId() {
		return basicOrgId;
	}

	public void setBasicOrgId(String basicOrgId) {
		this.basicOrgId = basicOrgId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getUpshelfType() {
		return upshelfType;
	}

	public void setUpshelfType(String upshelfType) {
		this.upshelfType = upshelfType;
	}
}
