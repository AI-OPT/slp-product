package com.ai.slp.product.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrValPageQuery;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;

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

    @Override
    public ProdAttrvalueDef selectById(String tenantId, String attrValueId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrValueIdEqualTo(attrValueId);
        List<ProdAttrvalueDef> prodAttrvalueDefList = prodAttrvalueDefMapper.selectByExample(example);
        if(prodAttrvalueDefList == null || prodAttrvalueDefList.size() == 0)
            return null;
        return prodAttrvalueDefList.get(0);
    }
    
    
    @Override
    public int insertProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        return prodAttrvalueDefMapper.insertSelective(prodAttrvalueDef);
    }

    @Override
    public int updateProdAttrVal(ProdAttrvalueDef prodAttrvalueDef) {
        return prodAttrvalueDefMapper.updateByPrimaryKeySelective(prodAttrvalueDef);
    }


    @Override
    public int deleteProdAttrVal(String tenantId, String attrValueId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrValueIdEqualTo(attrValueId);
        return prodAttrvalueDefMapper.deleteByExample(example);
    }


    @Override
    public PageInfo<ProdAttrvalueDef> selectAttrValPage(AttrValPageQuery attrValPageQuery) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        ProdAttrvalueDefCriteria.Criteria param = example.createCriteria();
        param.andAttrIdEqualTo(attrValPageQuery.getAttrId()).andTenantIdEqualTo(attrValPageQuery.getTenantId());
        if(attrValPageQuery.getAttrValueName() != null)
            param.andAttrValueNameEqualTo(attrValPageQuery.getAttrValueName());
        if(attrValPageQuery.getAttrvalueDefId() != null)
            param.andAttrvalueDefIdEqualTo(attrValPageQuery.getAttrvalueDefId());
        example.setLimitStart((attrValPageQuery.getPageNo()-1)*attrValPageQuery.getPageSize());
        example.setLimitEnd(attrValPageQuery.getPageSize());
        
        PageInfo<ProdAttrvalueDef> attrValPage = new PageInfo<ProdAttrvalueDef>();
        attrValPage.setPageSize(attrValPageQuery.getPageSize());
        attrValPage.setPageNo(attrValPageQuery.getPageNo());
        attrValPage.setResult(prodAttrvalueDefMapper.selectByExample(example));
        
        return attrValPage;
    }
    
    

    
}
