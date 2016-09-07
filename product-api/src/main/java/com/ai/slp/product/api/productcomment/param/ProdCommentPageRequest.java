package com.ai.slp.product.api.productcomment.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcomment.interfaces.IProdCommentManagerSV;

public class ProdCommentPageRequest extends BaseInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品评价分数
	 */
	private String commentBody;
	
	/**
	 * 商品SKU id
	 */
	@NotBlank(message = "skuId不能为空",groups = { IProdCommentManagerSV.QueryPageInfoBySku.class})
	private String skuId;
	
	@NotBlank(message = "pageSize不能为空",groups = { IProdCommentManagerSV.QueryPageInfoBySku.class})
	private Integer pageSize;
	
	@NotBlank(message = "PageNo不能为空",groups = { IProdCommentManagerSV.QueryPageInfoBySku.class})
	private Integer PageNo;
	
	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return PageNo;
	}

	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}
}
