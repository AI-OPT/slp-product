package com.ai.slp.product.api.productcomment.param;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

public class ProdCommentVO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 子订单id
	 */
	private String subOrderId;
	
	/**
	 * 单品ID
	 */
	@NotBlank(message = "skuId不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private String skuId;

	/**
	 * 评价内容
	 */
	@NotBlank(message = "commentBody不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private String commentBody;

	/**
	 * 商品描述相符评分
	 */
	@NotNull(message = "shopScoreMs不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private Long shopScoreMs;

	/**
	 * 卖家服务态度评分
	 */
	@NotNull(message = "shopScoreWl不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private Long shopScoreWl;

	/**
	 * 物流发货速度评分
	 */
	@NotNull(message = "shopScoreFw不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private Long shopScoreFw;
	
	/**
	 * 商品图片
	 */
	private List<PictureVO> pictureList;

	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
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

	public Long getShopScoreWl() {
		return shopScoreWl;
	}

	public void setShopScoreWl(Long shopScoreWl) {
		this.shopScoreWl = shopScoreWl;
	}

	public Long getShopScoreFw() {
		return shopScoreFw;
	}

	public void setShopScoreFw(Long shopScoreFw) {
		this.shopScoreFw = shopScoreFw;
	}

	public List<PictureVO> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PictureVO> pictureList) {
		this.pictureList = pictureList;
	}

}
