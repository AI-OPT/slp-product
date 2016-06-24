package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;

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

    /**
     * 查询商品的其他设置内容
     * @param tenantId
     * @param prodId
     * @return
     */
    public OtherSetOfProduct queryOtherSetOfProd(String tenantId, String prodId);

    /**
     * 更新产品编辑信息
     * @param productInfo
     */
    public void updateProdEdit(ProductInfoForUpdate productInfo);
}
