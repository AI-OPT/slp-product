package com.ai.slp.product.service.atom.impl.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria.Criteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentMapper;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;

@Component
public class ProdCommentAtomSVImpl implements IProdCommentAtomSV {

	@Autowired
	ProdCommentMapper prodCommentMapper;
	
	@Override
	public List<ProdComment> queryPageList(ProdComment params, Integer pageSize, Integer pageNo) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		if(pageSize != null && pageNo != null){
			example.setLimitStart((pageNo -1) * pageSize);
			example.setLimitEnd(pageSize);
		}
		Criteria criteria = example.createCriteria();
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
		prodCommentMapper.selectByExample(example);
		return null;
	}

}
