package com.ai.slp.product.service.atom.interfaces.comment;

import java.sql.Timestamp;
import java.util.List;

import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;


public interface IProdCommentAtomSV {
	
	/**
	 * 根据条件 查询评论集合
	 * @param params
	 * @return
	 * @author jiaxs
	 */
	public List<ProdComment> queryPageListByProductId(ProdComment params, Integer pageSize, Integer pageNo);
	
	/**
	 * 根据条件 查询评论集合
	 * @param params
	 * @return
	 * @author jiaxs
	 */
	public List<ProdComment> queryPageList(ProdComment params, Timestamp commentTimeBegin, Timestamp commentTimeEnd, Integer pageSize, Integer pageNo);
	
	/**
	 * 查询条数
	 * @param params
	 * @return
	 */
	public Integer queryCountByProductId(ProdComment params);

	/**
	 * 查询单品的评价的数量
	 * @param prodId
	 * @param isDiscard 是否包含废弃的状态
	 * @return
     */
	public int countBySkuId(String prodId, boolean isDiscard);
	
	/**
	 * 查询条数
	 * @param params
	 * @return
	 */
	public Integer queryCountByParams(ProdComment params,Timestamp commentTimeBegin, Timestamp commentTimeEnd);
	
	/**
	 * 创建商品评论
	 * @param params
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	public String createProdComment(ProdComment params);
	
	/**
	 * 商品评论回复
	 * @param params
	 * @return
	 * @author jiawen
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	public String prodCommentReply(ProdCommentReply commentReply);
	
	/**
	 * 根据评论编码查询评论
	 * @param commentId
	 * @return
	 * @author jiawen
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	public ProdComment queryByCommentId(String commentId);
	
	/**
	 * 修改评价状态
	 * @param state
	 * @param operId
	 * @param commentIdList
	 */
	public int updateStateByIds(String state, String operId, String tenantId, List<String> commentIdList);

	/**
	 * 创建是匹配评论
	 * @param params
	 * @param commentId
	 * @return
	 * @author
	 */
	String createProdComment(ProdComment params, String commentId);

}
