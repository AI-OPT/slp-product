package com.ai.slp.product.api.productcomment.param;

import java.sql.Timestamp;
import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class ProdCommentPageResponse extends BaseInfo{

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

	public String getReplyState() {
		return replyState;
	}

	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}

	public List<PictureVO> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PictureVO> pictureList) {
		this.pictureList = pictureList;
	}

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
	 * 评价人
	 */
	private String userId;
	
	/**
	 * 评价时间
	 */
	private Timestamp commentTime;
	
    /**
     * 是否有回复
     */
    private String replyState;
    
    /**
     * 图片List
     */
    private List<PictureVO> pictureList;
    
}
