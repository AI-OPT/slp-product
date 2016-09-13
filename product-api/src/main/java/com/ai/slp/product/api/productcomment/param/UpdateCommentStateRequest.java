package com.ai.slp.product.api.productcomment.param;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

public class UpdateCommentStateRequest extends BaseInfo{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 评价状态
	 */
	@NotBlank(message = "state不能为空",groups = { IProdCommentManagerSV.UpdateCommentState.class})
	private String state;
	
	/**
	 * 操作人Id
	 */
	@NotBlank(message = "operId不能为空",groups = { IProdCommentManagerSV.UpdateCommentState.class})
	private String operId;
	
	/**
	 * 评论id集合
	 */
	@NotEmpty(message = "commentIdList不能为空",groups = { IProdCommentManagerSV.UpdateCommentState.class})
	private List<String> commentIdList;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public List<String> getCommentIdList() {
		return commentIdList;
	}

	public void setCommentIdList(List<String> commentIdList) {
		this.commentIdList = commentIdList;
	}

}
