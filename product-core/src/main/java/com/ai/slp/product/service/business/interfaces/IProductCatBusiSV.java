package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.normproduct.param.PageInfoWrapper;
import com.ai.slp.product.api.normproduct.param.ProductCatInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatPageQuery;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;

import java.util.List;

/**
 * 商品类目
 * Created by jackieliu on 16/4/29.
 */
public interface IProductCatBusiSV {
    /**
     * 商品类目分页查询
     *
     * @param pageQuery 查询条件
     * @return
     */
    public PageInfoWrapper<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery);

    /**
     * 批量添加类目
     * @param pcpList
     */
    public void addCatList(List<ProductCatParam> pcpList);

    /**
     * 查询指定类目详细信息
     *
     * @param tenantId 租户id
     * @param productCatId 类目标识
     * @return
     */
    public ProductCatInfo queryByCatId(String tenantId,String productCatId);

    /**
     * 更新类目信息
     * @param catParam
     */
    public void updateByCatId(ProductCatParam catParam);
}
