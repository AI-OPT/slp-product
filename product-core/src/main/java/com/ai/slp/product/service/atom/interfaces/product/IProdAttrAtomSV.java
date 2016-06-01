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
}
