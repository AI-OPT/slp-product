package com.ai.slp.product.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.vo.AttrAndValPageQueryVo;

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
    public int deleteById(String tenantId,Long attrId, Long operId);
    
    /**
     * 分页查询属性
     * 
     * @param attrAndValPageQueryVo
     * @return
     * @author lipeng16
     */
    public PageInfo<ProdAttrDef> selectPageAttrs(AttrAndValPageQueryVo attrAndValPageQueryVo);
    
    /**
     * 通过属性信息查询拥有属性值数量
     * 
     * @param tenantId
     * @param attrId
     * @return
     * @author lipeng16
     */
    public int selectAttrvalNum(String tenantId,Long attrId);
    
    /**
     * 修改属性信息
     * 
     * @param attrDefParam
     * @return
     * @author lipeng16
     */
    public int  updateAttr(ProdAttrDef prodAttrDef);
    
    /**
     * 查询所有有效属性
     * 
     * @return
     * @author lipeng16
     */
    public List<ProdAttrDef> selectAllAttrs(String tenantId);
    

}
