package com.ai.slp.product.api.productcomment.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.CommentPageRequest;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;
import com.ai.slp.product.api.productcomment.param.ProdReplyComment;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {

	private static final Logger logger = LoggerFactory.getLogger(ProdCommentManagerSVImpl.class);

	@Autowired
	IProdCommentBusiSV prodCommentBusiSV;

	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;

	@Autowired
	IProductAtomSV productAtomSV;

	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;

	@Autowired
	IProdCommentPictureAtomSV prodCommentPictureAtomSV;

	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest)
			throws BusinessException, SystemException {
		PageInfoResponse<ProdCommentPageResponse> result = new PageInfoResponse<ProdCommentPageResponse>();
		ResponseHeader responseHeader = null;
		try {
			CommonUtils.checkTenantId(prodCommentPageRequest.getTenantId());
			// 查询商品信息
			String tenantId = prodCommentPageRequest.getTenantId();
			/*
			 * ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,
			 * prodCommentPageRequest.getSkuId()); if(prodSku == null){ throw
			 * new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
			 * "未查询到指定商品,租户ID:"+tenantId+",销售商品id:"+prodCommentPageRequest.
			 * getSkuId()); } String prodId = prodSku.getProdId();
			 */
			// SKUID与商品ID意义相同
			Product product = productAtomSV.selectByProductId(tenantId, prodCommentPageRequest.getSkuId());
			if (product == null) {
				result.setCount(0);
				result.setResult(null);
				result.setResponseHeader(new ResponseHeader(true, ResultCodeConstants.FAIL_CODE, "没有查询到商品信息。"));
				return result;
			}
			result = prodCommentBusiSV.queryPageBySku(prodCommentPageRequest, product.getStandedProdId());
		} catch (Exception e) {
			logger.error("查询商品评价失败", e);
			if (e instanceof BusinessException) {
				responseHeader = new ResponseHeader(false, ((BusinessException) e).getErrorCode(),
						((BusinessException) e).getErrorMessage());
			} else {
				responseHeader = new ResponseHeader(false, ExceptCodeConstants.Special.SYSTEM_ERROR, "查询商品评价失败");
			}
			result.setResponseHeader(responseHeader);
		}
		result.setResponseHeader(responseHeader);
		return result;
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest)
			throws BusinessException, SystemException {
		BaseResponse createProdComment = new BaseResponse();
		ResponseHeader responseHeader = null;
		try {
			CommonUtils.checkTenantId(prodCommentCreateRequest.getTenantId());
			checkCreateCommentParams(prodCommentCreateRequest);
			String tenantId = prodCommentCreateRequest.getTenantId();
			String userId = prodCommentCreateRequest.getUserId();
			List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
			List<ProdComment> prodComments = new ArrayList<ProdComment>();
			List<PictureVO> pictureList = new ArrayList<PictureVO>();
			if (!CollectionUtil.isEmpty(commentList)) {
				for (ProdCommentVO prodCommentVO : commentList) {
					ProdComment params = new ProdComment();
					params.setUserId(userId);
					BeanUtils.copyProperties(params, prodCommentVO);
					/*
					 * String skuId = prodCommentVO.getSkuId(); ProdSku prodSku
					 * = prodSkuAtomSV.querySkuById(tenantId, skuId); if(prodSku
					 * == null){ throw new BusinessException(
					 * "skuId 数据错误，找不到对应的销售商品"); } String prodId =
					 * prodSku.getProdId();
					 */
					Product product = productAtomSV.selectByProductId(tenantId, prodCommentVO.getSkuId());
					if (product == null) {
						throw new BusinessException("skuId 数据错误，找不到对应的标准商品");
					}
					params.setProdId(prodCommentVO.getSkuId());
					params.setStandedProdId(product.getStandedProdId());
					params.setSupplierId(product.getSupplierId());
					// 封装图片评论
					params.setTenantId(prodCommentCreateRequest.getTenantId());
					params.setOrderId(prodCommentCreateRequest.getOrderId());
					pictureList = prodCommentVO.getPictureList();
					// 判断是否有图片
					params.setIsPicture(CollectionUtil.isEmpty(pictureList) ? ProductCommentConstants.HasPicture.NO
							: ProductCommentConstants.HasPicture.YSE);
					prodComments.add(params);
				}
			}
			createProdComment = prodCommentBusiSV.createProdComment(prodCommentCreateRequest, prodComments,
					pictureList);
		} catch (Exception e) {
			logger.error("创建商品评价失败", e);
			if (e instanceof BusinessException) {
				responseHeader = new ResponseHeader(false, ((BusinessException) e).getErrorCode(),
						((BusinessException) e).getErrorMessage());
			} else {
				responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SYSTEM_ERROR, "");
			}
			createProdComment.setResponseHeader(responseHeader);
		}
		return createProdComment;
	}

	@Override
	public BaseResponse replyComment(ProdReplyComment replyComment) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(replyComment.getTenantId());
		CommonUtils.checkSupplierId(replyComment.getSupplierId());
		// return prodCommentBusiSV.replyProdComment(replyComment);

		BaseResponse baseResponse = new BaseResponse();
		// 查询是否有评论 有评论才可以回复评论
		ProdComment params = new ProdComment();
		params.setTenantId(replyComment.getTenantId());
		params.setSupplierId(replyComment.getSupplierId());
		params.setCommentId(replyComment.getCommentId());
		params.setState(CommonConstants.STATE_ACTIVE);

		long queryCommenStart = System.currentTimeMillis();
		logger.info("####loadtest####开始执行prodCommentAtomSV.queryByCommentId，根据评论编码查询评论,当前时间戳：" + queryCommenStart);

		ProdComment comment = prodCommentAtomSV.queryByCommentId(replyComment.getCommentId());
		Integer queryCountByParams = 0;
		if (comment != null) {
			queryCountByParams = 1;
		}
		long queryCommenEnd = System.currentTimeMillis();
		logger.info("####loadtest####结束调用prodCommentAtomSV.queryByCommentId，根据评论编码查询评论，查询评论条数,当前时间戳：" + queryCommenEnd
				+ ",用时:" + (queryCommenEnd - queryCommenStart) + "毫秒");

		// 判断评论条数
		if (queryCountByParams > 0) {
			// 对评论进行回复
			ProdCommentReply commentReply = new ProdCommentReply();
			commentReply.setCommentId(replyComment.getCommentId());
			commentReply.setReplyComment(replyComment.getReplyComment());
			commentReply.setSupplierId(replyComment.getSupplierId());
			commentReply.setReplierId(replyComment.getReplierId());
			commentReply.setProdId(comment.getProdId());
			commentReply.setSkuId(comment.getSkuId());
			commentReply.setStandedProdId(comment.getStandedProdId());

			long queryCommenReplyStart = System.currentTimeMillis();
			logger.info(
					"####loadtest####开始执行prodCommentAtomSV.queryByCommentId，进行商品评论回复,当前时间戳：" + queryCommenReplyStart);

			prodCommentBusiSV.replyProdComment(commentReply);

			long queryCommenReplyEnd = System.currentTimeMillis();
			logger.info("####loadtest####结束调用prodCommentAtomSV.queryByCommentId，进行商品评论回复，查询评论条数,当前时间戳："
					+ queryCommenReplyEnd + ",用时:" + (queryCommenReplyEnd - queryCommenReplyStart) + "毫秒");

			ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "");
			baseResponse.setResponseHeader(responseHeader);
		} else {
			ResponseHeader responseHeader = new ResponseHeader(false,
					ExceptCodeConstants.Special.NO_DATA_OR_CACAE_ERROR, "没有评论");
			baseResponse.setResponseHeader(responseHeader);
		}
		return baseResponse;

	}

	/**
	 * 检查新增评论入参
	 * 
	 * @param prodCommentCreateRequest
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private void checkCreateCommentParams(ProdCommentCreateRequest prodCommentCreateRequest) {
		List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
		if (commentList == null || commentList.size() == 0) {
			throw new BusinessException("commentList 不能为空");
		}
		for (ProdCommentVO prodCommentVO : commentList) {
			String commentBody = prodCommentVO.getCommentBody();
			if (StringUtils.isBlank(commentBody)) {
				throw new BusinessException("commentBody 不能为空");
			}
			Long shopScoreFw = prodCommentVO.getShopScoreFw();
			if (shopScoreFw == null) {
				throw new BusinessException("shopScoreFw 不能为空");
			}
			Long shopScoreMs = prodCommentVO.getShopScoreMs();
			if (shopScoreMs == null) {
				throw new BusinessException("shopScoreMs 不能为空");
			}
			Long shopScoreWl = prodCommentVO.getShopScoreWl();
			if (shopScoreWl == null) {
				throw new BusinessException("shopScoreWl 不能为空");
			}
			String skuId = prodCommentVO.getSkuId();
			if (StringUtils.isBlank(skuId)) {
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
		List<ProdComment> queryPageList = prodCommentBusiSV.queryPageInfo(params, commentTimeBegin, commentTimeEnd,
				pageSize, pageNo);

		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "");
		result.setResponseHeader(responseHeader);
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		if (queryPageList == null || queryPageList.size() == 0) {
			result.setCount(0);
			result.setResult(null);
			return result;
		} else {
			// 查询条数
			Integer count = prodCommentAtomSV.queryCountByParams(params, commentTimeBegin, commentTimeEnd);
			result.setCount(count);
			List<CommentPageResponse> prodCommentList = prodCommentBusiSV.getCommentResponseList(queryPageList);
			result.setResult(prodCommentList);
			return result;
		}

		// ruturn null;

	}

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
