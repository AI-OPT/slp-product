package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;

/**
 * 商品属性操作
 *
 * Created by jackieliu on 16/4/27.
 */
public interface IProdAttrDefAtomSV {
    /**
     * 添加商品属性
     * @param prodAttrDef
     * @return
     */
    public int installObj(ProdAttrDef prodAttrDef);

    /**
     * 根据标识查询商品属性
     *
     * @param tenantId 租户id
     * @param attrId 属性标识
     * @return
     */
    public ProdAttrDef selectById(String tenantId,Long attrId);

    /**
     * 删除指定标识的商品属性
     *
     * @param tenantId 租户id
     * @param attrId 商品属性标识
     * @return
     */
    public int deleteById(String tenantId,Long attrId);

}
