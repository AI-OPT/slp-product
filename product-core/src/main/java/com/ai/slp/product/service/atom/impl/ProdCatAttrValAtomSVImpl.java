package com.ai.slp.product.service.atom.impl;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValueCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrValueMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrValAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        attrValue.setState(CommonConstants.STATE_INACTIVE);
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
     * @return
     */
    @Override
    public int deleteValByAttr(String tenantId, String catAttrId, String attrValId, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andCatAttrIdEqualTo(catAttrId).andAttrvalueDefIdGreaterThan(attrValId);
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonConstants.STATE_INACTIVE);
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

    /**
     * 删除管理属性对应的属性值
     *
     * @param tenantId
     * @param catAttrIdList
     * @return
     */
    @Override
    public int deleteByCatAttrId(String tenantId, List<String> catAttrIdList,Long operId) {
        if (CollectionUtil.isEmpty(catAttrIdList)){
        	return 0;
        }
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonConstants.STATE_INACTIVE);
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdIn(catAttrIdList);
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

    /**
     * 删除指定属性下不包含在当前属性值集合的属性值
     *
     * @param tenantId
     * @param catAttrId
     * @param valIdList
     * @param operId
     * @return
     */
    @Override
    public int deleteNoValIds(String tenantId, String catAttrId, List<String> valIdList, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        ProdCatAttrValueCriteria.Criteria criteria = example.createCriteria().andTenantIdEqualTo(tenantId)
                .andCatAttrIdEqualTo(catAttrId);
        if (!CollectionUtil.isEmpty(valIdList)){
        	criteria.andAttrvalueDefIdNotIn(valIdList);
        }
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonConstants.STATE_INACTIVE);
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

    /**
     * 删除指定的属性值关联
     *
     * @param tenantId
     * @param catAttrValId
     * @param operId
     * @return
     */
    @Override
    public int deleteById(String tenantId, String catAttrValId, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andCatAttrValueIdEqualTo(catAttrValId);
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setState(CommonConstants.STATE_INACTIVE);
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
        .andStateEqualTo(CommonConstants.STATE_ACTIVE);
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
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
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
        attrValue.setCatAttrValueId(SequenceUtil.genProdCatAttrValId());
        if (attrValue.getOperTime()==null){
        	attrValue.setOperTime(DateUtils.currTimeStamp());
        }
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
                && !tenantId.equals(attrValue.getTenantId())){
        	attrValue = null;
        }
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

    /**
     * 删除关联属性所关联的属性值
     *
     * @param tenantId
     * @param catAttrId
     * @param operId
     * @return
     */
    @Override
    public int deleteByCatAttrId(String tenantId, String catAttrId, Long operId) {
        ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId);
        ProdCatAttrValue attrValue = new ProdCatAttrValue();
        attrValue.setOperId(operId);
        attrValue.setOperTime(DateUtils.currTimeStamp());
        attrValue.setState(CommonConstants.STATE_INACTIVE);
        return attrValueMapper.updateByExampleSelective(attrValue,example);
    }

}
