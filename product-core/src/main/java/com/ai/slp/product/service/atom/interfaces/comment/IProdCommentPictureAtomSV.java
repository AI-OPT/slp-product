package com.ai.slp.product.service.atom.interfaces.comment;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;


public interface IProdCommentPictureAtomSV {
	
	/**
	 * 商品评论图片原子服务
	 * @param commentId
	 * @return
	 * @author
	 */
	List<ProdCommentPicture> queryPictureListByCommentId(String commentId);
	
	/**
	 * 创建商品评论图片服务
	 * @param prodCommentPicture
	 * @return
	 * @author
	 */
	String createPicture(ProdCommentPicture prodCommentPicture);
}
