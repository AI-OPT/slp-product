package com.ai.slp.product.search.bo.comment;

import com.google.gson.annotations.Expose;

/**
 * 评论图信息
 * Date: 2017年3月26日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class CommentPictrueInfo {

	/**
	 * 评论编码
	 */
	@Expose
	private String commentid;

	/**
	 * 文件附件id
	 */
	@Expose
	private String vfsid;

	/**
	 * 地址
	 */
	@Expose
	private String picaddr;

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getVfsid() {
		return vfsid;
	}

	public void setVfsid(String vfsid) {
		this.vfsid = vfsid;
	}

	public String getPicaddr() {
		return picaddr;
	}

	public void setPicaddr(String picaddr) {
		this.picaddr = picaddr;
	}

}
