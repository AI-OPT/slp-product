package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProdSkuAttrMapperExt;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuAttrMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/10.
 */
@Component
public class ProdSkuAttrAtomSV implements IProdSkuAttrAtomSV {
    @Autowired
    ProdSkuAttrMapper skuAttrMapper;
    @Autowired
    ProdSkuAttrMapperExt skuAttrMapperExt;
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

    /**
     * 查询SKU单品中某个属性的属性值
     *
     * @param tenantId
     * @param skuId
     * @param attrId
     * @return
     */
    @Override
    public ProdSkuAttr queryAttrValBySkuIdAndAttr(String tenantId, String skuId, Long attrId) {
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andSkuIdEqualTo(skuId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        List<ProdSkuAttr> prodSkuAttrList = skuAttrMapper.selectByExample(example);
        return CollectionUtil.isEmpty(prodSkuAttrList)?null:prodSkuAttrList.get(0);
    }

    @Override
    public List<String> queryAttrValIdByProdIdAndAttrId(String tenantId,String prodId,Long attrId){
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        example.setDistinct(true);
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        return skuAttrMapperExt.selectAttrValId(example);
    }

    /**
     * 查询SKU的属性及属性值
     *
     * @param tenantId
     * @param skuId
     * @return
     */
    @Override
    public List<ProdSkuAttr> queryBySkuId(String tenantId, String skuId) {
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andSkuIdEqualTo(skuId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        return skuAttrMapper.selectByExample(example);
    }

    /**
     * 查询SKU的属性及属性值
     *
     * @param tenantId
     * @param skuId
     * @param hasDiscard
     * @return
     */
    @Override
    public List<ProdSkuAttr> queryBySkuId(String tenantId, String skuId, boolean hasDiscard) {
        ProdSkuAttrCriteria example = new ProdSkuAttrCriteria();
        ProdSkuAttrCriteria.Criteria criteria = example.createCriteria().andTenantIdEqualTo(tenantId)
                .andSkuIdEqualTo(skuId);
        if(!hasDiscard){
        	criteria.andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        }
        return skuAttrMapper.selectByExample(example);
    }
}
