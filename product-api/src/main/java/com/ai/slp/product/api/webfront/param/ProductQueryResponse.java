package com.ai.slp.product.api.webfront.param;


import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ProductQueryResponse extends BaseResponse{
    private static final long serialVersionUID = 1L;

    /**
     * 商品分页查询
     */
    private PageInfo<ProductData> pageInfo;

    public PageInfo<ProductData> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductData> pageInfo) {
        this.pageInfo = pageInfo;
    }
    
}
