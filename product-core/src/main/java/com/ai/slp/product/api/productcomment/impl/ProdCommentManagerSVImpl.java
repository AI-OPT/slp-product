package com.ai.slp.product.api.productcomment.impl;

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
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {

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
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest)throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentPageRequest.getTenantId());
		//查询商品信息
		PageInfoResponse<ProdCommentPageResponse> result = new PageInfoResponse<ProdCommentPageResponse>();
		String tenantId = prodCommentPageRequest.getTenantId();
		ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId, prodCommentPageRequest.getSkuId());
		if(prodSku == null){
			throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定商品,租户ID:"+tenantId+",销售商品id:"+prodCommentPageRequest.getSkuId());
		}
		String prodId = prodSku.getProdId();
		Product product = productAtomSV.selectByProductId(tenantId, prodId);
		if(product == null){
			result.setCount(0);
			result.setResult(null);
			result.setResponseHeader(new ResponseHeader(true,ResultCodeConstants.FAIL_CODE,"没有查询到商品信息。"));
			return result;
		}
		return prodCommentBusiSV.queryPageBySku(prodCommentPageRequest,product.getStandedProdId());
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentCreateRequest.getTenantId());
		checkCreateCommentParams(prodCommentCreateRequest);
		String tenantId = prodCommentCreateRequest.getTenantId();
		String userId = prodCommentCreateRequest.getUserId();
		List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
		List<ProdComment> prodComments = new ArrayList<ProdComment>();
		List<PictureVO> pictureList = new ArrayList<PictureVO>();
		if(!CollectionUtil.isEmpty(commentList)){
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
				//封装图片评论
				params.setTenantId(prodCommentCreateRequest.getTenantId());
				params.setOrderId(prodCommentCreateRequest.getOrderId());
				pictureList = prodCommentVO.getPictureList();
				//判断是否有图片
				params.setIsPicture(CollectionUtil.isEmpty(pictureList)?ProductCommentConstants.HasPicture.NO:ProductCommentConstants.HasPicture.YSE);
				prodComments.add(params);
			}
		}
		return prodCommentBusiSV.createProdComment(prodCommentCreateRequest,prodComments, pictureList);
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
		CommonUtils.checkTenantId(commentPageRequest.getTenantId());
		return prodCommentBusiSV.queryPageInfo(commentPageRequest);
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
