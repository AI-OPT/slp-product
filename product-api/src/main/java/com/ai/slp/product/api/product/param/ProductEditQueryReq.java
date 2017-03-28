package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 商品管理待编辑查询参数
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductEditQueryReq extends BaseInfo {
	private static final long serialVersionUID = 1L;

	/**
	 * 请求查询的页码 默认为1
	 */
	private Integer pageNo = 1;

	/**
	 * 每页显示条数,默认每页30条
	 */
	private Integer pageSize = 30;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品ID
	 */
	private String prodId;
	/**
	 * 标准品ID
	 * 
	 */
	private String standedProdId;
	/**
	 * 商品类目ID,必填
	 */
	private String productCatId;
	/**
	 * 商品类型<br>
	 * null:全部、1实物、2虚拟
	 */
	private String productType;

	/**
	 * 状态集合 0:新增 1:未编辑;2:已编辑 3:审核中;4:审核未通过 5:在售 6:仓库中（审核通过放入） 61:售罄下架 62:废弃下架
	 * 7:废弃
	 */

	private List<String> stateList;

	/**
	 * 商户标识,必填 -1:自营 0:全部
	 */
	@NotBlank(message = "商户标识不能为空")
	private String supplierId;

	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<String> getStateList() {
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
