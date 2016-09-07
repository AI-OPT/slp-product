package com.ai.slp.product.service.business.impl.comment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;

@Service
@Transactional
public class ProdCommentBusiSVImpl implements IProdCommentBusiSV {

	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	@Autowired
	IProdCommentPictureAtomSV prodCommentPictureAtomSV;
	
	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest) {
		PageInfoResponse<ProdCommentPageResponse> result = new PageInfoResponse<ProdCommentPageResponse>();
		//查询评论信息
		ProdComment params = new ProdComment();
		BeanUtils.copyProperties(params, prodCommentPageRequest);
		Integer pageSize = prodCommentPageRequest.getPageSize();
		Integer pageNo = prodCommentPageRequest.getPageNo();
		List<ProdComment> queryPageList = prodCommentAtomSV.queryPageList(params, pageSize, pageNo);
		if(queryPageList == null || queryPageList.size() == 0){
			result.setCount(0);
			result.setPageNo(pageNo);
			result.setPageSize(pageSize);
			result.setResult(null);
		}else{
			//查询条数
			Integer count = prodCommentAtomSV.queryCountByParams(params);
			result.setCount(count);
			result.setPageNo(pageNo);
			result.setPageSize(pageSize);
			List<ProdCommentPageResponse> prodCommentList = getProdCommentResponseList(queryPageList);
			result.setResult(prodCommentList);
		}
		return result;
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
					pictureVO.setPicDir(pricture.getPicAddr());
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

}
