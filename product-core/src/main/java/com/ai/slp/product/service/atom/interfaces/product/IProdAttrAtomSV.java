package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.search.bo.AttrInfo;

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

    /**
     * 查询某类目下某个属性值被关联的数量
     * @param tenantId
     * @param catId
     * @param attrValDefId
     * @return
     */
    public int countOfAttrValOfCat(String tenantId,  String catId, String attrValDefId);

    /**
     * 根据标识进行商品属性更新
     * @param prodAttr
     * @return
     */
    public int updateByProdAttrId(ProdAttr prodAttr);

    /**
     *
     * @param prodId
     * @return
     */
    public List<AttrInfo> queryAttrOfProdId(String prodId);
}
