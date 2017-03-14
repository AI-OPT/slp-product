package com.ai.slp.product.api.productcomment.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {

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
		return prodCommentBusiSV.replyProdComment(replyComment);
	}
	/**
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
