package com.ai.slp.product.api.flushdata.params;

import java.io.Serializable;

public class CreateCommentRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 需要商品评论数量
	 */
	private Integer number;

	private String commentIdStartNum;
	/**
	 * 商品ID起始
	 */
	private String productIdStartNum;
	/**
	 * 商品ID结束
	 */
	private String productIdEndNum;

	/**
	 * 商品评论
	 */
	private String commentContent;

	public Integer getNumber() {
		return number;
	}

	public String getCommentIdStartNum() {
		return commentIdStartNum;
	}

	public void setCommentIdStartNum(String commentIdStartNum) {
		this.commentIdStartNum = commentIdStartNum;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getProductIdStartNum() {
		return productIdStartNum;
	}

	public void setProductIdStartNum(String productIdStartNum) {
		this.productIdStartNum = productIdStartNum;
	}

	public String getProductIdEndNum() {
		return productIdEndNum;
	}

	public void setProductIdEndNum(String productIdEndNum) {
		this.productIdEndNum = productIdEndNum;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

}
