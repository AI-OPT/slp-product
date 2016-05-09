package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieliu on 16/5/6.
 */
@Component
public class ProdSkuAtomSVImpl implements IProdSkuAtomSV {
    @Autowired
    ProdSkuMapper prodSkuMapper;
    /**
     * 查询商品的SKU信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public List<ProdSku> querySkuOfProd(String tenantId, String prodId) {
        ProdSkuCriteria example = new ProdSkuCriteria();
        List<String> stateList = new ArrayList<>();
        stateList.add(ProductConstants.SKU_STATE_DISCARD);
        stateList.add(ProductConstants.SKU_STATE_AUTO_DISCARD);
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andStateNotIn(stateList);
        return prodSkuMapper.selectByExample(example);
    }
}
