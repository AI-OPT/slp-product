package com.ai.slp.product.service.atom.impl;

import java.util.List;

import com.ai.slp.product.util.SequenceUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDefCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrDefMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.vo.AttrAndValPageQueryVo;

/**
 * 属性定义原子操作
 * Created by jackieliu on 16/4/27.
 */
@Component
public class ProdAttrDefAtomSVImpl implements IProdAttrDefAtomSV {
    @Autowired
    ProdAttrDefMapper prodAttrDefMapper;
    @Autowired
    ProdAttrvalueDefMapper prodAttrvalueDefMapper;

    @Override
    public int installObj(ProdAttrDef productAttr) {
        productAttr.setAttrId(SequenceUtil.createAttrDefId());
        if(productAttr.getOperTime() == null)
            productAttr.setOperTime(DateUtils.currTimeStamp());
        return prodAttrDefMapper.insertSelective(productAttr);
    }

    @Override
    public ProdAttrDef selectById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProdAttrDef> prodAttrDefList = prodAttrDefMapper.selectByExample(example);
        return (prodAttrDefList==null|| prodAttrDefList.isEmpty())?null:prodAttrDefList.get(0);
    }

    @Override
    public int deleteById(String tenantId, Long attrId, Long operId) {
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        prodAttrDef.setState(CommonSatesConstants.STATE_INACTIVE);
        prodAttrDef.setOperId(operId);
        prodAttrDef.setOperTime(DateUtils.currTimeStamp());
        
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        
        return prodAttrDefMapper.updateByExampleSelective(prodAttrDef, example);
    }

    @Override
    public PageInfo<ProdAttrDef> selectPageAttrs(AttrAndValPageQueryVo attrAndValPageQueryVo) {
        //添加查询条件参数
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        ProdAttrDefCriteria.Criteria request = example.createCriteria();
        request.andTenantIdEqualTo(attrAndValPageQueryVo.getTenantId());
        if(attrAndValPageQueryVo.getAttrId()!=null)
            request.andAttrIdEqualTo(attrAndValPageQueryVo.getAttrId());
        	
        if(StringUtils.isNoneBlank(attrAndValPageQueryVo.getAttrName()))
            request.andAttrNameEqualTo(attrAndValPageQueryVo.getAttrName());
        
        if(StringUtils.isNotBlank(attrAndValPageQueryVo.getValueWay()))
            request.andValueWayEqualTo(attrAndValPageQueryVo.getValueWay());
        
        //设置数据的查询状态为有效状态
        request.andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        //获取查询到的条目数
        int count = prodAttrDefMapper.countByExample(example);
        if(attrAndValPageQueryVo.getPageNo() != null && attrAndValPageQueryVo.getPageSize() != null){
            example.setLimitStart((attrAndValPageQueryVo.getPageNo()-1) * attrAndValPageQueryVo.getPageSize());
            example.setLimitEnd(attrAndValPageQueryVo.getPageSize());
        }
        //分页返回对象设置
        PageInfo<ProdAttrDef> pageInfo = new PageInfo<ProdAttrDef>();
        pageInfo.setPageNo(attrAndValPageQueryVo.getPageNo());
        pageInfo.setPageSize(attrAndValPageQueryVo.getPageSize());
        pageInfo.setResult(prodAttrDefMapper.selectByExample(example));
        pageInfo.setCount(count);
            
        return pageInfo;
    }

    @Override
    public int selectAttrvalNum(String tenantId, Long attrId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return prodAttrvalueDefMapper.countByExample(example);
    }

    @Override
    public int updateAttr(ProdAttrDef prodAttrDef) {
        prodAttrDef.setOperTime(DateUtils.currTimeStamp());
        return prodAttrDefMapper.updateByPrimaryKeySelective(prodAttrDef);
    }

    @Override
    public List<ProdAttrDef> selectAllAttrs(String tenantId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.setOrderByClause("firstLetter");
        example.createCriteria().andTenantIdEqualTo(tenantId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return prodAttrDefMapper.selectByExample(example);
    }

    
    
}
