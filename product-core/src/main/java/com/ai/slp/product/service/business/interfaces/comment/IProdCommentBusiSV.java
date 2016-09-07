package com.ai.slp.product.service.business.interfaces.comment;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;

public interface IProdCommentBusiSV {
	
	public PageInfoResponse<ProdCommentPageResponse> queryPageBySku(ProdCommentPageRequest prodCommentPageRequest);
}
