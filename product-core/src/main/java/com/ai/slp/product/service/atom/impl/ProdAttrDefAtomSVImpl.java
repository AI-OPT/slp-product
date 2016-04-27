package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/27.
 */
@Component
public class ProdAttrDefAtomSVImpl implements IProdAttrDefAtomSV {
    @Autowired
    ProdAttrDefMapper prodAttrDefMapper;

    @Override
    public int installObj(ProdAttrDef productAttr) {
        return prodAttrDefMapper.insertSelective(productAttr);
    }
}
