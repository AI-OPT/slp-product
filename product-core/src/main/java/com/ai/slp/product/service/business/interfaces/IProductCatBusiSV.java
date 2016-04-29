package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;

/**
 * 商品类目
 * Created by jackieliu on 16/4/29.
 */
public interface IProductCatBusiSV {
    /**
     * 商品类目分页查询
     *
     * @param productCatParam 查询条件
     * @return
     */
    public PageInfo<ProductCatInfo> queryProductCat(ProductCatParam productCatParam);
}
