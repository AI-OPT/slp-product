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

}
