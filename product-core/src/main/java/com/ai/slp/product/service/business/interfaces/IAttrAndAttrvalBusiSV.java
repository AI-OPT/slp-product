package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.common.param.MapForRes;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.AttrDef;
import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
import com.ai.slp.product.api.normproduct.param.AttrPam;
import com.ai.slp.product.api.normproduct.param.AttrParam;
import com.ai.slp.product.api.normproduct.param.AttrValDef;
import com.ai.slp.product.api.normproduct.param.AttrValInfo;
import com.ai.slp.product.api.normproduct.param.AttrValPageQuery;
import com.ai.slp.product.api.normproduct.param.AttrValParam;
import com.ai.slp.product.api.normproduct.param.AttrValUniqueReq;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;

/**
 * 属性与属性值的相关操作
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IAttrAndAttrvalBusiSV {
    /**
     * 查询指定的属性信息
     * 
     * @return
     * @author lipeng
     */
    public AttrDefInfo selectAttrById(String tenantId,Long attrId);
    
    /**
     * 分页查询属性
     * 
     * @param attrDefParam
     * @return
     * @author lipeng
     */
    public PageInfoForRes<AttrDefInfo> selectAttrs(AttrDefParam attrDefParam);
    
    /**
     * 修改属性信息
     * 
     * @param attrDefParam
     * @return
     * @author lipeng
     */
    public int updateAttr(AttrParam attrParam);
    
    /**
     * 删除属性信息
     * 
     * @param tenantId
     * @param attrId
     * @return
     * @author lipeng
     */
    public int deleteAttr(AttrPam attrPam);
    
    /**
     * 新增属性
     * 
     * @param attrParamList
     * @return
     * @author lipeng
     */
    public int insertAttr(List<AttrParam> attrParamList);
    
    /**
     * 分页查询属性值
     * 
     * @param attrValPageQuery
     * @return
     * @author lipeng
     */
    public PageInfoForRes<AttrValInfo> selectAttrvals(AttrValPageQuery attrValPageQuery);
    
    /**
     * 查询属性值
     * 
     * @param attrValParam
     * @return
     * @author lipeng
     */
    public AttrValInfo queryAttrVal(AttrValUniqueReq attrValParam);
    
    /**
     * 删除属性值
     * 
     * @param tenantId
     * @param attrValueId
     * @return
     * @author lipeng
     */
    public int deleteAttrVal(AttrValUniqueReq attrValUniqueReq);
    
    /**
     * 修改属性值信息
     * 
     * @param attrValParam
     * @return
     * @author lipeng
     */
    public int updateAttrVal(AttrValParam attrValParam);
    
    /**
     * 插入(新增)属性值信息
     * 
     * @param attrValParamList
     * @return
     * @author lipeng
     */
    public int insertAttrVal(List<AttrValParam> attrValParamList);
    
    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @author lipeng
     */
    public MapForRes<AttrDef, List<AttrValDef>> queryAttrAndAttVals();
}
