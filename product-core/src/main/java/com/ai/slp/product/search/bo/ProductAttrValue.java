package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

public class ProductAttrValue {

	/**
	 * 商品属性值标识<br>
	 * 值输入方式为1下拉单选、2多选时存在
	 */
	@Expose
	private String attrvaluedefid;
	/**
	 * 属性值名称
	 */
	@Expose
	private String attrvaluename;

	/**
	 * 属性值名称2
	 */
	@Expose
	private String attrvaluename2;
	/**
	 * 是否自有属性
	 */
	@Expose
	private boolean isown;
	/**
	 * 上传后的图片ID
	 */
	@Expose
	private String vfsid;

	/**
	 * 图片扩展名
	 */
	@Expose
	private String pictype;
	/**
	 * 图片url,由调用方组装
	 */
	@Expose
	private String imageurl;

	public String getAttrvaluedefid() {
		return attrvaluedefid;
	}

	public void setAttrvaluedefid(String attrvaluedefid) {
		this.attrvaluedefid = attrvaluedefid;
	}

	public String getAttrvaluename() {
		return attrvaluename;
	}

	public void setAttrvaluename(String attrvaluename) {
		this.attrvaluename = attrvaluename;
	}

	public String getAttrvaluename2() {
		return attrvaluename2;
	}

	public void setAttrvaluename2(String attrvaluename2) {
		this.attrvaluename2 = attrvaluename2;
	}

	public boolean isIsown() {
		return isown;
	}

	public void setIsown(boolean isown) {
		this.isown = isown;
	}

	public String getVfsid() {
		return vfsid;
	}

	public void setVfsid(String vfsid) {
		this.vfsid = vfsid;
	}

	public String getPictype() {
		return pictype;
	}

	public void setPictype(String pictype) {
		this.pictype = pictype;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

}
