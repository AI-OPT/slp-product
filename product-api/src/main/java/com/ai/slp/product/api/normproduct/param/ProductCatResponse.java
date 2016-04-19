package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 商品类目搜索返回参数<br>
 * 
 * Date: 2016年4月19日<br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author Onle
 */
public class ProductCatResponse extends BaseResponse{

	/**
	 * 商品类目标识： PRODUCT_CAT_ID
	 */
	private Long productCatId;
	
	/**
	 * 名称:PRODUCT_CAT_NAME
	 */
	private String productCatName;
	
	/**
	 * 父类目:PARENT_PRODUCT_CAT_ID
	 */
	private Long parentProductCatId;
	
	/**
	 * 是否叶子节点:IS_LEAF
	 */
	private String isLeaf;

	/**
	 * 状态:STATE
	 */
	private String state;
	
	/**
	 * 操作人：OPER_ID
	 */
	private Long operId;
	
	/**
	 * 操作时间：OPER_TIME
	 */
	private Date operTime;

	public Long getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(Long productCatId) {
		this.productCatId = productCatId;
	}

	public String getProductCatName() {
		return productCatName;
	}

	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	
}
