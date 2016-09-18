package com.ai.slp.product.api.productcomment.param;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

/**
 * 商品评论查询入参对象
 *
 * Date: 2016年9月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author jiaxs
 */
public class CommentPageRequest extends BaseInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 销售商ID
	 */
	private String supplierId;
	
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
     * 评价时间（开始）
     */
    private Timestamp commentTimeBegin;
    
    /**
     * 评价时间（结束）
     */
    private Timestamp commentTimeEnd;
	
	@NotNull(message = "pageSize不能为空",groups = { IProdCommentManagerSV.QueryPageInfo.class})
	private Integer pageSize;
	
	@NotNull(message = "pageNo不能为空",groups = { IProdCommentManagerSV.QueryPageInfo.class})
	private Integer pageNo;
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public Timestamp getCommentTimeBegin() {
		return commentTimeBegin;
	}

	public void setCommentTimeBegin(Timestamp commentTimeBegin) {
		this.commentTimeBegin = commentTimeBegin;
	}

	public Timestamp getCommentTimeEnd() {
		return commentTimeEnd;
	}

	public void setCommentTimeEnd(Timestamp commentTimeEnd) {
		this.commentTimeEnd = commentTimeEnd;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getShopScoreMs() {
		return shopScoreMs;
	}

	public void setShopScoreMs(Long shopScoreMs) {
		this.shopScoreMs = shopScoreMs;
	}

	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
