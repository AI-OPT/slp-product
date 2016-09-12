package com.ai.slp.product.api.productcomment.param;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

public class ProdCommentCreateRequest extends BaseInfo {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单ID
	 */
	@NotBlank(message = "orderId不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private String orderId;
	
	/**
	 * 评价人
	 */
	@NotBlank(message = "userId不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private String userId;
	
	@NotEmpty(message = "commentList不能为空",groups = { IProdCommentManagerSV.CreateProdComment.class})
	private List<ProdCommentVO> commentList;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProdCommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<ProdCommentVO> commentList) {
		this.commentList = commentList;
	}

}
