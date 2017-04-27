package com.ai.slp.product.service.atom.impl.comment;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCommentConstants;
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
	public List<ProdComment> queryPageList(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd,
			Integer pageSize, Integer pageNo) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		if(pageSize != null && pageNo != null){
			example.setLimitStart((pageNo -1) * pageSize);
			example.setLimitEnd(pageSize);
		}
		
		//example.setOrderByClause("COMMENT_TIME desc");
		
		setQueryCriteria(params, commentTimeBegin, commentTimeEnd, example);
		return prodCommentMapper.selectByExample(example);
	}
	
	@Override
	public Integer queryCountByParams(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		setQueryCriteria(params, commentTimeBegin, commentTimeEnd, example);
		return prodCommentMapper.countByExample(example);
	}

	/**
	 * 设置查询条件
	 * @param params
	 * @param commentTimeBegin
	 * @param commentTimeEnd
	 * @param example
	 */
	private void setQueryCriteria(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd,
			ProdCommentCriteria example) {
		Criteria criteria = example.createCriteria();
		String tenantId = params.getTenantId();
		if(!StringUtil.isBlank(tenantId)){
			criteria.andTenantIdEqualTo(tenantId);
		}
		String commentId = params.getCommentId();
		if(!StringUtil.isBlank(commentId)){
			criteria.andCommentIdEqualTo(commentId);
		}
		String supplierId = params.getSupplierId();
		if(!StringUtil.isBlank(supplierId)){
			criteria.andSubOrderIdEqualTo(supplierId);
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
		Long shopScoreFw = params.getShopScoreFw();
		if(shopScoreFw != null){
			criteria.andShopScoreFwEqualTo(shopScoreFw);
		}
		Long shopScoreWl = params.getShopScoreWl();
		if(shopScoreWl != null){
			criteria.andShopScoreWlEqualTo(shopScoreWl);
		}
		String orderId = params.getOrderId();
		if(!StringUtil.isBlank(orderId)){

			//criteria.andOrderIdLike("%"+orderId+"%");

			criteria.andOrderIdEqualTo(orderId);
		}
		String standedProdId = params.getStandedProdId();
		if(!StringUtil.isBlank(standedProdId)){

			//criteria.andStandedProdIdLike("%"+standedProdId+"%");

			criteria.andStandedProdIdEqualTo(standedProdId);
		}
		if(commentTimeBegin != null){
			criteria.andCommentTimeGreaterThanOrEqualTo(commentTimeBegin);
		}
		if(commentTimeEnd != null){
			criteria.andCommentTimeLessThanOrEqualTo(commentTimeEnd);
		}
		criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
	}
	
	@Override
	public List<ProdComment> queryPageListByProductId(ProdComment params, Integer pageSize, Integer pageNo) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		if(pageSize != null && pageNo != null){
			example.setLimitStart((pageNo -1) * pageSize);
			example.setLimitEnd(pageSize);
		}
		
		//example.setOrderByClause("COMMENT_TIME desc");
		
		setQueryCriteria(params,example);
		return prodCommentMapper.selectByExample(example);
	}

	@Override
	public Integer queryCountByProductId(ProdComment params) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		setQueryCriteria(params, example);
		return prodCommentMapper.countByExample(example);
	}

	/**
	 * 查询商品下评价的数量
	 *
	 * @param skuId
	 * @param isDiscard 是否包含废弃的状态
	 * @return
	 */
	@Override
	public int countBySkuId(String skuId, boolean isDiscard) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		ProdCommentCriteria.Criteria criteria = example.createCriteria();
		criteria.andSkuIdEqualTo(skuId);
		if (!isDiscard){
			criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		}
		return prodCommentMapper.countByExample(example);
	}

	private void setQueryCriteria(ProdComment params, ProdCommentCriteria example) {
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
		String standedProdId = params.getStandedProdId();
		if(!StringUtil.isBlank(standedProdId)){
			criteria.andStandedProdIdEqualTo(standedProdId);
		}
		criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
	}

	@Override
	public String createProdComment(ProdComment params) {
		Long prodCommentId = SequenceUtil.createProdCommentDefId();
		params.setCommentId(Long.toString(prodCommentId));
		params.setCommentTime(DateUtil.getSysDate());
		params.setState(CommonConstants.STATE_ACTIVE);
		params.setReplyState(ProductCommentConstants.ReplyState.NO);
		prodCommentMapper.insert(params);
		return params.getCommentId();
	}
	
	@Override
	public String createProdComment(ProdComment params,String commentId) {
		params.setCommentId(commentId);
		params.setCommentTime(DateUtil.getSysDate());
		params.setState(CommonConstants.STATE_ACTIVE);
		params.setReplyState(ProductCommentConstants.ReplyState.NO);
		prodCommentMapper.insert(params);
		return params.getCommentId();
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

	@Override
	public int updateStateByIds(String state, String operId, String tenantId, List<String> commentIdList) {
		ProdComment record = new ProdComment();
		record.setOperId(operId);
		record.setOperTime(DateUtil.getSysDate());
		record.setState(state);
		ProdCommentCriteria example = new ProdCommentCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andCommentIdIn(commentIdList);
		return prodCommentMapper.updateByExampleSelective(record, example);
	}

	@Override
	public ProdComment queryByCommentId(String commentId) {
		
		return prodCommentMapper.selectByPrimaryKey(commentId);
	}
}
