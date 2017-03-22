package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.dao.mapper.attach.CatAttrValAttach;
import com.ai.slp.product.dao.mapper.attach.ProdAttrAndValIdAttrch;
import com.ai.slp.product.dao.mapper.bo.ProductCat;

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
    public PageInfo<ProductCat> queryProductCat(ProductCatPageQuery pageQuery);

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
    public void deleteByCatId(String tenantId, String productCatId, Long operId);

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId,String productCatId);

    /**
     * 查询类目下某个类型的属性标识和属性值
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    public List<ProdCatAttrDef> queryAttrOfCatByIdAndType(
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

    /**
     * 删除类目的属性关联
     * @param catAttrVal
     */
    public void deleteAttr(ProdCatAttrVal catAttrVal);

    /**
     * 删除类目的属性值关联
     * @param catAttrVal
     */
    public void deleteAttrVal(ProdCatAttrVal catAttrVal);

    /**
     * 根据名称或首字母查询类目信息
     *
     * @param query
     * @return
     */
    public List<ProdCatInfo> queryByNameOrFirst(ProductCatQuery query);

    /**
     * 类目添加指定属性类型的属性和属性值
     *
     * @param addCatAttrParam
     */
    public void addAttrAndValOfAttrType(ProdCatAttrAddParam addCatAttrParam);

    /**
     * 更新类目属性和属性值
     *
     * @param updateReq
     * @return 更新成功条目数
     */
    public int updateCatAttrAndVal(ProdCatAttrUpdateReq updateReq);

    /**
     * 查询类目下指定类型和指定属性的属性值
     * @param tenantId
     * @param catId
     * @param attrId
     * @param attrType
     * @return
     */
    public List<CatAttrValAttach> queryAttrValOfAttrAndType(String tenantId, String catId, long attrId, String attrType);
    
    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    public List<ProdAttrAndValIdAttrch> queryAttrAndAttrvalueDefId(String tenantId, String productCatId, String attrType);
}
