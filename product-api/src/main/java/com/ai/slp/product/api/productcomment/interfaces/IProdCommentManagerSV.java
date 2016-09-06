package com.ai.slp.product.api.productcomment.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;

/**
 * 商品评价
 *
 * Date: 2016年9月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author jiaxs
 */
@Path("/prodCommentManager")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProdCommentManagerSV {
	
	/**
	 * 查询sku商品下的商品评论
	 * @param prodCommentPageRequest
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode	PROD_COMM_0002
	 * @RestRelativeURL prodCommentManager/queryPageInfoBySku
	 */
	@POST
	@Path("/queryPageInfoBySku")
	public PageInfoResponse<ProdCommentPageResponse> queryPageInfoBySku(ProdCommentPageRequest prodCommentPageRequest);

}
