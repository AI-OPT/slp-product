package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;

import java.util.List;

/**
 * 商品属性值原子操作
 * Created by jackieliu on 16/6/1.
 */
public interface IProdAttrAtomSV {

    /**
     * 查询指定商品下某个属性的属性值
     * @param tenantId
     * @param prodId
     * @param attrId
     * @return
     */
    public List<ProdAttr> queryOfProdAndAttr(String tenantId,String prodId,Long attrId);

    /**
     * 查询某个商品中某个属性对应的属性值信息
     * @param tenantId
     * @param prodId
     * @param attrId
     * @param attrValId
     * @return
     */
    public ProdAttr queryByProdAndAttrAndAttrVal(String tenantId,String prodId,Long attrId,String attrValId);

    /**
     * 添加属性值信息
     * @param prodAttr
     * @return
     */
    public int installProdAttr(ProdAttr prodAttr);
}
