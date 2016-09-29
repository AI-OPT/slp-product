package com.ai.slp.product.service.atom.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProdAttrAttachMapper;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAttrMapper;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * Created by jackieliu on 16/6/1.
 */
@Component
public class ProdAttrAtomSVImpl implements IProdAttrAtomSV {
    @Autowired
    ProdAttrMapper prodAttrMapper;
    @Autowired
    ProdAttrAttachMapper prodAttrAttachMapper;
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

    /**
     * 查询某个商品中某个属性对应的属性值信息
     *
     * @param tenantId
     * @param prodId
     * @param attrId
     * @param attrValId
     * @return
     */
    @Override
    public ProdAttr queryByProdAndAttrAndAttrVal(String tenantId, String prodId, Long attrId, String attrValId) {
        ProdAttrCriteria example = new ProdAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrIdEqualTo(attrId)
                .andAttrvalueDefIdEqualTo(attrValId)
                .andStateEqualTo(ProductConstants.ProdAttr.State.ACTIVE);
        List<ProdAttr> prodAttrs = prodAttrMapper.selectByExample(example);
        return CollectionUtil.isEmpty(prodAttrs)?null:prodAttrs.get(0);
    }

    @Override
    public int installProdAttr(ProdAttr prodAttr) {
        prodAttr.setProdAttrId(SequenceUtil.genProdAttrId());
        prodAttr.setOperTime(DateUtils.currTimeStamp());
        return prodAttrMapper.insert(prodAttr);
    }

    /**
     * 查询某类目下某个属性值被关联的数量
     *
     * @param tenantId
     * @param catId
     * @param attrValDefId
     * @return
     */
    @Override
    public int countOfAttrValOfCat(String tenantId, String catId, String attrValDefId) {
        return prodAttrAttachMapper.countOfAttrValOfCat(tenantId,catId,attrValDefId);
    }

    /**
     * 根据标识进行商品属性更新
     *
     * @param prodAttr
     * @return
     */
    @Override
    public int updateByProdAttrId(ProdAttr prodAttr) {
        if (prodAttr==null){
        	return 0;
        }
        prodAttr.setOperTime(DateUtils.currTimeStamp());
        return prodAttrMapper.updateByPrimaryKey(prodAttr);
    }

    public List<AttrInfo> queryAttrOfProdId(String prodId){
        return prodAttrAttachMapper.selectAllAttrOfSku(prodId);
    }

}
