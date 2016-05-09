package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValueCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrValueMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrValAtomSV;
import com.ai.slp.product.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jackieliu on 16/5/1.
 */
@Component
public class ProdCatAttrValAtomSVImpl implements IProdCatAttrValAtomSV {
    @Autowired
    ProdCatAttrValueMapper attrValueMapper;
    /**
     * 删除类目属性关系对应属性值
     *
     * @param tenantId
     * @param catAttrId
     * @return
     */
    @Override
    public int deleteByCat(String tenantId, String catAttrId, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId);
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonSatesConstants.STATE_INACTIVE);
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

    /**
     * 删除类目属性下的一个属性值
     *
     * @param tenantId
     * @param catAttrId
     * @param attrValId
     * @param operId
     * @param operTime
     * @return
     */
    @Override
    public int deleteValByAttr(String tenantId, String catAttrId, String attrValId, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andCatAttrIdEqualTo(catAttrId).andAttrvalueDefIdGreaterThan(attrValId);
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonSatesConstants.STATE_INACTIVE);
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

    /**
     * 查询类目属性关系对应的属性值
     *
     * @param tenantId
     * @param catAttrId
     * @return
     */
    @Override
    public List<ProdCatAttrValue> queryByCatAttrId(String tenantId, String catAttrId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId)
        .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return attrValueMapper.selectByExample(example);
    }

    /**
     * 查询指定关系和属性值的属性值信息
     *
     * @param tenantId
     * @param catAttrId
     * @param valId
     * @return
     */
    @Override
    public ProdCatAttrValue queryByCatAndCatAttrId(String tenantId, String catAttrId, String valId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andCatAttrIdEqualTo(catAttrId)
                .andAttrvalueDefIdEqualTo(valId)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProdCatAttrValue> valueList = attrValueMapper.selectByExample(example);
        return valueList==null||valueList.isEmpty()?null:valueList.get(0);
    }

    /**
     * 添加类目属性值关系
     *
     * @param attrValue
     * @return
     */
    @Override
    public int installCatAttrVal(ProdCatAttrValue attrValue) {
        if (attrValue.getOperTime()==null)
            attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.insert(attrValue);
    }

    /**
     * 根据唯一标识查询类目与属性值的关联
     *
     * @param tenantId
     * @param catAttrValId
     * @return
     */
    @Override
    public ProdCatAttrValue selectById(String tenantId, String catAttrValId) {
        ProdCatAttrValue attrValue = attrValueMapper.selectByPrimaryKey(catAttrValId);
        //若唯一标识正确,但租户id不符合,则返回空
        if (attrValue!=null
                && !tenantId.equals(attrValue.getTenantId()))
            attrValue = null;
        return attrValue;
    }

    /**
     * 更新类目对应的属性值
     *
     * @param attrValue
     * @return
     */
    @Override
    public int update(ProdCatAttrValue attrValue) {
        attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.updateByPrimaryKey(attrValue);
    }
}
