package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValueCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrValueMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrValAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/1.
 */
@Component
public class ProdCatAttrValAtomSVImpl implements IProdCatAttrValAtomSV {
    @Autowired
    ProdCatAttrValueMapper attrValueMapper;
    /**
     * 删除类目属性关系对应属性值
     *
     * @param tenantId
     * @param catAttrId
     * @return
     */
    @Override
    public int deleteByCat(String tenantId, String catAttrId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId);
        return attrValueMapper.deleteByExample(example);
    }
}
