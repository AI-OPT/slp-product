package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;
import java.util.Set;

import com.ai.slp.product.dao.mapper.attach.ProdFastSkuAttach;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;

/**
 * 商品SKU信息
 * Created by jackieliu on 16/5/6.
 */
public interface IProdSkuAtomSV {
    /**
     * 查询商品的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public List<ProdSku> querySkuOfProd(String tenantId,String prodId);
    /**
     * 查询商品的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @param hasDiscard true:包含废弃状态;false:不包含废弃状态
     * @return
     */
    public List<ProdSku> querySkuOfProd(String tenantId,String prodId,boolean hasDiscard);

    /**
     * 废弃指定SKU单品
     * @return
     */
    public int updateSkuById(ProdSku prodSku);

    /**
     * 添加商品SKU信息
     * @param prodSku
     * @return
     */
    public int createObj(ProdSku prodSku);
    /**
     * 通过SKU标识查询SKU信息
     *
     * @param tenantId
     * @param skuId
     * @return
     * @author lipeng16
     */
    public ProdSku querySkuById(String tenantId,String skuId);

    /**
     * 根据属性串查询SKU信息
     * @param tenantId
     * @param attrs
     * @return
     */
    public ProdSku querySkuByAttrs(String tenantId,String attrs);

    /**
     * 查询全国范围的快充产品
     *
     * @param tenantId
     * @param productCatId
     * @param basicOrgId
     * @param userType
     * @param userId
     * @param attrId
     * @return
     */
    public List<ProdFastSkuAttach> queryNationFastProd(
            String tenantId,String productCatId,String basicOrgId,String userType, String userId,Long attrId
    );

    /**
     * 查询指定地域的快充产品
     * @param tenantId
     * @param productCatId
     * @param basicOrgId
     * @param userType
     * @param userId
     * @param attrId
     * @param provCode
     * @return
     */
    public List<ProdFastSkuAttach> queryLocalFastProd(
            String tenantId,String productCatId,String basicOrgId,String userType,String userId,Long attrId,Integer provCode
    );
    
    /**
     * 通过销售属性串获得sku集合
     * @param tenantId
     * @param skuSaleAttrs
     * @return
     * @author jiaxs
     */
    public List<ProdSku> queryProdSkuBySaleAttrs(String tenantId, String groupId,Set<String> skuSaleAttrs);

    /**
     * 通过SKU标识查询SKU信息
     *
     * @param tenantId
     * @param skuId
     * @return
     * @author lipeng16
     */
    public ProdSku querySkuById(String tenantId,String skuId,boolean hasDiscard);

    /**
     * 查询指定商品的SKU信息,用于添加搜索信息
     * @param productId
     * @return
     */
    public List<ProdSkuInfoSes> queryOfProdForSearch(String productId);

    /**
     * 为搜索信息,添加所有在售商品
     * @param limitStart
     * @param limitEnd
     * @return
     */
    public List<ProdSkuInfoSes> queryInSaleForSearch(Integer limitStart,Integer limitEnd);
}
