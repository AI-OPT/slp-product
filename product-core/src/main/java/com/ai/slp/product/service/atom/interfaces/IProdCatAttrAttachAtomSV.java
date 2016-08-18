package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.attach.CatAttrValAttach;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;

import java.util.List;

/**
 * 类目属性扩展表
 * Created by jackieliu on 16/5/2.
 */
public interface IProdCatAttrAttachAtomSV {

    /**
     * 查询指定类目下的属性信息
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @return
     */
    public List<ProdCatAttrAttch> queryAttrOfByIdAndType(String tenantId,String catId,String attrType);

    /**
     * 查询类目关联属性的属性值
     *
     * @param tenantId 租户id
     * @param catAttrId 类目属性关联id
     * @return
     */
    public List<CatAttrValAttach> queryValListByCatAttr(String tenantId, String catAttrId);
   
}
