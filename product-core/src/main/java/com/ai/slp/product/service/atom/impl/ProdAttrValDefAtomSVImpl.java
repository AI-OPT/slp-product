package com.ai.slp.product.service.atom.impl;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import com.ai.slp.product.vo.AttrValPageQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date: 2016年4月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Component
public class ProdAttrValDefAtomSVImpl implements IProdAttrValDefAtomSV{
    @Autowired
    ProdAttrvalueDefMapper prodAttrvalueDefMapper;
    
    @Override
    public ProdAttrvalueDef selectById(String tenantId, String attrvalueDefId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrvalueDefIdEqualTo(attrvalueDefId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProdAttrvalueDef> prodAttrvalueDefList = prodAttrvalueDefMapper.selectByExample(example);
        if(prodAttrvalueDefList == null || prodAttrvalueDefList.isEmpty()){
        	return null;
        }
        return prodAttrvalueDefList.get(0);
    }
    
    
    @Override
    public int insertProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        if(prodAttrvalueDef.getOperTime() == null){
        	prodAttrvalueDef.setOperTime(DateUtils.currTimeStamp());
        }
        prodAttrvalueDef.setAttrvalueDefId(SequenceUtil.genProdAttrvalueDefId());
        prodAttrvalueDef.setState(CommonConstants.STATE_ACTIVE);
        return prodAttrvalueDefMapper.insertSelective(prodAttrvalueDef);
    }

    @Override
    public int updateProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        prodAttrvalueDef.setOperTime(DateUtils.currTimeStamp());
        return prodAttrvalueDefMapper.updateByPrimaryKeySelective(prodAttrvalueDef);
    }


    @Override
    public int deleteProdAttrVal(String tenantId, String attrvalueDefId, Long operId) {
        ProdAttrvalueDef prodAttrvalueDef =new ProdAttrvalueDef();
        prodAttrvalueDef.setState(CommonConstants.STATE_INACTIVE);
        prodAttrvalueDef.setOperId(operId);
        prodAttrvalueDef.setOperTime(DateUtils.currTimeStamp());
        
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrvalueDefIdEqualTo(attrvalueDefId);
        return prodAttrvalueDefMapper.updateByExampleSelective(prodAttrvalueDef, example);
    }


    @Override
    public PageInfo<ProdAttrvalueDef> selectAttrValPage(AttrValPageQueryVo pageQueryVo) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        ProdAttrvalueDefCriteria.Criteria param = example.createCriteria();
        param.andTenantIdEqualTo(pageQueryVo.getTenantId())
            .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        
        //example.setOrderByClause("OPER_TIME desc");//操作时间倒序
        
        if(pageQueryVo.getAttrId()!=null){
        	param.andAttrIdEqualTo(pageQueryVo.getAttrId());
        }
        if(StringUtils.isNotBlank(pageQueryVo.getAttrvalueDefId())){
        	param.andAttrvalueDefIdEqualTo(pageQueryVo.getAttrvalueDefId());
        }
        if(StringUtils.isNotBlank(pageQueryVo.getAttrValueName())){
        	param.andAttrValueNameLike("%"+ pageQueryVo.getAttrValueName()+"%");
        }
        //统计查询条目数
        int count = prodAttrvalueDefMapper.countByExample(example);
        
        example.setLimitStart((pageQueryVo.getPageNo()-1)* pageQueryVo.getPageSize());
        example.setLimitEnd(pageQueryVo.getPageSize());
        
        PageInfo<ProdAttrvalueDef> attrValPage = new PageInfo<ProdAttrvalueDef>();
        attrValPage.setPageSize(pageQueryVo.getPageSize());
        attrValPage.setPageNo(pageQueryVo.getPageNo());
        attrValPage.setResult(prodAttrvalueDefMapper.selectByExample(example));
        attrValPage.setCount(count);
        System.out.println("count"+count);

        return attrValPage;
    }
    
    @Override
    public List<ProdAttrvalueDef> selectAttrValForAttr(String tenantId, Long attrId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        
        //example.setOrderByClause("first_letter");
        
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProdAttrvalueDef> prodAttrValList = prodAttrvalueDefMapper.selectByExample(example);
        return prodAttrValList;
    }


	@Override
	public int selectAttrValNum(String tenantId, Long attrId) {
		 ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
		 example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
		 int num = prodAttrvalueDefMapper.countByExample(example);

		return num;
	}



}
