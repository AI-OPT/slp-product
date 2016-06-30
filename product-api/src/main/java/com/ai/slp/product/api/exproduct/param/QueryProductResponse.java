package com.ai.slp.product.api.exproduct.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryProductResponse extends BaseResponse{
    private static final long serialVersionUID = 1L;
    /**
     * 商品分页查询
     */
    private PageInfo<ProductDataResponse> pageInfo;

    public PageInfo<ProductDataResponse> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductDataResponse> pageInfo) {
        this.pageInfo = pageInfo;
    }

   
}
