package com.ai.slp.product.search.bo;

import java.sql.Timestamp;
import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
import com.google.gson.annotations.Expose;

public class ProductDetail extends BaseResponse {

	private static final long serialVersionUID = 1L;
	/**
	 * 销售商(租户)标识
	 */
	@Expose
	private String supplierid;
	/**
	 * sku单品标识
	 */
	@Expose
	private String skuid;
	/**
	 * sku名称
	 */
	@Expose
	private String skuname;
	/**
	 * 属性串
	 */
	@Expose
	private String saleattrs;
	/**
	 * SKU单品的状态<br>
	 * SKU有效时,状态同商品状态 sku无效时,状态为无效
	 */
	@Expose
	private String state;
	/**
	 * 销售商品标识
	 */
	@Expose
	private String prodId;
	/**
	 * 商品名称
	 */
	@Expose
	private String prodname;
	/**
	 * 商品卖点
	 */
	@Expose
	private String productsellpoint;

	/**
	 * 充值类型
	 */
	@Expose
	private String rechargetype;

	/**
	 * 有效期类型
	 */
	@Expose
	private String activetype;
	/**
	 * 生效时间
	 */
	@Expose
	private Timestamp activetime;
	/**
	 * 失效时间
	 */
	private Timestamp inactivetime;
	/**
	 * 有效周期
	 */
	@Expose
	private Short activecycle;
	/**
	 * 单位
	 */
	@Expose
	private String unit;
	/**
	 * 商品类目
	 */
	@Expose
	private String productcatId;
	/**
	 * 商品属性集合
	 */
	@Expose
	private List<ProductAttrValue> productattrlist;
	/**
	 * 商品图片
	 */
	@Expose
	private List<ImageInfo> productimagelist;
	/**
	 * 库存可用量
	 */
	@Expose
	private Long usablenum;
	/**
	 * 销售价,单位:厘
	 */
	@Expose
	private Long saleprice;
	/**
	 * 销量
	 */
	@Expose
	private Long salenum;
	/**
	 * 评价条数
	 */
	@Expose
	private Long commentnum;
	/**
	 * 商品详情
	 */
	@Expose
	private String prodetailcontent;

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public String getSkuname() {
		return skuname;
	}

	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}

	public String getSaleattrs() {
		return saleattrs;
	}

	public void setSaleattrs(String saleattrs) {
		this.saleattrs = saleattrs;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProductsellpoint() {
		return productsellpoint;
	}

	public void setProductsellpoint(String productsellpoint) {
		this.productsellpoint = productsellpoint;
	}

	public String getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(String rechargetype) {
		this.rechargetype = rechargetype;
	}

	public String getActivetype() {
		return activetype;
	}

	public void setActivetype(String activetype) {
		this.activetype = activetype;
	}

	public Timestamp getActivetime() {
		return activetime;
	}

	public void setActivetime(Timestamp activetime) {
		this.activetime = activetime;
	}

	public Timestamp getInactivetime() {
		return inactivetime;
	}

	public void setInactivetime(Timestamp inactivetime) {
		this.inactivetime = inactivetime;
	}

	public Short getActivecycle() {
		return activecycle;
	}

	public void setActivecycle(Short activecycle) {
		this.activecycle = activecycle;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductcatId() {
		return productcatId;
	}

	public void setProductcatId(String productcatId) {
		this.productcatId = productcatId;
	}

	public List<ProductAttrValue> getProductattrlist() {
		return productattrlist;
	}

	public void setProductattrlist(List<ProductAttrValue> productattrlist) {
		this.productattrlist = productattrlist;
	}

	public List<ImageInfo> getProductimagelist() {
		return productimagelist;
	}

	public void setProductimagelist(List<ImageInfo> productimagelist) {
		this.productimagelist = productimagelist;
	}

	public Long getUsablenum() {
		return usablenum;
	}

	public void setUsablenum(Long usablenum) {
		this.usablenum = usablenum;
	}

	public Long getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Long saleprice) {
		this.saleprice = saleprice;
	}

	public Long getSalenum() {
		return salenum;
	}

	public void setSalenum(Long salenum) {
		this.salenum = salenum;
	}

	public Long getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(Long commentnum) {
		this.commentnum = commentnum;
	}

	public String getProdetailcontent() {
		return prodetailcontent;
	}

	public void setProdetailcontent(String prodetailcontent) {
		this.prodetailcontent = prodetailcontent;
	}

}
