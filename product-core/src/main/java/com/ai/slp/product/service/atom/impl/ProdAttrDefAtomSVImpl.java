package com.ai.slp.product.service.atom.impl;

import com.ai.opt.sdk.sequence.util.SeqUtil;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
@Component
public class ProdAttrDefAtomSVImpl implements IProdAttrDefAtomSV {
    @Autowired
    ProdAttrDefMapper prodAttrDefMapper;

    @Override
    public int installObj(ProdAttrDef productAttr) {
        productAttr.setAttrId(SeqUtil.getNewId(""));
        return prodAttrDefMapper.insertSelective(productAttr);
    }

    @Override
    public ProdAttrDef selectById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        List<ProdAttrDef> prodAttrDefList = prodAttrDefMapper.selectByExample(example);
        return (prodAttrDefList==null|| prodAttrDefList.isEmpty())?null:prodAttrDefList.get(0);
    }

    @Override
    public int deleteById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        return prodAttrDefMapper.deleteByExample(example);
    }
}
