package com.ai.slp.product.service.business.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.AttrInfo;

/**
 * 商品SKU业务操作
 * Created by jackieliu on 16/5/12.
 */
public interface IProdSkuBusiSV {
	
	/**
	 * 保存SKU
	 * @param product
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public ProdSku createSkuProduct(Product product);

    /**
     * 产生库存组对应商品的SKU
     * 完全使用配置到标准品的销售属性
     *
     * @param group
     */
    public int createSkuOfProduct(String tenantId,String groupId,List<AttrValRequest> attrValList, Long operId);
    
    /**
     * 产生库存组对应商品的SKU
     * 完全使用配置到标准品的销售属性
     *
     */
    public int createSkuOfAttrValue(String tenantId,String groupId,Map<Long, List<String>> attrValMap,Long operId);

    /**
     * 更新商品SKU信息
     * @param saveInfo
     */
    public void updateSkuOfProduct(SkuInfoMultSave saveInfo);

    /**
     * 查询指定商品下的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public SkuSetForProduct querySkuByProdId(String tenantId,String supplierId, String prodId);

    /**
     * 根据SKU标识或SKU属性串查询SKU的信息
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public List<ProdCatAttrAttch> querySkuDetail(String tenantId,Product product,String skuAttrs);
    /**
     * 根据SKU标识或SKU属性串查询SKU的信息
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public ProductSKUResponse querySkuDetail(String tenantId,String skuId,String skuAttrs);

    /**
     * 根据SKU标识或SKU属性串查询SKU的所有属性信息
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public List<AttrInfo> querySkuAttr(String tenantId,String skuId,String skuAttrs);

    /**
     * 查询库组下SKU的信息
     * @param tenantId
     * @param supplierId
     * @param groupId
     * @return
     */
    public SkuSetForProduct querySkuByStoGroupId(String tenantId,String supplierId, String groupId);
    
    /**
     * 废除sku
     * @param tenantId
     * @param groupId
     * @param attrValMap
     * @return
     */
    public int discardSkuOfAttrValue(String tenantId,String groupId,Map<Long, List<String>> attrValMap, Long operId);

    /**
     * 查询某个库存下的SKU信息
     * @param tenantId
     * @param supplierId
     * @param storageId
     * @return
     */
    public SkuSetForProduct querySkuByStorageId(String tenantId,String supplierId, String storageId);
    /**
     * 根据SKU标识或SKU属性串查询SKU的信息(订单专用)
     * @param tenantId
     * @param skuId
     * @param skuAttrs
     * @return
     */
    public ProductSKUResponse querySkuDetail4ShopCart(String tenantId,String skuId,String skuAttrs);
}
