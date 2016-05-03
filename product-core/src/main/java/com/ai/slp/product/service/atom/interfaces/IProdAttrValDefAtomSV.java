package com.ai.slp.product.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrValPageQuery;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;

/**
 * 属性值操作
 * 
 * Date: 2016年4月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProdAttrValDefAtomSV {
    /**
     * 依据属性ID查询商品属性值
     * 
     * @param tenantId 租户ID
     * @param attrValueId 属性值ID
     * @return 商品属性值对象
     * @author lipeng
     */
    public ProdAttrvalueDef selectById(String tenantId,String attrValueId);
    /**
     * 添加属性值
     * 
     * @param prodAttrvalueDef
     * @return 
     * @author lipeng
     */
    public int insertProdAttrVal(ProdAttrvalueDef prodAttrvalueDef);
    /**
     * 修改属性值
     * 
     * @param prodAttrvalueDef
     * @return
     * @author lipeng
     */
    public int updateProdAttrVal(ProdAttrvalueDef prodAttrvalueDef);
    /**
     * 删除属性值
     * 
     * @param tenantId
     * @param attrValueId
     * @return
     * @author lipeng
     */
    public int deleteProdAttrVal(String tenantId,String attrValueId);
    
    /**
     * 根据属性信息分页查询属性值信息
     * 
     * @param tenantId
     * @param attrId
     * @return
     * @author lipeng
     */
    public PageInfo<ProdAttrvalueDef> selectAttrValPage(AttrValPageQuery attrValPageQuery);

    /**
     * 查询属性对应的所有有效属性值
     * 
     * @return
     * @author lipeng
     */
    public List<ProdAttrvalueDef> selectAttrValForAttr(Long attrId);
}
