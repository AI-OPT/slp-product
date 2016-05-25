package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ProductHomeRecommendResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 推荐商品列表
     */
    private PageInfo<ProductHomeRecommend> pageInfo;

    public PageInfo<ProductHomeRecommend> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductHomeRecommend> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
