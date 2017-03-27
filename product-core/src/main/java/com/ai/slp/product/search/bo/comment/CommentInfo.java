package com.ai.slp.product.search.bo.comment;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * 评论信息 Date: 2017年3月26日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class CommentInfo {

	/**
	 * 租户id
	 */
	@Expose
	private String tenantid;
	/**
	 * 评论id
	 */
	@Expose
	private String commentid;

	/**
	 * 商品标识
	 */
	@Expose
	private String prodid;

	/**
	 * 评论人
	 */
	@Expose
	private String userid;

	/**
	 * 评论内容
	 */
	@Expose
	private String commentbody;

	/**
	 * 描述
	 */
	@Expose
	private long shopscorems;
	/**
	 * 服务
	 */
	@Expose
	private long shopscorefw;
	/**
	 * 物流
	 */
	@Expose
	private long shopscorewl;

	/**
	 * 评论时间
	 */
	@Expose
	private long commenttime;

	/**
	 * 评论状态
	 */
	@Expose
	private String state;

	/**
	 * 回复状态
	 */
	@Expose
	private String replaystate;

	/**
	 * 是否传图
	 */
	@Expose
	private String ispicture;

	/**
	 * 评论图片
	 */
	private List<CommentPictrueInfo> commentPictrueInfos;

	public String getCommentid() {
		return commentid;
	}

	public List<CommentPictrueInfo> getCommentPictrueInfos() {
		return commentPictrueInfos;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public void setCommentPictrueInfos(List<CommentPictrueInfo> commentPictrueInfos) {
		this.commentPictrueInfos = commentPictrueInfos;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCommentbody() {
		return commentbody;
	}

	public void setCommentbody(String commentbody) {
		this.commentbody = commentbody;
	}

	public long getShopscorems() {
		return shopscorems;
	}

	public void setShopscorems(long shopscorems) {
		this.shopscorems = shopscorems;
	}

	public long getShopscorefw() {
		return shopscorefw;
	}

	public void setShopscorefw(long shopscorefw) {
		this.shopscorefw = shopscorefw;
	}

	public long getShopscorewl() {
		return shopscorewl;
	}

	public void setShopscorewl(long shopscorewl) {
		this.shopscorewl = shopscorewl;
	}

	public long getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(long commenttime) {
		this.commenttime = commenttime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReplaystate() {
		return replaystate;
	}

	public void setReplaystate(String replaystate) {
		this.replaystate = replaystate;
	}

	public String getIspicture() {
		return ispicture;
	}

	public void setIspicture(String ispicture) {
		this.ispicture = ispicture;
	}

}
