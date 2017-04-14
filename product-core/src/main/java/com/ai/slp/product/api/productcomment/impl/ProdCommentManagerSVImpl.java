package com.ai.slp.product.api.productcomment.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.common.JsonBuilder;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.util.StringUtil;
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
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.comment.CommentInfo;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.ConvertUtils;
import com.ai.slp.product.util.CriteriaUtils;
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
			/**
			 * 先查询ES缓存
			 */
			IProductSearch productSearch = new ProductSearchImpl();
			int startSize = 1;
			int maxSize = 1;
			// 最大条数设置
			int pageNo = prodCommentPageRequest.getPageNo();
			int size = prodCommentPageRequest.getPageSize();
			if (prodCommentPageRequest.getPageNo() == 1) {
				startSize = 0;
			} else {
				startSize = (pageNo - 1) * size;
			}
			maxSize = size;
			List<SearchCriteria> searchfieldVos = new ArrayList<SearchCriteria>();
			searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.STATE, "1",
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
			if (!StringUtil.isBlank(prodCommentPageRequest.getSkuId())) {
				searchfieldVos
						.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID, prodCommentPageRequest.getSkuId(),
								new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
			}
			Result<CommentInfo> commentResult = productSearch.searchComment(searchfieldVos, startSize, maxSize, null);
			if (!CollectionUtil.isEmpty(commentResult.getContents())) {
				List<ProdCommentPageResponse> prodCommentPageResponses = new ArrayList<>();
				for (CommentInfo commentInfo : commentResult.getContents()) {
					ProdCommentPageResponse response = ConvertUtils.convertToProdCommentPageResponse(commentInfo);
					prodCommentPageResponses.add(response);
				}
				result.setResult(prodCommentPageResponses);
				result.setCount((int) commentResult.getCount());
				result.setPageNo(prodCommentPageRequest.getPageNo());
				result.setPageSize(prodCommentPageRequest.getPageSize());
			} /*
				 * else { // 查询商品信息 String tenantId =
				 * prodCommentPageRequest.getTenantId(); // SKUID与商品ID意义相同
				 * Product product = productAtomSV.selectByProductId(tenantId,
				 * prodCommentPageRequest.getSkuId()); if (product == null) {
				 * result.setCount(0); result.setResult(null);
				 * result.setResponseHeader(new ResponseHeader(true,
				 * ResultCodeConstants.FAIL_CODE, "没有查询到商品信息。")); return result;
				 * } result =
				 * prodCommentBusiSV.queryPageBySku(prodCommentPageRequest,
				 * product.getStandedProdId()); }
				 */
			responseHeader = new ResponseHeader(true, CommonConstants.OPERATE_SUCCESS, "success");
		} catch (Exception e) {
			logger.error("查询商品评价失败", e);
			if (e instanceof BusinessException) {
				responseHeader = new ResponseHeader(false, ((BusinessException) e).getErrorCode(),
						((BusinessException) e).getErrorMessage());
			} else {
				responseHeader = new ResponseHeader(false, ExceptCodeConstants.Special.SYSTEM_ERROR, "查询商品评价失败");
			}
		}
		result.setResponseHeader(responseHeader);
		return result;
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest)
			throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = null;
		try {
			CommonUtils.checkTenantId(prodCommentCreateRequest.getTenantId());
			checkCreateCommentParams(prodCommentCreateRequest);
			String tenantId = prodCommentCreateRequest.getTenantId();
			String userId = prodCommentCreateRequest.getUserId();
			List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
			List<PictureVO> pictureList = new ArrayList<PictureVO>();
			List<ProdComment> prodComments = new ArrayList<>();
			Map<String, List<PictureVO>> pictureMap = new HashMap<>();
			if (!CollectionUtil.isEmpty(commentList)) {
				for (ProdCommentVO prodCommentVO : commentList) {
					ProdComment params = new ProdComment();
					params.setUserId(userId);
					BeanUtils.copyProperties(params, prodCommentVO);
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
					String commnentId = prodCommentBusiSV.createProdComment(params, pictureList);
					params.setCommentId(commnentId);
					prodComments.add(params);
					pictureMap.put(commnentId, pictureList);
				}
			}
			/**
			 * 加缓存
			 */
			List<CommentInfo> commentInfos = ConvertUtils.convertToCommentInfo(prodComments, pictureMap);
			SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace_COMMENT).bulkInsert(commentInfos);
			responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "");
		} catch (Exception e) {
			logger.info("创建商品评价失败", e);
			if (e instanceof BusinessException) {
				responseHeader = new ResponseHeader(false, ((BusinessException) e).getErrorCode(),
						((BusinessException) e).getErrorMessage());
			} else {
				responseHeader = new ResponseHeader(false, ExceptCodeConstants.Special.SYSTEM_ERROR, "");
			}
			response.setResponseHeader(responseHeader);
		}
		return response;
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

		// ProdComment comment =
		// prodCommentAtomSV.queryByCommentId(replyComment.getCommentId());
		// 先查询ES缓存
		IProductSearch productSearch = new ProductSearchImpl();
		List<SearchCriteria> searchfieldVos = new ArrayList<SearchCriteria>();
		if (!StringUtil.isBlank(replyComment.getCommentId())) {
			searchfieldVos.add(new SearchCriteria("commentid", replyComment.getCommentId(),
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		ProdComment comment = new ProdComment();
		Result<CommentInfo> commentResult = productSearch.searchComment(searchfieldVos, 0, 10, null);
		if (!CollectionUtil.isEmpty(commentResult.getContents())) {
			CommentInfo commentInfo = commentResult.getContents().get(0);
			comment.setTenantId(commentInfo.getTenantid());
			comment.setCommentId(commentInfo.getCommentid());
			comment.setProdId(commentInfo.getProductid());
			comment.setUserId(commentInfo.getUserid());
			comment.setCommentBody(commentInfo.getCommentbody());
			comment.setShopScoreMs(commentInfo.getShopscorems());
			comment.setShopScoreFw(commentInfo.getShopscorefw());
			comment.setShopScoreWl(commentInfo.getShopscorewl());
			comment.setCommentTime(new Timestamp(commentInfo.getCommenttime()));
			comment.setState(commentInfo.getState());
			comment.setReplyState(commentInfo.getReplaystate());
			comment.setIsPicture(commentInfo.getIspictrue());

		} else {
			logger.error("查询商品评价失败");
		}

		Integer queryCountByParams = 0;
		if (comment != null) {
			queryCountByParams = 1;
		}

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

			prodCommentBusiSV.replyProdComment(commentReply);

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

		PageInfoResponse<CommentPageResponse> result = new PageInfoResponse<CommentPageResponse>();
		ResponseHeader responseHeader = null;
		try {
			CommonUtils.checkTenantId(commentPageRequest.getTenantId());
			/**
			 * 先查询ES缓存
			 */
			IProductSearch productSearch = new ProductSearchImpl();
			int startSize = 1;
			int maxSize = 1;
			// 最大条数设置
			int pageNo = commentPageRequest.getPageNo();
			int size = commentPageRequest.getPageSize();
			if (commentPageRequest.getPageNo() == 1) {
				startSize = 0;
			} else {
				startSize = (pageNo - 1) * size;
			}
			maxSize = size;
			List<SearchCriteria> searchfieldVos = CriteriaUtils.commonConditions(commentPageRequest);
			Result<CommentInfo> commentResult = productSearch.searchComment(searchfieldVos, startSize, maxSize, null);
			result.setCount(0);
			result.setPageNo(commentPageRequest.getPageNo());
			result.setPageSize(commentPageRequest.getPageSize());
			if (!CollectionUtil.isEmpty(commentResult.getContents())) {
				List<CommentPageResponse> prodCommentPageResponses = new ArrayList<>();
				for (CommentInfo commentInfo : commentResult.getContents()) {
					CommentPageResponse response = ConvertUtils.convertToCommentPageResponse(commentInfo);
					prodCommentPageResponses.add(response);
				}
				result.setResult(prodCommentPageResponses);
				result.setCount((int) commentResult.getCount());
				result.setPageNo(commentPageRequest.getPageNo());
				result.setPageSize(commentPageRequest.getPageSize());
			}
			responseHeader = new ResponseHeader(true,CommonConstants.OPERATE_SUCCESS,"success");
		} catch (Exception e) {
			logger.error("查询商品评价失败", e);
			if (e instanceof BusinessException) {
				responseHeader = new ResponseHeader(false, ((BusinessException) e).getErrorCode(),
						((BusinessException) e).getErrorMessage());
			} else {
				responseHeader = new ResponseHeader(false, ExceptCodeConstants.Special.SYSTEM_ERROR, "查询商品评价失败");
			}
		}
		result.setResponseHeader(responseHeader);
		return result;
	}

	/*
	 * @Override public PageInfoResponse<CommentPageResponse>
	 * queryPageInfo(CommentPageRequest commentPageRequest) throws
	 * BusinessException, SystemException {
	 * 
	 * ProdComment params = new ProdComment(); BeanUtils.copyProperties(params,
	 * commentPageRequest); Integer pageSize = commentPageRequest.getPageSize();
	 * Integer pageNo = commentPageRequest.getPageNo(); Timestamp
	 * commentTimeBegin = commentPageRequest.getCommentTimeBegin(); Timestamp
	 * commentTimeEnd = commentPageRequest.getCommentTimeEnd();
	 * PageInfoResponse<CommentPageResponse> result = new
	 * PageInfoResponse<CommentPageResponse>();
	 * CommonUtils.checkTenantId(commentPageRequest.getTenantId());
	 * List<ProdComment> queryPageList = prodCommentBusiSV.queryPageInfo(params,
	 * commentTimeBegin, commentTimeEnd, pageSize, pageNo);
	 * 
	 * ResponseHeader responseHeader = new ResponseHeader(true,
	 * ExceptCodeConstants.Special.SUCCESS, "");
	 * result.setResponseHeader(responseHeader); result.setPageNo(pageNo);
	 * result.setPageSize(pageSize); if (queryPageList == null ||
	 * queryPageList.size() == 0) { result.setCount(0); result.setResult(null);
	 * return result; } else { // 查询条数 Integer count =
	 * prodCommentAtomSV.queryCountByParams(params, commentTimeBegin,
	 * commentTimeEnd); result.setCount(count); List<CommentPageResponse>
	 * prodCommentList =
	 * prodCommentBusiSV.getCommentResponseList(queryPageList);
	 * result.setResult(prodCommentList); return result; }
	 * 
	 * // ruturn null;
	 * 
	 * }
	 */
	@Override
	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest)
			throws IOException, Exception {
		CommonUtils.checkTenantId(updateCommentStateRequest.getTenantId());
		int count = prodCommentBusiSV.updateCommentState(updateCommentStateRequest);
		if (count > 0) {
			BaseResponse baseResponse = new BaseResponse();
			ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "更新成功");
			baseResponse.setResponseHeader(responseHeader);
			for (String commentId : updateCommentStateRequest.getCommentIdList()) {
				SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace_COMMENT).upsert(commentId,
						new JsonBuilder().startObject()
								.field(SearchFieldConfConstants.STATE, updateCommentStateRequest.getState())
								.field(SearchFieldConfConstants.COMMENT_ID, commentId).endObject());
			}
			return baseResponse;
		} else {
			BaseResponse baseResponse = new BaseResponse();
			ResponseHeader responseHeader = new ResponseHeader(false, ExceptCodeConstants.Special.SUCCESS, "更新失败");
			baseResponse.setResponseHeader(responseHeader);
			return baseResponse;
		}
	}

	@Override
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset)
			throws BusinessException, SystemException {
		return prodCommentBusiSV.queryPictureByCommentId(queryRequset);
	}

}
