package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;

import java.sql.Timestamp;
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
     * @param tenantId
     * @param catAttrId
     * @return
     */
    public int deleteByCatId(String tenantId,String catAttrId,Long operId, Timestamp operTime);

    /**
     * 删除类目的指定属性
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param operId
     * @param operTime
     * @return
     */
    public int deleteByCatAttrId(String tenantId, String catId,Long attrId, Long operId, Timestamp operTime);

    /**
     * 查询类目下某个类型的属性
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @return
     */
    public List<ProdCatAttr> queryAttrOfCatByIdAndType(String tenantId, String catId,String attrType);

    /**
     * 查询类目下指定属性类型和属性标识的关联信息
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param attrType
     * @return
     */
    public ProdCatAttr queryByCatIdAndTypeAndAttrId(String tenantId, String catId,Long attrId,String attrType);

    /**
     * 更新类目的指定属性
     *
     * @param prodCatAttr
     * @return
     */
    public int update(ProdCatAttr prodCatAttr);
    
    /**
     * 通过属性ID查询关联类目数量
     * 
     * @param tenantId
     * @param attrId
     * @return
     * @author lipeng
    *  @ApiCode
     */
    public int selectCatNumByAttrId(String tenantId, Long attrId );
}
