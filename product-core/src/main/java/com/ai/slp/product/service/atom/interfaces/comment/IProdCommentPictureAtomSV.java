package com.ai.slp.product.service.atom.interfaces.comment;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;


public interface IProdCommentPictureAtomSV {
	
	List<ProdCommentPicture> queryPictureListByCommentId(String commentId);
	
	String createPicture(ProdCommentPicture prodCommentPicture);
}
