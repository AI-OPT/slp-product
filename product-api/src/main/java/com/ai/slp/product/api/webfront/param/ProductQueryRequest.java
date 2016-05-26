package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

public class ProductQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * sku标识
     */
    private String skuId;
    /**
     * 商品类目标识
     */
    private String  productCatId;

    private PageInfo<ProductData> pageInfo;

    public PageInfo<ProductData> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductData> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

}
