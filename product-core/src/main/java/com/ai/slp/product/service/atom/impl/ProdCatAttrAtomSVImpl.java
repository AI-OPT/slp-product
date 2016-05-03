package com.ai.slp.product.service.atom.impl;

import java.util.List;

import com.ai.slp.product.constants.CommonSatesConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import org.springframework.stereotype.Component;

@Component
public class ProdCatAttrAtomSVImpl implements IProdCatAttrAtomSV{

    @Autowired
    ProdCatAttrMapper prodCatAttrMapper;
    
    @Override
    public ProdCatAttr selectById(String tenantId, String productCatId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        List<ProdCatAttr> prodCatAttrList = prodCatAttrMapper.selectByExample(example);
        if(prodCatAttrList == null || prodCatAttrList.isEmpty())
            return null;
        return prodCatAttrList.get(0);
    }

    @Override
    public int insertProdCatAttr(ProdCatAttr prodCatAttr) {
        return prodCatAttrMapper.insertSelective(prodCatAttr);
    }

    @Override
    public List<ProdCatAttr> queryAttrsByCatId(String tenantId, String catId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return prodCatAttrMapper.selectByExample(example);
    }

    /**
     * 删除指定类目属性关系
     *
     * @param catId
     * @return
     */
    @Override
    public int deleteByCatId(String catId) {
        return prodCatAttrMapper.deleteByPrimaryKey(catId);
    }

    /**
     * 查询类目下某个类型的属性
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @return
     */
    @Override
    public List<ProdCatAttr> queryAttrOfCatByIdAndType(String tenantId, String catId, String attrType) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andAttrTypeEqualTo(attrType)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return prodCatAttrMapper.selectByExample(example);
    }

}
