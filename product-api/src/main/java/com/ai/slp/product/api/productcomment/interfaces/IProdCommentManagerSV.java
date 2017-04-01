package com.ai.slp.product.api.productcomment.interfaces;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
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

/**
 * 商品评价
 *
 * Date: 2016年9月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author jiaxs
 */
@Path("/prodCommentManager")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProdCommentManagerSV {
	
	/**
	 * 查询sku所属标准品下的商品评论
	 * @param prodCommentPageRequest
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode	PROD_COMM_0002
	 * @RestRelativeURL prodCommentManager/queryPageInfoBySku
	 */
	@POST
	@Path("/queryPageInfoBySku")
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest) throws BusinessException, SystemException;
	@interface QueryPageInfoBySku{}
	/**
	 * 发表商品评价
	 * @param prodCommentCreateRequest
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode PROD_COMM_0003
	 * @RestRelativeURL prodCommentManager/createProdComment
	 */
	@POST
	@Path("/createProdComment")
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest) throws BusinessException, SystemException;
	@interface CreateProdComment{}
	
	/**
	 * 回复商品评价
	 * @param ProdReplyComment
	 * @return
	 * @author jiawen
	 * @ApiDocMethod
	 * @ApiCode	PROD_COMM_0004
	 * @RestRelativeURL prodCommentManager/replyComment
	 */
	@POST
	@Path("/replyComment")
	public BaseResponse replyComment(ProdReplyComment replyComment) throws BusinessException, SystemException;
	@interface ReplyComment{}
	
	/**
	 * 分页查询商品评论
	 * @param commentPageRequest
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode	PROD_COMM_0001
	 * @RestRelativeURL prodCommentManager/queryPageInfo
	 */
	@POST
	@Path("/queryPageInfo")
	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest) throws BusinessException, SystemException;
	@interface QueryPageInfo{}
	
	/**
	 * 修改商品评价状态
	 * @param updateCommentStateRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author jiaxs
	 * @throws Exception 
	 * @throws IOException 
	 * @ApiDocMethod
	 * @ApiCode PROD_COMM_0005
	 * @RestRelativeURL prodCommentManager/updateCommentState
	 */
	@POST
	@Path("/updateCommentState")
	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest) throws BusinessException, SystemException, IOException, Exception;
	@interface UpdateCommentState{}
	
	/**
	 * 查询评论图片
	 * @param queryRequset
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode PROD_COMM_0006
	 * @RestRelativeURL prodCommentManager/queryPictureByCommentId
	 */
	@POST
	@Path("/queryPictureByCommentId")
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset) throws BusinessException, SystemException;
	@interface QueryPictureByCommentId{}
}
