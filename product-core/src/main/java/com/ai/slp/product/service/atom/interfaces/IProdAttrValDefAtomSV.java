package com.ai.slp.product.service.atom.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.vo.AttrValPageQueryVo;

import java.util.List;

/**
 * 属性值操作
 * 
 * Date: 2016年4月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IProdAttrValDefAtomSV {
    /**
     * 依据属性值ID查询商品属性值
     * 
     * @param tenantId 租户ID
     * @param attrvalueDefId 属性值ID
     * @return 商品属性值对象
     * @author lipeng16
     */
    public ProdAttrvalueDef selectById(String tenantId,String attrvalueDefId);
    /**
     * 添加属性值
     * 
     * @param prodAttrvalueDef
     * @return 
     * @author lipeng16
     */
    public int insertProdAttrVal(ProdAttrvalueDef prodAttrvalueDef);
    /**
     * 修改属性值
     * 
     * @param prodAttrvalueDef
     * @return
     * @author lipeng16
     */
    public int updateProdAttrVal(ProdAttrvalueDef prodAttrvalueDef);
    /**
     * 删除属性值
     * 
     * @param tenantId
     * @param attrvalueDefId
     * @param operId
     * @return
     * @author lipeng16
     */
    public int deleteProdAttrVal(String tenantId,String attrvalueDefId, Long operId);
    
    /**
     * 根据信息分页查询属性值信息
     * 
     * @param pageQueryVo
     * @return
     * @author lipeng16
     */
    public PageInfo<ProdAttrvalueDef> selectAttrValPage(AttrValPageQueryVo pageQueryVo);

    /**
     * 查询属性对应的所有有效属性值
     * 
     * @return
     * @author lipeng16
     */
    public List<ProdAttrvalueDef> selectAttrValForAttr(String tenantId, Long attrId);
    
    /**
     * 查询属性对应的实行值的数量
     * 
     */
    public int selectAttrValNum(String tenantId, Long attrId);
}
