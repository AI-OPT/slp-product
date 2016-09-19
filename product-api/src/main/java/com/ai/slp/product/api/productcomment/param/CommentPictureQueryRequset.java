package com.ai.slp.product.api.productcomment.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

public class CommentPictureQueryRequset implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "commentId不能为空",groups = { IProdCommentManagerSV.QueryPictureByCommentId.class})
	private String commentId;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

}
