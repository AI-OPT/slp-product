package com.ai.slp.product.service.business.impl.comment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryRequset;
import com.ai.slp.product.api.productcomment.param.CommentPictureQueryResponse;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.productcomment.param.UpdateCommentStateRequest;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.alibaba.fastjson.JSON;


@Service
@Transactional
public class ProdCommentBusiSVImpl implements IProdCommentBusiSV {
	private static final Logger logger = LoggerFactory.getLogger(IProdCommentBusiSV.class);
	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	
	@Autowired
	IProdCommentPictureAtomSV prodCommentPictureAtomSV;
	
	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest,String standedProdId) {
		PageInfoResponse<ProdCommentPageResponse> result = new PageInfoResponse<ProdCommentPageResponse>();
		ProdComment params = new ProdComment();
		BeanUtils.copyProperties(params, prodCommentPageRequest);
		params.setSkuId(null);
		params.setStandedProdId(standedProdId);
		Integer pageSize = prodCommentPageRequest.getPageSize();
		Integer pageNo = prodCommentPageRequest.getPageNo();
		//查询条数
		Integer count = prodCommentAtomSV.queryCountByProductId(params);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		result.setResponseHeader(responseHeader );
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		result.setCount(count);
		logger.debug(JSON.toJSONString(result));
		if(count==0){
			result.setResult(null);
			result.setResponseHeader(new ResponseHeader(true,ResultCodeConstants.FAIL_CODE,"该商品没有评论信息。"));
			return result;
		}else{
			List<ProdComment> queryPageList = prodCommentAtomSV.queryPageListByProductId(params, pageSize, pageNo);
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
	public String createProdComment(ProdComment prodComment, List<PictureVO> pictureList) {
		String prodCommentId = prodCommentAtomSV.createProdComment(prodComment);
		// 添加商品图片
		if (!StringUtil.isBlank(prodCommentId)
				&& ProductCommentConstants.HasPicture.YSE.equals(prodComment.getIsPicture())) {
			if (CollectionUtil.isEmpty(pictureList)) {
				for (PictureVO pictureVO : pictureList) {
					ProdCommentPicture prodCommentPicture = new ProdCommentPicture();
					BeanUtils.copyProperties(prodCommentPicture, pictureVO);
					prodCommentPicture.setCommentId(prodCommentId);
					prodCommentPictureAtomSV.createPicture(prodCommentPicture);
				}
			}
		}
		return prodCommentId;
	}
	
	@Override
	public String createProdComment(ProdComment prodComment, List<PictureVO> pictureList,String commentId) {
		String prodCommentId = prodCommentAtomSV.createProdComment(prodComment,commentId);
		// 添加商品图片
		if (!StringUtil.isBlank(prodCommentId)
				&& ProductCommentConstants.HasPicture.YSE.equals(prodComment.getIsPicture())) {
			if (CollectionUtil.isEmpty(pictureList)) {
				for (PictureVO pictureVO : pictureList) {
					ProdCommentPicture prodCommentPicture = new ProdCommentPicture();
					BeanUtils.copyProperties(prodCommentPicture, pictureVO);
					prodCommentPicture.setCommentId(prodCommentId);
					prodCommentPictureAtomSV.createPicture(prodCommentPicture);
				}
			}
		}
		return prodCommentId;
	}

	@Override
	public void replyProdComment(ProdCommentReply commentReply) {
		//查询是否有评论   有评论才可以回复评论
		prodCommentAtomSV.prodCommentReply(commentReply);
	}
/*	public BaseResponse replyProdComment(ProdReplyComment replyComment) {
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
			
			prodCommentAtomSV.prodCommentReply(commentReply);
			
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
*/
	
	@Override
	public List<CommentPageResponse> queryPageInfo(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd, Integer pageSize, Integer pageNos) {
		
		//List<ProdComment> queryPageList = prodCommentAtomSV.queryPageList(params,commentTimeBegin, commentTimeEnd, pageSize, pageNo);
		List<CommentPageResponse> prodCommentPage = null;
		try {
			params.setSkuId(null);
			List<ProdComment> queryPageList = prodCommentAtomSV.queryPageList(params,commentTimeBegin, commentTimeEnd, pageSize, pageNos);
			prodCommentPage = getCommentResponseList(queryPageList);
		} catch (Exception e) {
			logger.error("查询商品评价失败", e);
		}
		return prodCommentPage;
	}
	
	/*public PageInfoResponse<CommentPageResponse> queryPageInfo(CommentPageRequest commentPageRequest) {
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
	}*/

	/**
	 * 转换返回对象
	 * @param prodCommentList
	 * @return
	 */
//	private List<CommentPageResponse> getCommentResponseList(List<ProdComment> prodCommentList) {
	public List<CommentPageResponse> getCommentResponseList(List<ProdComment> prodCommentList) {
		List<CommentPageResponse> responseList = new LinkedList<CommentPageResponse>();
		for(ProdComment prodComment : prodCommentList ){
			//转换返回对象
			CommentPageResponse commentPageResponse = new CommentPageResponse();
			BeanUtils.copyProperties(commentPageResponse, prodComment);
			Product product = productAtomSV.selectByProductId(prodComment.getProdId());
			if(product != null){
				commentPageResponse.setProdName(product.getProdName());
			}
			responseList.add(commentPageResponse);
		}
		return responseList;
	}

	@Override
	public int updateCommentState(UpdateCommentStateRequest updateCommentStateRequest) {
		String state = updateCommentStateRequest.getState();
		String operId = updateCommentStateRequest.getOperId();
		String tenantId = updateCommentStateRequest.getTenantId();
		List<String> commentIdList = updateCommentStateRequest.getCommentIdList();
		return prodCommentAtomSV.updateStateByIds(state,operId,tenantId,commentIdList);
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
