package com.ai.slp.product.api.productcomment.impl;

import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {

	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
