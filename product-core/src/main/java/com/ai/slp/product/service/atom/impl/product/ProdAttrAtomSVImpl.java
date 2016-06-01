package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAttrMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/6/1.
 */
@Component
public class ProdAttrAtomSVImpl implements IProdAttrAtomSV {
    @Autowired
    ProdAttrMapper prodAttrMapper;
    /**
     * 查询指定商品下某个属性的属性值
     *
     * @param tenantId
     * @param prodId
     * @param attrId
     * @return
     */
    @Override
    public List<ProdAttr> queryOfProdAndAttr(String tenantId, String prodId, Long attrId) {
        ProdAttrCriteria example = new ProdAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(ProductConstants.ProdAttr.State.ACTIVE);
        return prodAttrMapper.selectByExample(example);
    }
}
