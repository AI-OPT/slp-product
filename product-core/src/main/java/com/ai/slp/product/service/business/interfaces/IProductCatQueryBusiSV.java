package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.productcat.param.ProductCatInfo;

import java.util.List;

/**
 * 商品类目查询业务
 *
 * Created by jackieliu on 16/7/22.
 */
public interface IProductCatQueryBusiSV {
    /**
     * 查询指定标识的类目信息
     * @param tenantId
     * @param catId
     * @return
     */
    public ProductCatInfo queryById(String tenantId,String catId);

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId, String productCatId);

    /**
     * 查询类目的下级类目
     *
     * @param tenantId
     * @param catId
     * @return
     */
    public List<ProductCatInfo> queryChileOfCatById(String tenantId,String catId);

    /**
     * 查询指定级别下的类目信息
     * @param tenantId
     * @param level
     * @return
     */
    public List<ProductCatInfo> queryByLevel(String tenantId,Short level);
}
