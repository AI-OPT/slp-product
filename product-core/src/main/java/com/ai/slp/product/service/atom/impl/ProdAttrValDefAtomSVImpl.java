package com.ai.slp.product.service.atom.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.vo.AttrAndValPageQueryVo;

/**
 * Date: 2016年4月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
@Component
public class ProdAttrValDefAtomSVImpl implements IProdAttrValDefAtomSV{
    @Autowired
    ProdAttrvalueDefMapper prodAttrvalueDefMapper;

    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;
    
    @Override
    public ProdAttrvalueDef selectById(String tenantId, String attrvalueDefId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrvalueDefIdEqualTo(attrvalueDefId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProdAttrvalueDef> prodAttrvalueDefList = prodAttrvalueDefMapper.selectByExample(example);
        if(prodAttrvalueDefList == null || prodAttrvalueDefList.isEmpty())
            return null;
        return prodAttrvalueDefList.get(0);
    }
    
    
    @Override
    public int insertProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        if(prodAttrvalueDef.getOperTime() == null)
            prodAttrvalueDef.setOperTime(DateUtils.currTimeStamp());
        prodAttrvalueDef.setAttrvalueDefId(sequenceCreditAtomSV.getSeqByName()+"");
        return prodAttrvalueDefMapper.insertSelective(prodAttrvalueDef);
    }

    @Override
    public int updateProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        if(prodAttrvalueDef.getOperTime() == null)
            prodAttrvalueDef.setOperTime(DateUtils.currTimeStamp());
        return prodAttrvalueDefMapper.updateByPrimaryKeySelective(prodAttrvalueDef);
    }


    @Override
    public int deleteProdAttrVal(String tenantId, String attrvalueDefId, Long operId, Timestamp operTime) {
        ProdAttrvalueDef prodAttrvalueDef =new ProdAttrvalueDef();
        prodAttrvalueDef.setState(CommonSatesConstants.STATE_INACTIVE);
        prodAttrvalueDef.setOperId(operId);
        prodAttrvalueDef.setOperTime(operTime!=null?operTime: DateUtils.currTimeStamp());
        
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrvalueDefIdEqualTo(attrvalueDefId);
        return prodAttrvalueDefMapper.updateByExampleSelective(prodAttrvalueDef, example);
    }


    @Override
    public PageInfo<ProdAttrvalueDef> selectAttrValPage(AttrAndValPageQueryVo attrAndValPageQueryVo) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        ProdAttrvalueDefCriteria.Criteria param = example.createCriteria();
        param.andAttrIdEqualTo(attrAndValPageQueryVo.getAttrId()).andTenantIdEqualTo(attrAndValPageQueryVo.getTenantId()).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        if(attrAndValPageQueryVo.getAttrValueName() != null)
            param.andAttrValueNameEqualTo(attrAndValPageQueryVo.getAttrValueName());
        if(attrAndValPageQueryVo.getAttrvalueDefId() != null)
            param.andAttrvalueDefIdEqualTo(attrAndValPageQueryVo.getAttrvalueDefId());
        //统计查询条目数
        int count = prodAttrvalueDefMapper.countByExample(example);
        
        example.setLimitStart((attrAndValPageQueryVo.getPageNo()-1)*attrAndValPageQueryVo.getPageSize());
        example.setLimitEnd(attrAndValPageQueryVo.getPageSize());
        
        PageInfo<ProdAttrvalueDef> attrValPage = new PageInfo<ProdAttrvalueDef>();
        attrValPage.setPageSize(attrAndValPageQueryVo.getPageSize());
        attrValPage.setPageNo(attrAndValPageQueryVo.getPageNo());
        attrValPage.setResult(prodAttrvalueDefMapper.selectByExample(example));
        attrValPage.setCount(count);
        
        return attrValPage;
    }
    
    @Override
    public List<ProdAttrvalueDef> selectAttrValForAttr(String tenantId, Long attrId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.setOrderByClause("firstLetter");
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProdAttrvalueDef> prodAttrValList = prodAttrvalueDefMapper.selectByExample(example);
        return prodAttrValList;
    }


    
}
