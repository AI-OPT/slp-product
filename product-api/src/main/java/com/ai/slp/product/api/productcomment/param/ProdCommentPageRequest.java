package com.ai.slp.product.api.productcomment.param;

import com.ai.opt.base.vo.BaseInfo;

public class ProdCommentPageRequest extends BaseInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品评价分数
	 */
	private String commentBody;
	
	/**
	 * 商品SKU id
	 */
	private String skuId;
	

	private Integer pageSize;
	
	private Integer PageNo;
	
	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return PageNo;
	}

	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}
}
