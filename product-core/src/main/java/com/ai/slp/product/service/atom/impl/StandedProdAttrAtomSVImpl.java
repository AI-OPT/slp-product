package com.ai.slp.product.service.atom.impl;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.attach.StandedProdAttrAttachMapper;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.StandedProdAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标准品属性值
 * Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProdAttrAtomSVImpl implements IStandedProdAttrAtomSV {
    @Autowired
    StandedProdAttrMapper standedProdAttrMapper;
    @Autowired
    StandedProdAttrAttachMapper prodAttrAttachMapper;
    /**
     * 添加标准品属性值
     *
     * @param prodAttr
     * @return
     */
    @Override
    public int installObj(StandedProdAttr prodAttr) {
        if (prodAttr==null){
        	return 0;
        }
        //设置序列号
        prodAttr.setStandedProdAttrId(SequenceUtil.genStandedProdAttrId());
        if (prodAttr.getOperTime()==null){
        	prodAttr.setOperTime(DateUtils.currTimeStamp());
        }
        return standedProdAttrMapper.insert(prodAttr);
    }
    
    /**
     * 添加标准品属性值
     *
     * @param prodAttr
     * @return
     */
    @Override
    public int installObj(StandedProdAttr prodAttr,long standedProdAttrId) {
        if (prodAttr==null){
        	return 0;
        }
        //设置序列号
        prodAttr.setStandedProdAttrId(standedProdAttrId);
        if (prodAttr.getOperTime()==null){
        	prodAttr.setOperTime(DateUtils.currTimeStamp());
        }
        return standedProdAttrMapper.insert(prodAttr);
    }

    @Override
    public int updateObj(StandedProdAttr prodAttr) {
        prodAttr.setOperTime(DateUtils.currTimeStamp());
        return standedProdAttrMapper.updateByPrimaryKey(prodAttr);
    }
    @Override
    public int updateSateBySQL(StandedProdAttr prodAttr) {
    	prodAttr.setOperTime(DateUtils.currTimeStamp());
    	return prodAttrAttachMapper.updateSateBySQL(prodAttr.getStandedProdAttrId(),
    			prodAttr.getState(),
    			prodAttr.getOperId(),
    			prodAttr.getOperTime());
    }

    @Override
    public int updateStandedProdAttrBySQL(StandedProdAttr prodAttr) {
    	//prodAttr.setOperTime(DateUtils.currTimeStamp());
    	if (prodAttr.getAttrValueName() == null && prodAttr.getAttrValueName2() == null) {
			return 1;
		}
    	return prodAttrAttachMapper.updateStandedProdAttrBySQL(prodAttr.getStandedProdAttrId(),
    			prodAttr.getAttrValueName(),
    			prodAttr.getAttrValueName2()
    			);
    }
    


    /**
     * 查询租户下的某个标准品的所有属性值,只查询有效的
     * @param tenantId
     * @param standedId
     * @return
     */
    @Override
    public List<StandedProdAttr> queryByNormProduct(String tenantId, String standedId) {
        if (StringUtils.isBlank(tenantId)||StringUtils.isBlank(standedId)){
        	return new ArrayList<StandedProdAttr>();
        }
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return standedProdAttrMapper.selectByExample(example);
    }

    @Override
    public Map<Long, StandedProdAttr> queryMapByNormProduct(String tenantId, String standedId) {
        List<StandedProdAttr> attrs = queryByNormProduct(tenantId,standedId);
        Map<Long,StandedProdAttr> attrMap = new HashMap<>();
        for (StandedProdAttr prodAttr:attrs){
            attrMap.put(prodAttr.getStandedProdAttrId(),prodAttr);
        }
        return attrMap;
    }

    @Override
    public int updateInactiveByNormProduct(String tenantId, String standedId,Long operId) {
        StandedProdAttr prodAttr = new StandedProdAttr();
        prodAttr.setState(CommonConstants.STATE_INACTIVE);
        prodAttr.setOperId(operId);
        prodAttr.setOperTime(DateUtils.currTimeStamp());
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);

        return standedProdAttrMapper.updateByExampleSelective(prodAttr,example);
    }

    /**
     * 查询某个属性关联标准品的数量
     *
     * @param tenantId
     * @param attrId
     * @return
     */
    @Override
    public int queryProdNumOfAttr(String tenantId, Long attrId) {
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return standedProdAttrMapper.countByExample(example);
    }
    /**
     * 查询某个属性关联标准品的数量
     *
     * @param tenantId
     * @param attrvalueDefId
     * @return
     */
    @Override
    public int queryProdNumOfAttrValue(String tenantId, String attrvalueDefId) {

    	StandedProdAttrCriteria example = new StandedProdAttrCriteria();
    	example.createCriteria()
    	.andTenantIdEqualTo(tenantId)
    	.andAttrvalueDefIdEqualTo(attrvalueDefId)
    	.andStateEqualTo(CommonConstants.STATE_ACTIVE);
    	return standedProdAttrMapper.countByExample(example);
    }

    /**
     * 查询指定标准品下某个属性的属性值
     *
     * @param tenantId
     * @param standedId
     * @param attrId
     * @return
     */
    @Override
    public List<StandedProdAttr> queryAttrVal(String tenantId, String standedId, Long attrId) {
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        
        //example.setOrderByClause("SERIAL_NUMBER ");
        
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return standedProdAttrMapper.selectByExample(example);
    }

    /**
     * 查询指定类目下某个属性值被标准品关联的数量
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
     * 查询标准品下某个属性值的信息
     *
     * @param tenantId
     * @param standedId
     * @param attrValId
     * @return
     */
    @Override
    public StandedProdAttr queryByProdIdAndAttrValId(String tenantId, String standedId, String attrValId) {
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedId)
                .andAttrvalueDefIdEqualTo(attrValId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<StandedProdAttr> prodAttrList = standedProdAttrMapper.selectByExample(example);
        return CollectionUtil.isEmpty(prodAttrList)?null:prodAttrList.get(0);
    }

}
