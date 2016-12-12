package com.ai.slp.product.service.atom.impl.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPictureCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPictureCriteria.Criteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentPictureMapper;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.util.SequenceUtil;

@Component
public class ProdCommentPictureAtomSVImpl implements IProdCommentPictureAtomSV {

	@Autowired
	ProdCommentPictureMapper prodCommentPictureMapper;
	
	@Override
	public List<ProdCommentPicture> queryPictureListByCommentId(String commentId) {
		ProdCommentPictureCriteria example = new ProdCommentPictureCriteria();
		
		//example.setOrderByClause("CREATE_TIME,SERIAL_NUMBER");
		
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCommentIdEqualTo(commentId);
		createCriteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCommentPictureMapper.selectByExample(example );
	}

	@Override
	public String createPicture(ProdCommentPicture prodCommentPicture) {
		Long pictureDefId = SequenceUtil.createProdCommentPictureDefId();
		prodCommentPicture.setProdCommentPicId(Long.toString(pictureDefId));
		prodCommentPicture.setState(CommonConstants.STATE_ACTIVE);
		prodCommentPicture.setCreateTime(DateUtil.getSysDate());
		int insert = prodCommentPictureMapper.insert(prodCommentPicture);
		if(insert > 0){
			return prodCommentPicture.getProdCommentPicId();
		}else{
			return null;
		}
	}

}
