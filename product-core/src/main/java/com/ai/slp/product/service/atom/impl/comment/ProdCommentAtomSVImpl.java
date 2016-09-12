package com.ai.slp.product.service.atom.impl.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria.Criteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentReplyMapper;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.util.SequenceUtil;

@Component
public class ProdCommentAtomSVImpl implements IProdCommentAtomSV {

	@Autowired
	ProdCommentMapper prodCommentMapper;
	@Autowired
	ProdCommentReplyMapper prodCommentReplyMapper;
	
	@Override
	public List<ProdComment> queryPageList(ProdComment params, Integer pageSize, Integer pageNo) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		if(pageSize != null && pageNo != null){
			example.setLimitStart((pageNo -1) * pageSize);
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("COMMENT_TIME desc");
		Criteria criteria = example.createCriteria();
		String tenantId = params.getTenantId();
		if(!StringUtil.isBlank(tenantId)){
			criteria.andTenantIdEqualTo(tenantId);
		}
		Long shopScoreMs = params.getShopScoreMs();
		if(shopScoreMs != null){
			if(shopScoreMs == 1){
				criteria.andShopScoreMsLessThan(3L);
			}else if(shopScoreMs == 3){
				criteria.andShopScoreMsEqualTo(3L);
			}else{
				criteria.andShopScoreMsGreaterThan(3L);
			}
		}
		String skuId = params.getSkuId();
		if (!StringUtil.isBlank(skuId)) {
			criteria.andSkuIdEqualTo(skuId);
		}
		String standedProdId = params.getStandedProdId();
		if(!StringUtil.isBlank(standedProdId)){
			criteria.andStandedProdIdEqualTo(standedProdId);
		}
		criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCommentMapper.selectByExample(example);
	}

	@Override
	public Integer queryCountByParams(ProdComment params) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		Criteria criteria = example.createCriteria();
		String tenantId = params.getTenantId();
		if(!StringUtil.isBlank(tenantId)){
			criteria.andTenantIdEqualTo(tenantId);
		}
		Long shopScoreMs = params.getShopScoreMs();
		if(shopScoreMs != null){
			if(shopScoreMs == 1){
				criteria.andShopScoreMsLessThan(3L);
			}else if(shopScoreMs == 3){
				criteria.andShopScoreMsEqualTo(3L);
			}else{
				criteria.andShopScoreMsGreaterThan(3L);
			}
		}
		String skuId = params.getSkuId();
		if (!StringUtil.isBlank(skuId)) {
			criteria.andSkuIdEqualTo(skuId);
		}
		String standedProdId = params.getStandedProdId();
		if(!StringUtil.isBlank(standedProdId)){
			criteria.andStandedProdIdEqualTo(standedProdId);
		}
		criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCommentMapper.countByExample(example);
	}

	@Override
	public String createProdComment(ProdComment params) {
		Long prodCommentId = SequenceUtil.createProdCommentDefId();
		params.setCommentId(Long.toString(prodCommentId));
		params.setCommentTime(DateUtil.getSysDate());
		params.setState(CommonConstants.STATE_ACTIVE);
		int count = prodCommentMapper.insert(params);
		if(count > 0){
			return params.getCommentId();
		}else{
			return null;
		}
	}

	@Override
	public String prodCommentReply(ProdCommentReply commentReply) {
		Long ReplyId = SequenceUtil.prodCommentReplyDefId();
		commentReply.setReplyId(Long.toString(ReplyId));
		commentReply.setReplyTime(DateUtil.getSysDate());
		int count = prodCommentReplyMapper.insert(commentReply);
		if (count > 0) {
			return commentReply.getReplyId();
		}else {
			return null;
		}
	}

}
