package com.ai.slp.product.api.productcomment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class ProdCommentManagerSVImpl implements IProdCommentManagerSV {

	@Autowired
	IProdCommentBusiSV prodCommentBusiSV;
	
	@Override
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest)throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentPageRequest.getTenantId());
		return prodCommentBusiSV.queryPageBySku(prodCommentPageRequest);
	}

	@Override
	public BaseResponse createProdComment(ProdCommentCreateRequest prodCommentCreateRequest) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(prodCommentCreateRequest.getTenantId());
		return prodCommentBusiSV.createProdComment(prodCommentCreateRequest);
	}

}
