package com.ai.slp.product.api.productcomment.param;

import com.ai.opt.base.vo.BaseInfo;

public class ProdReplyComment extends BaseInfo{

	/**
	 * 商品评价回复入参
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 销售商ID
	 */
	private String supplierId;
	/**
	 * 评价类型
	 * 1评价 2追评
	 * 
	 */
	private String commentType;
	/**
	 * 评价/追评ID
	 */
	private String commentId;
	/**
	 * 回复人类型 U：商户S：员工
	 */
	private String replierType;
	/**
	 * 回复人
	 */
	private String replierId;
	/**
	 * 回复内容
	 */
	private String replyComment;
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getReplierType() {
		return replierType;
	}
	public void setReplierType(String replierType) {
		this.replierType = replierType;
	}
	public String getReplierId() {
		return replierId;
	}
	public void setReplierId(String replierId) {
		this.replierId = replierId;
	}
	public String getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}
	
}
