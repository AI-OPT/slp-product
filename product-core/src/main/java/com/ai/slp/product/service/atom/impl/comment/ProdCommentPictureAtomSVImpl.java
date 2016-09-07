package com.ai.slp.product.service.atom.impl.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPictureCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPictureCriteria.Criteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentPictureMapper;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;

@Component
public class ProdCommentPictureAtomSVImpl implements IProdCommentPictureAtomSV {

	@Autowired
	ProdCommentPictureMapper prodCommentPictureMapper;
	
	@Override
	public List<ProdCommentPicture> queryPictureListByCommentId(String commentId) {
		ProdCommentPictureCriteria example = new ProdCommentPictureCriteria();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCommentIdEqualTo(commentId);
		createCriteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCommentPictureMapper.selectByExample(example );
	}

}
