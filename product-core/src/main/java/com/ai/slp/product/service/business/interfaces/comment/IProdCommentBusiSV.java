package com.ai.slp.product.service.business.interfaces.comment;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.param.CommentPageRequest;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.ProdReplyComment;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;

public interface IProdCommentBusiSV {
	/**
	 * 查询评论
	 */
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest,String standedProdId);
	/**
	 * 发表评论
	 */
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest);
	/**
	 * 回复评价
	 */
	public BaseResponse replyProdComment(ProdReplyComment replyComment);
	/**
	 * 查询评论
	 */
	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest);
	/**
	 * 更新评论状态
	 */
	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest);
	/**
	 * 查看评论图片
	 */
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset);
}
