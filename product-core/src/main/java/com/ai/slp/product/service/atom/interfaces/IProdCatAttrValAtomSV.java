package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;

import java.util.List;

/**
 * 商品类目属性值操作
 *
 * Date: 2016年05月01日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public interface IProdCatAttrValAtomSV {

    /**
     * 删除类目属性关系对应属性值
     *
     * @param tenantId
     * @param catAttrId
     * @return
     */
    public int deleteByCat(String tenantId,String catAttrId);

    /**
     * 查询类目属性关系对应的属性值
     *
     * @param tenantId
     * @param catAttrId
     * @return
     */
    public List<ProdCatAttrValue> queryByCatAttrId(String tenantId,String catAttrId);
}
