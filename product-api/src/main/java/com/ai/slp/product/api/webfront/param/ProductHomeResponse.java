package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ProductHomeResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 话费流量商品列表
     */
    private PageInfo<ProductHome> pageInfo;

    public PageInfo<ProductHome> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductHome> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
