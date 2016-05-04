package com.ai.slp.product.service.atom.impl;

import java.sql.Timestamp;
import java.util.List;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.util.DateUtils;
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
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;
    
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
        if (prodCatAttr.getOperTime()==null)
            prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        prodCatAttr.setCatAttrId(sequenceCreditAtomSV.getSeqByName()+"");
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
     * 删除类目的指定属性
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param operId
     * @param operTime
     * @return
     */
    @Override
    public int deleteByCatAttrId(String tenantId, String catId, Long attrId, Long operId, Timestamp operTime) {
        ProdCatAttr prodCatAttr = new ProdCatAttr();
        prodCatAttr.setState(CommonSatesConstants.STATE_INACTIVE);
        prodCatAttr.setOperId(operId);
        prodCatAttr.setOperTime(operTime!=null?operTime: DateUtils.currTimeStamp());
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andProductCatIdEqualTo(catId);
        return prodCatAttrMapper.updateByExampleSelective(prodCatAttr,example);
    }


    @Override
    public int deleteByCatId(String tenantId,String catAttrId,Long operId, Timestamp operTime) {
        ProdCatAttr prodCatAttr = new ProdCatAttr();
        prodCatAttr.setState(CommonSatesConstants.STATE_INACTIVE);
        prodCatAttr.setOperId(operId);
        prodCatAttr.setOperTime(operTime!=null?operTime: DateUtils.currTimeStamp());
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId);
        return prodCatAttrMapper.updateByExampleSelective(prodCatAttr,example);
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

    /**
     * 查询类目下指定属性类型和属性标识的关联信息
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param attrType
     * @return
     */
    @Override
    public ProdCatAttr queryByCatIdAndTypeAndAttrId(String tenantId, String catId, Long attrId, String attrType) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProductCatIdEqualTo(catId)
                .andAttrIdEqualTo(attrId)
                .andAttrTypeEqualTo(attrType)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProdCatAttr> attrList = prodCatAttrMapper.selectByExample(example);
        return attrList==null||attrList.isEmpty()?null:attrList.get(0);
    }

}
