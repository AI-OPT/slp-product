package com.ai.slp.product.service.business.impl.comment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
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
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;


@Service
@Transactional
public class ProdCommentBusiSVImpl implements IProdCommentBusiSV {

	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProdCommentPictureAtomSV prodCommentPictureAtomSV;
	
	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest) {
		PageInfoResponse<ProdCommentPageResponse> result = new PageInfoResponse<ProdCommentPageResponse>();
		//查询评论信息
		String tenantId = prodCommentPageRequest.getTenantId();
		ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId, prodCommentPageRequest.getSkuId());
		if(prodSku == null){
			/*result.setCount(0);
			result.setResult(null);
			result.setResponseHeader(new ResponseHeader(true,ResultCodeConstants.FAIL_CODE,"没有查询到sku信息。"));
			return result;*/
			throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未查询到指定商品,租户ID:"+tenantId+",销售商品id:"+prodCommentPageRequest.getSkuId());
		}
		String prodId = prodSku.getProdId();
		Product product = productAtomSV.selectByProductId(tenantId, prodId);
		if(product == null){
			result.setCount(0);
			result.setResult(null);
			result.setResponseHeader(new ResponseHeader(true,ResultCodeConstants.FAIL_CODE,"没有查询到商品信息。"));
			return result;
		}
		ProdComment params = new ProdComment();
		BeanUtils.copyProperties(params, prodCommentPageRequest);
		params.setSkuId(null);
		params.setStandedProdId(product.getStandedProdId());
		Integer pageSize = prodCommentPageRequest.getPageSize();
		Integer pageNo = prodCommentPageRequest.getPageNo();
		List<ProdComment> queryPageList = prodCommentAtomSV.queryPageListByProductId(params, pageSize, pageNo);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		result.setResponseHeader(responseHeader );
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		if(queryPageList == null || queryPageList.size() == 0){
			result.setCount(0);
			result.setResult(null);
			result.setResponseHeader(new ResponseHeader(true,ResultCodeConstants.FAIL_CODE,"该商品没有评论信息。"));
			return result;
		}else{
			//查询条数
			Integer count = prodCommentAtomSV.queryCountByProductId(params);
			result.setCount(count);
			List<ProdCommentPageResponse> prodCommentList = getProdCommentResponseList(queryPageList);
			result.setResult(prodCommentList);
			return result;
		}
	}

	/**
	 * 获得分页查询返回对象List
	 * @param queryPageList
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private List<ProdCommentPageResponse> getProdCommentResponseList(List<ProdComment> queryPageList) {
		//获得有图片的评论
		Set<String> commentIdSet = new HashSet<String>();
		for(ProdComment prodComment : queryPageList){
			String isPicture = prodComment.getIsPicture();
			if(ProductCommentConstants.HasPicture.YSE.equals(isPicture)){
				commentIdSet.add(prodComment.getCommentId());
			}
		}
		//查询图片信息
		Map<String, List<PictureVO>> commentPictureMap = new HashMap<String, List<PictureVO>>();
		if(commentIdSet.size()>0){
			for(String commentId : commentIdSet){
				List<ProdCommentPicture> pictureList = prodCommentPictureAtomSV.queryPictureListByCommentId(commentId);
				List<PictureVO> pictureVoList = new LinkedList<PictureVO>();
				for(ProdCommentPicture pricture : pictureList){
					PictureVO pictureVO = new PictureVO();
					pictureVO.setPicAddr(pricture.getPicAddr());
					pictureVO.setPicName(pricture.getPicName());
					pictureVoList.add(pictureVO);
				}
				commentPictureMap.put(commentId, pictureVoList);
			}
		}
		List<ProdCommentPageResponse> prodCommentList = new LinkedList<ProdCommentPageResponse>();
		for(ProdComment prodComment : queryPageList){
			//转换返回对象
			ProdCommentPageResponse prodCommentPageResponse = new ProdCommentPageResponse();
			BeanUtils.copyProperties(prodCommentPageResponse, prodComment);
			//设置图片list
			String commentId = prodComment.getCommentId();
			if(commentPictureMap.containsKey(commentId)){
				prodCommentPageResponse.setPictureList(commentPictureMap.get(commentId));
			}
			prodCommentList.add(prodCommentPageResponse);
		}
		return prodCommentList;
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest) {
		BaseResponse baseResponse = new BaseResponse();
		String tenantId = prodCommentCreateRequest.getTenantId();
		String userId = prodCommentCreateRequest.getUserId();
		List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
		if(commentList != null && commentList.size() >0){
			for(ProdCommentVO prodCommentVO : commentList){
				ProdComment params = new ProdComment();
				params.setUserId(userId);
				BeanUtils.copyProperties(params, prodCommentVO);
				String skuId = prodCommentVO.getSkuId();
				ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId, skuId);
				if(prodSku == null){
					throw new BusinessException("skuId 数据错误，找不到对应的销售商品");
				}
				String prodId = prodSku.getProdId();
				Product product = productAtomSV.selectByProductId(tenantId, prodId);
				if(product == null){
					throw new BusinessException("skuId 数据错误，找不到对应的标准商品");
				}
				params.setProdId(prodId);
				params.setStandedProdId(product.getStandedProdId());
				params.setSupplierId(product.getSupplierId());
				//添加评论
				params.setTenantId(prodCommentCreateRequest.getTenantId());
				params.setOrderId(prodCommentCreateRequest.getOrderId());
				List<PictureVO> pictureList = prodCommentVO.getPictureList();
				//判断是否有图片
				boolean isHasPicture = pictureList != null && pictureList.size() >0;
				if(isHasPicture){
					params.setIsPicture(ProductCommentConstants.HasPicture.YSE);
				}else{
					params.setIsPicture(ProductCommentConstants.HasPicture.NO);
				}
				String prodCommentId = prodCommentAtomSV.createProdComment(params);
				//添加商品图片
				if(!StringUtil.isBlank(prodCommentId) && isHasPicture){
					if (CollectionUtil.isEmpty(pictureList)) {
						for(PictureVO pictureVO : pictureList){
							ProdCommentPicture prodCommentPicture = new ProdCommentPicture();
							BeanUtils.copyProperties(prodCommentPicture, pictureVO);
							prodCommentPicture.setCommentId(prodCommentId);
							prodCommentPictureAtomSV.createPicture(prodCommentPicture);
						}
					}
				}
			}
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
			baseResponse.setResponseHeader(responseHeader );
		}else{
			ResponseHeader responseHeader = new ResponseHeader(false,ExceptCodeConstants.Special.NO_DATA_OR_CACAE_ERROR,"无数据");
			baseResponse.setResponseHeader(responseHeader );
		}
		return baseResponse;
	}

	@Override
	public BaseResponse replyProdComment(ProdReplyComment replyComment) {
		BaseResponse baseResponse = new BaseResponse();
		//查询是否有评论   有评论才可以回复评论
		ProdComment params = new ProdComment();
		params.setTenantId(replyComment.getTenantId());
		params.setSupplierId(replyComment.getSupplierId());
		params.setCommentId(replyComment.getCommentId());
		params.setState(CommonConstants.STATE_ACTIVE);
		Integer queryCountByParams = prodCommentAtomSV.queryCountByProductId(params);
		ProdComment comment = prodCommentAtomSV.queryByCommentId(replyComment.getCommentId());
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
			
			prodCommentAtomSV.prodCommentReply(commentReply);
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
			baseResponse.setResponseHeader(responseHeader );
		}else{
			ResponseHeader responseHeader = new ResponseHeader(false,ExceptCodeConstants.Special.NO_DATA_OR_CACAE_ERROR,"没有评论");
			baseResponse.setResponseHeader(responseHeader );
		}
		return baseResponse;
	}

	@Override
	public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest) {
		PageInfoResponse<CommentPageResponse> result = new PageInfoResponse<CommentPageResponse>();
		//查询评论信息
		ProdComment params = new ProdComment();
		BeanUtils.copyProperties(params, commentPageRequest);
		Integer pageSize = commentPageRequest.getPageSize();
		Integer pageNo = commentPageRequest.getPageNo();
		Timestamp commentTimeBegin = commentPageRequest.getCommentTimeBegin();
		Timestamp commentTimeEnd = commentPageRequest.getCommentTimeEnd();
		List<ProdComment> queryPageList = prodCommentAtomSV.queryPageList(params,commentTimeBegin, commentTimeEnd, pageSize, pageNo);
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
			List<CommentPageResponse> prodCommentList = getCommentResponseList(queryPageList);
			result.setResult(prodCommentList);
			return result;
		}
	}

	/**
	 * 转换返回对象
	 * @param prodCommentList
	 * @return
	 */
	private List<CommentPageResponse> getCommentResponseList(List<ProdComment> prodCommentList) {
		List<CommentPageResponse> responseList = new LinkedList<CommentPageResponse>();
		for(ProdComment prodComment : prodCommentList ){
			//转换返回对象
			CommentPageResponse commentPageResponse = new CommentPageResponse();
			BeanUtils.copyProperties(commentPageResponse, prodComment);
			Product product = productAtomSV.selectByProductId(prodComment.getTenantId(), prodComment.getProdId());
			if(product != null){
				commentPageResponse.setProdName(product.getProdName());
			}
			responseList.add(commentPageResponse);
		}
		return responseList;
	}

	@Override
	public BaseResponse updateCommentState(UpdateCommentStateRequest updateCommentStateRequest) {
		String state = updateCommentStateRequest.getState();
		String operId = updateCommentStateRequest.getOperId();
		String tenantId = updateCommentStateRequest.getTenantId();
		List<String> commentIdList = updateCommentStateRequest.getCommentIdList();
		int count = prodCommentAtomSV.updateStateByIds(state,operId,tenantId,commentIdList);
		if(count>0){
			BaseResponse baseResponse = new BaseResponse();
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"更新成功");
			baseResponse.setResponseHeader(responseHeader );
			return baseResponse;
		}else{
			BaseResponse baseResponse = new BaseResponse();
			ResponseHeader responseHeader = new ResponseHeader(false,ExceptCodeConstants.Special.SUCCESS,"更新失败");
			baseResponse.setResponseHeader(responseHeader );
			return baseResponse;
		}
	}

	@Override
	public CommentPictureQueryResponse queryPictureByCommentId(CommentPictureQueryRequset queryRequset) {
		List<ProdCommentPicture> pictureList = prodCommentPictureAtomSV.queryPictureListByCommentId(queryRequset.getCommentId());
		CommentPictureQueryResponse queryResponse= new CommentPictureQueryResponse();
		if(pictureList != null && pictureList.size()>0){
			List<PictureVO> pictureVoList = new ArrayList<PictureVO>();
			for(ProdCommentPicture picture : pictureList){
				PictureVO pictureVO = new PictureVO();
				BeanUtils.copyProperties(pictureVO, picture);
				pictureVoList.add(pictureVO);
			}
			queryResponse.setPictureList(pictureVoList);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "查询完成");
		queryResponse.setResponseHeader(responseHeader);
		return queryResponse;
	}

}
