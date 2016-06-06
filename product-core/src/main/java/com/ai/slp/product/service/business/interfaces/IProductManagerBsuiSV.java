package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;

/**
 * 销售商品管理
 * Created by jackieliu on 16/6/6.
 */
public interface IProductManagerBsuiSV {
    /**
     * 商品管理中分页查询商品信息
     * @param queryReq
     * @return
     */
    public PageInfoResponse<ProductEditUp> queryPageForEdit(ProductEditQueryReq queryReq);
}
