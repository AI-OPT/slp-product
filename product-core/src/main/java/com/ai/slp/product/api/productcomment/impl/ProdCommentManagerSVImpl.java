package com.ai.slp.product.api.productcomment.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.CommentPageRequest;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.ProdReplyComment;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {
	private static final Logger logger = LoggerFactory.getLogger(ProdCommentManagerSVImpl.class);
	@Autowired
	IProdCommentBusiSV prodCommentBusiSV;
	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	
	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest)throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentPageRequest.getTenantId());
		return prodCommentBusiSV.queryPageBySku(prodCommentPageRequest);
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentCreateRequest.getTenantId());
		checkCreateCommentParams(prodCommentCreateRequest);
		return prodCommentBusiSV.createProdComment(prodCommentCreateRequest);
	}

	@Override
	public BaseResponse replyComment(ProdReplyComment replyComment) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(replyComment.getTenantId());
		CommonUtils.checkSupplierId(replyComment.getSupplierId());
		//return prodCommentBusiSV.replyProdComment(replyComment);
		
		BaseResponse baseResponse = new BaseResponse();
		//查询是否有评论   有评论才可以回复评论
		ProdComment params = new ProdComment();
		params.setTenantId(replyComment.getTenantId());
		params.setSupplierId(replyComment.getSupplierId());
		params.setCommentId(replyComment.getCommentId());
		params.setState(CommonConstants.STATE_ACTIVE);
		
		long queryCommenStart = System.currentTimeMillis();
		logger.info("####loadtest####开始执行prodCommentAtomSV.queryByCommentId，根据评论编码查询评论,当前时间戳：" + queryCommenStart);
		
		ProdComment comment = prodCommentAtomSV.queryByCommentId(replyComment.getCommentId());
		Integer queryCountByParams =0;
		if(comment!=null){
			queryCountByParams=1;
		}
		long queryCommenEnd = System.currentTimeMillis();
		logger.info("####loadtest####结束调用prodCommentAtomSV.queryByCommentId，根据评论编码查询评论，查询评论条数,当前时间戳：" + queryCommenEnd + ",用时:"
				+ (queryCommenEnd - queryCommenStart) + "毫秒");
		
		//判断评论条数
		if (queryCountByParams>0) {
			//对评论进行回复
			ProdCommentReply commentReply = new ProdCommentReply();
			commentReply.setCommentId(replyComment.getCommentId());
			commentReply.setReplyComment(replyComment.getReplyComment());
			commentReply.setSupplierId(replyComment.getSupplierId());
			commentReply.setReplierId(replyComment.getReplierId());
			commentReply.setProdId(comment.getProdId());
			commentReply.setSkuId(comment.getSkuId());
			commentReply.setStandedProdId(comment.getStandedProdId());
			
			long queryCommenReplyStart = System.currentTimeMillis();
			logger.info("####loadtest####开始执行prodCommentAtomSV.queryByCommentId，进行商品评论回复,当前时间戳：" + queryCommenReplyStart);
			
			prodCommentBusiSV.replyProdComment(commentReply);
			
			long queryCommenReplyEnd = System.currentTimeMillis();
			logger.info("####loadtest####结束调用prodCommentAtomSV.queryByCommentId，进行商品评论回复，查询评论条数,当前时间戳：" + queryCommenReplyEnd + ",用时:"
					+ (queryCommenReplyEnd - queryCommenReplyStart) + "毫秒");
			
			
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
			baseResponse.setResponseHeader(responseHeader );
		}else{
			ResponseHeader responseHeader = new ResponseHeader(false,ExceptCodeConstants.Special.NO_DATA_OR_CACAE_ERROR,"没有评论");
			baseResponse.setResponseHeader(responseHeader );
		}
		return baseResponse;
		
		
	}
/*	public BaseResponse replyComment(ProdReplyComment replyComment) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(replyComment.getTenantId());
		CommonUtils.checkSupplierId(replyComment.getSupplierId());
		return prodCommentBusiSV.replyProdComment(replyComment);
	}
*/	/**
	 * 检查新增评论入参
	 * @param prodCommentCreateRequest
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private void checkCreateCommentParams(ProdCommentCreateRequest prodCommentCreateRequest) {
		List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
		if(commentList == null || commentList.size() == 0){
			throw new BusinessException("commentList 不能为空");
		}
		for(ProdCommentVO prodCommentVO : commentList){
			String commentBody = prodCommentVO.getCommentBody();
			if(StringUtils.isBlank(commentBody)){
				throw new BusinessException("commentBody 不能为空");
			}
			Long shopScoreFw = prodCommentVO.getShopScoreFw();
			if(shopScoreFw == null){
				throw new BusinessException("shopScoreFw 不能为空");
			}
			Long shopScoreMs = prodCommentVO.getShopScoreMs();
			if(shopScoreMs == null){
				throw new BusinessException("shopScoreMs 不能为空");
			}
			Long shopScoreWl = prodCommentVO.getShopScoreWl();
			if(shopScoreWl == null){
				throw new BusinessException("shopScoreWl 不能为空");
			}
			String skuId = prodCommentVO.getSkuId();
			if(StringUtils.isBlank(skuId)){
				throw new BusinessException("skuId 不能为空");
			}
		}
	}

	@Override
	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest)
			throws BusinessException, SystemException {
		
		ProdComment params = new ProdComment();
		BeanUtils.copyProperties(params, commentPageRequest);
		Integer pageSize = commentPageRequest.getPageSize();
		Integer pageNo = commentPageRequest.getPageNo();
		Timestamp commentTimeBegin = commentPageRequest.getCommentTimeBegin();
		Timestamp commentTimeEnd = commentPageRequest.getCommentTimeEnd();
		PageInfoResponse<CommentPageResponse> result = new PageInfoResponse<CommentPageResponse>();
		CommonUtils.checkTenantId(commentPageRequest.getTenantId());
		List<ProdComment> queryPageList = prodCommentBusiSV.queryPageInfo(params,commentTimeBegin, commentTimeEnd, pageSize, pageNo);
		 
		 ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
			result.setResponseHeader(responseHeader );
			result.setPageNo(pageNo);
			result.setPageSize(pageSize);
			if(queryPageList == null || queryPageList.size() == 0){
				result.setCount(0);
				result.setResult(null);
				return result;
			}else{
				//查询条数
				Integer count = prodCommentAtomSV.queryCountByParams(params,commentTimeBegin, commentTimeEnd);
				result.setCount(count);
				List<CommentPageResponse> prodCommentList = prodCommentBusiSV.getCommentResponseList(queryPageList);
				result.setResult(prodCommentList);
				return result;
			}
		
	//	ruturn null;
		
	}
/*	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(commentPageRequest.getTenantId());
		return prodCommentBusiSV.queryPageInfo(commentPageRequest);
	}
*/
	@Override
	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(updateCommentStateRequest.getTenantId());
		return prodCommentBusiSV.updateCommentState(updateCommentStateRequest);
	}

	@Override
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset)
			throws BusinessException, SystemException {
		return prodCommentBusiSV.queryPictureByCommentId(queryRequset);
	}

}
