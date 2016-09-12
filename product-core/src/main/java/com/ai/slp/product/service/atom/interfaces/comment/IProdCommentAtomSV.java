package com.ai.slp.product.service.atom.interfaces.comment;

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
	public List<ProdComment> queryPageList(ProdComment params, Integer pageSize, Integer pageNo);
	
	/**
	 * 查询条数
	 * @param params
	 * @return
	 */
	public Integer queryCountByParams(ProdComment params);
	
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
	public String prodCommentReply(ProdCommentReply CommentReply);

}