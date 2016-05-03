package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
import com.ai.slp.product.api.normproduct.param.AttrParam;

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
    public PageInfo<AttrDefInfo> selectAttrs(AttrDefParam attrDefParam);
    
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
    public int deleteAttr(String tenantId,Long attrId);
    
    /**
     * 新增属性
     * 
     * @param attrParamList
     * @return
     * @author lipeng
     */
    public int insertAttr(List<AttrParam> attrParamList);
}
