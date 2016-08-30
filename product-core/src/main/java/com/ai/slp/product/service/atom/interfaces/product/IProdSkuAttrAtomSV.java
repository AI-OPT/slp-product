package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;

import java.util.List;

/**
 * Created by jackieliu on 16/5/10.
 */
public interface IProdSkuAttrAtomSV {

    //查询商品SKU下属性值被使用次数
    public int queryAttrValNumOfSku(String tenantId,String prodId,String valId);

    /**
     * 废弃SKU单品下的属性
     *
     * @param tenantId
     * @param skuId sku单品标识
     * @param operId 操作者ID
     * @return
     */
    public int discardAttrOfSku(String tenantId,String skuId,Long operId);

    /**
     * 添加SKU属性值
     * @param prodSkuAttr
     * @return
     */
    public int createAttr(ProdSkuAttr prodSkuAttr);

    /**
     * 查询SKU单品中某个属性的属性值
     * @param tenantId
     * @param skuId
     * @param attrId
     * @return
     */
    public ProdSkuAttr queryAttrValBySkuIdAndAttr(String tenantId,String skuId,Long attrId);

    /**
     * 根据商品标识和属性标识查询对应的属性值标识
     *
     * @param tenantId
     * @param prodId
     * @param attrId
     * @return
     */
    public List<String> queryAttrValIdByProdIdAndAttrId(String tenantId, String prodId, Long attrId);

    /**
     * 查询SKU的属性及属性值
     * @param tenantId
     * @param skuId
     * @return
     */
    public List<ProdSkuAttr> queryBySkuId(String tenantId,String skuId);

    /**
     * 查询SKU的属性及属性值
     * @param tenantId
     * @param skuId
     * @param hasDiscard
     * @return
     */
    public List<ProdSkuAttr> queryBySkuId(String tenantId,String skuId,boolean hasDiscard);

}
