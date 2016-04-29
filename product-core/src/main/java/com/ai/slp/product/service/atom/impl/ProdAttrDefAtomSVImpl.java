package com.ai.slp.product.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDefCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrDefMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;

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

    //获取序列号
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;

    @Override
    public int installObj(ProdAttrDef productAttr) {
        productAttr.setAttrId(
                sequenceCreditAtomSV.get6SeqByName());
        return prodAttrDefMapper.insertSelective(productAttr);
    }

    @Override
    public ProdAttrDef selectById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        List<ProdAttrDef> prodAttrDefList = prodAttrDefMapper.selectByExample(example);
        return (prodAttrDefList==null|| prodAttrDefList.isEmpty())?null:prodAttrDefList.get(0);
    }

    @Override
    public int deleteById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        return prodAttrDefMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<ProdAttrDef> selectPageAttrs(AttrDefParam attrDefParam) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        ProdAttrDefCriteria.Criteria request = example.createCriteria();
        request.andTenantIdEqualTo(attrDefParam.getTenantId());
        if(attrDefParam.getAttrId() != null)
            request.andAttrIdEqualTo(attrDefParam.getAttrId());
        if(attrDefParam.getAttrName() != null)
            request.andAttrNameEqualTo(attrDefParam.getAttrName());
        if(attrDefParam.getValueWay() != null)
            request.andValueWayEqualTo(attrDefParam.getValueWay());
        if(String.valueOf(attrDefParam.getOperId()) != null)
            request.andOperIdEqualTo(attrDefParam.getOperId());
            
        PageInfo<ProdAttrDef> pageInfo = new PageInfo<ProdAttrDef>();
        if(attrDefParam.getPageNo() != null && attrDefParam.getPageSize() != null){
            example.setLimitStart((attrDefParam.getPageNo()-1) * attrDefParam.getPageSize());
            example.setLimitEnd(attrDefParam.getPageSize());
        }
        pageInfo.setPageNo(attrDefParam.getPageNo());
        pageInfo.setPageSize(attrDefParam.getPageSize());
        pageInfo.setResult(prodAttrDefMapper.selectByExample(example));
            
        return pageInfo;
    }

    @Override
    public int selectAttrvalNum(String tenantId, Long attrId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        return prodAttrvalueDefMapper.countByExample(example);
    }
    
    
}
