package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.param.*;

import java.util.List;

/**
 * 属性与属性值的相关操作
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface IAttrAndAttrvalBusiSV {
    
    /**
     * 分页查询属性
     * 
     * @param attrDefParam
     * @return
     * @author lipeng16
     */
    public PageInfoResponse<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam);
    
    /**
     * 查询指定的属性信息
     * 
     * @return
     * @author lipeng16
     */
    public AttrInfo queryAttrById(String tenantId,Long attrId);
    
    /**
     * 新增属性
     * 
     * @param attrParamList
     * @return
     * @author lipeng16
     */
    public int addAttr(List<AttrParam> attrParamList);
    
    /**
     * 修改属性信息
     * 
     * @param attrDefParam
     * @return
     * @author lipeng16
     */
    public int updateAttr(AttrParam attrParam);
    
    /**
     * 删除属性信息
     * 
     * @param tenantId
     * @param attrId
     * @return
     * @author lipeng16
     */
    public int deleteAttr(AttrPam attrPam);
    
    /**
     * 分页查询属性值
     * 
     * @param attrValPageQuery
     * @return
     * @author lipeng16
     */
    public PageInfoResponse<AttrValInfo> queryAttrvals(AttrValPageQuery attrValPageQuery);

    /**
     * 插入(新增)属性值信息
     * 
     * @param attrValParamList
     * @return
     * @author lipeng16
     */
    public int addAttrVal(List<AttrValParam> attrValParamList);

    /**
     * 修改属性值信息
     * 
     * @param attrValParam
     * @return
     * @author lipeng16
     */
    public int updateAttrVal(AttrValParam attrValParam);
    
    /**
     * 删除属性值
     * 
     * @param tenantId
     * @param attrValueId
     * @return
     * @author lipeng16
     */
    public int deleteAttrVal(AttrValUniqueReq attrValUniqueReq);
    
    /**
     * 查询属性值
     * 
     * @param attrValParam
     * @return
     * @author lipeng16
     */
    public AttrVal queryAttrVal(AttrValUniqueReq attrValUniqueReq);
   
    
    /**
     * 查询所有的属性和属性值
     * 
     * @return 由属性对象对应的属性值List的Map
     * @author lipeng16
     */
    public List<AttrDef> queryAllAttrAndVals(String tenantId);

}
