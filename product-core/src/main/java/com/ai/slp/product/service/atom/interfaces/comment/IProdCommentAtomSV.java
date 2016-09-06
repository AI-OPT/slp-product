package com.ai.slp.product.service.atom.interfaces.comment;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.ProdComment;

public interface IProdCommentAtomSV {
	
	/**
	 * 根据条件 查询评论集合
	 * @param params
	 * @return
	 * @author jiaxs
	 */
	public List<ProdComment> queryPageList(ProdComment params, Integer pageSize, Integer pageNo);

}
