package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public PageInfoForRes<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery);

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

    /**
     * 删除指定类目
     * @param tenantId
     * @param productCatId
     */
    public void deleteByCatId(String tenantId,String productCatId);

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId,String productCatId);

    /**
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    public Map<ProdCatAttrDef, List<AttrValInfo>> querAttrOfCatByIdAndType(
            String tenantId,String productCatId,String attrType);

    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    public Map<Long,Set<String>> queryAttrAndValIdByCatIdAndType(
            String tenantId,String productCatId,String attrType);
}
