package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;

import java.util.List;

/**
 * 商品类目属性操作
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProdCatAttrAtomSV {
    
    /**
     * 根据ID查询类目属性
     * 
     * @param tenantId
     * @param productCatId
     * @return
     * @author lipeng
    *  @ApiCode
     */
    public ProdCatAttr selectById(String tenantId,String productCatId);
    
    /**
     * 添加类目属性
     * 
     * @param prodCatAttr
     * @return
     * @author lipeng
    *  @ApiCode
     */
    public int insertProdCatAttr(ProdCatAttr prodCatAttr);

    /**
     * 查询类目关联属性
     *
     * @param tenantId
     * @param catId
     * @return
     */
    public List<ProdCatAttr> queryAttrsByCatId(String tenantId, String catId);

    /**
     * 删除指定类目属性关系
     * @param catId
     * @return
     */
    public int deleteByCatId(String catId);

    /**
     * 查询类目下某个类型的属性
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @return
     */
    public List<ProdCatAttr> queryAttrOfCatByIdAndType(String tenantId, String catId,String attrType);
}
