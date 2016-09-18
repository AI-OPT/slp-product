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
	
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest);

	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest);
	
	public BaseResponse replyProdComment(ProdReplyComment replyComment);

	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest);

	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest);

	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset);
}
