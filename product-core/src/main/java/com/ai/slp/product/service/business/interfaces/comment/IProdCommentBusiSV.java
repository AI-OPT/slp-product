package com.ai.slp.product.service.business.interfaces.comment;

import java.sql.Timestamp;
import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;

public interface IProdCommentBusiSV {
	/**
	 * 查询评论
	 */
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest,String standedProdId);
	/**
	 * 发表评论
	 */
	public String createProdComment(ProdComment prodComment,List<PictureVO> pictureList);
	/**
	 * 回复评价
	 */
	public void replyProdComment(ProdCommentReply commentReply);
	//public BaseResponse replyProdComment(ProdReplyComment replyComment);
	/**
	 * 查询评论
	 */
	public List<CommentPageResponse> queryPageInfo(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd, Integer pageSize, Integer pageNo);
	
	
	//public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest);
	/**
	 * 更新评论状态
	 */
	public int updateCommentState(UpdateCommentStateRequest updateCommentStateRequest);
	/**
	 * 查看评论图片
	 */
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset);
	
	/**
	 * 获取评论返回信息
	 */
	public List<CommentPageResponse> getCommentResponseList(List<ProdComment> prodCommentList);
	
	/**
	 * 创建商品评论
	 * @param prodComment
	 * @UCUSER
	 */
	String createProdComment(ProdComment prodComment, List<PictureVO> pictureList, String commentId);
}
