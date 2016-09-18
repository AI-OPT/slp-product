package com.ai.slp.product.api.productcomment.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class CommentPageResponse extends BaseInfo{

	private static final long serialVersionUID = 1L;

	/**
	 * 评价ID
	 */
	private String commentId;
	
	/**
	 * 评价内容
	 */
	private String commentBody;
	
	/**
	 * 商品评价分数
	 */
	private Long shopScoreMs;

	/**
	 * 物流评价分数
	 */
    private Long shopScoreFw;

    /**
     * 服务评价分数
     */
    private Long shopScoreWl;
    
    /**
     * 订单号
     */
    private String orderId;
    
    /**
     * 商品ID
     */
    private String standedProdId;
    
    /**
     * 商品名称
     */
    private String prodName;
	
	/**
	 * 评价人
	 */
	private String userId;
	
	/**
	 * 评价时间
	 */
	private Timestamp commentTime;
	
	/**
	 * 评价状态
	 */
	private String state;
	
    /**
     * 是否有图片
     */
	private String isPicture;
	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public Long getShopScoreMs() {
		return shopScoreMs;
	}

	public void setShopScoreMs(Long shopScoreMs) {
		this.shopScoreMs = shopScoreMs;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public Long getShopScoreFw() {
		return shopScoreFw;
	}

	public void setShopScoreFw(Long shopScoreFw) {
		this.shopScoreFw = shopScoreFw;
	}

	public Long getShopScoreWl() {
		return shopScoreWl;
	}

	public void setShopScoreWl(Long shopScoreWl) {
		this.shopScoreWl = shopScoreWl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsPicture() {
		return isPicture;
	}

	public void setIsPicture(String isPicture) {
		this.isPicture = isPicture;
	}

	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}
}
