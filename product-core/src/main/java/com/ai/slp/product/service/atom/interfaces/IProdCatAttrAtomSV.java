package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;

import java.util.List;

/**
 * 商品类目属性操作
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IProdCatAttrAtomSV {
    
    /**
     * 根据ID查询类目属性
     * 
     * @param tenantId
     * @param productCatId
     * @return
     * @author lipeng16
    *  @ApiCode
     */
    public ProdCatAttr selectById(String tenantId,String productCatId);
    
    /**
     * 添加类目属性
     * 
     * @param prodCatAttr
     * @return
     * @author lipeng16
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
     * @param operId
     * @return
     */
    public int deleteByCatId(String tenantId,String catAttrId,Long operId);

    /**
     * 删除类目的指定属性
     *
     * @param tenantId
     * @param catId
     * @param attrId
     
     * @return
     */
    public int deleteByCatAttrId(String tenantId, String catId,Long attrId, Long operId);

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
     * @author lipeng16
    *  @ApiCode
     */
    public int selectCatNumByAttrId(String tenantId, Long attrId );
    
    /**
     * 通过属性标识查看商品类目属性信息
     */
    public List<ProdCatAttr> queryCatAttrByAttrId(String tenantId,Long attrId);

    /**
     * 查询类目销售属性中需要上传图片的属性
     *
     * @param tenantId
     * @param catId
     * @return
     */
    public List<ProdCatAttr> queryAttrOfPicByIdAndSale(String tenantId, String catId);
}
