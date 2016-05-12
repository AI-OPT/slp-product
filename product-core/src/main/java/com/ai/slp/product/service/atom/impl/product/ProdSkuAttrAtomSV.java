package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuAttrMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
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
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrvalueDefIdEqualTo(valId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        return skuAttrMapper.countByExample(example);
    }

    /**
     * 废弃SKU单品下的属性
     *
     * @param tenantId
     * @param skuId    sku单品标识
     * @param operId   操作者ID
     * @return
     */
    @Override
    public int discardAttrOfSku(String tenantId, String skuId, Long operId) {
        ProdSkuAttr prodSkuAttr = new ProdSkuAttr();
        prodSkuAttr.setOperTime(DateUtils.currTimeStamp());
        prodSkuAttr.setOperId(operId);
        prodSkuAttr.setState(ProductConstants.ProdSkuAttr.State.INACTIVE);
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andSkuIdEqualTo(skuId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        return skuAttrMapper.updateByExampleSelective(prodSkuAttr,example);
    }

    /**
     * 添加SKU属性值
     *
     * @param prodSkuAttr
     * @return
     */
    @Override
    public int createAttr(ProdSkuAttr prodSkuAttr) {
        prodSkuAttr.setSkuAttrId(SequenceUtil.genSkuAttrId());
        prodSkuAttr.setOperTime(DateUtils.currTimeStamp());
        return skuAttrMapper.insert(prodSkuAttr);
    }


}
