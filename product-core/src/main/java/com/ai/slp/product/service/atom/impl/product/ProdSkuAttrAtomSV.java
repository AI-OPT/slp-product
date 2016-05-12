package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuAttrMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieliu on 16/5/10.
 */
@Component
public class ProdSkuAttrAtomSV implements IProdSkuAttrAtomSV {
    @Autowired
    ProdSkuAttrMapper skuAttrMapper;
    @Override
    public int queryAttrValNumOfSku(String tenantId, String prodId, String valId) {
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        List<String> state = new ArrayList<>();
        state.add(ProductConstants.ProdSkuAttr.State.DISCARD);
        state.add(ProductConstants.ProdSkuAttr.State.AUTO_DISCARD);
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrvalueDefIdEqualTo(valId)
                .andStateNotIn(state);
        return skuAttrMapper.countByExample(example);
    }
}
