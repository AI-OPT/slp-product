package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
import com.ai.slp.product.api.normproduct.param.AttrParam;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;

public class AttrAndAttrvalBusiSVImpl implements IAttrAndAttrvalBusiSV {

    @Autowired 
    IProdAttrDefAtomSV prodAttrDefAtomSV;
    
    @Override
    public AttrDefInfo selectAttrById(String tenantId, Long attrId) {
        ProdAttrDef prodAttrDef = prodAttrDefAtomSV.selectById(tenantId, attrId);
        if(prodAttrDef == null)
            throw new BusinessException("","未找到指定的属性信息，租户ID="+tenantId+",属性标识="+attrId);
        AttrDefInfo attrDefInfo = new AttrDefInfo();
//        BeanUtils.copyProperties(attrDefInfo, prodAttrDef);
        attrDefInfo.setAttrId(prodAttrDef.getAttrId());
        attrDefInfo.setAttrName(prodAttrDef.getAttrName());
        if(prodAttrDef.getFirstLetter() != null)
            attrDefInfo.setFirstLetter(prodAttrDef.getFirstLetter());
        attrDefInfo.setValueWay(prodAttrDef.getValueWay());
        prodAttrDef.setIsAllowCustom("N");//用户自定义输入统一设置为否
        attrDefInfo.setIsAllowCustom(prodAttrDef.getIsAllowCustom());//一期不做 Y是 N否
        
        return attrDefInfo;
    }

    @Override
    public PageInfo<AttrDefInfo> selectAttrs(AttrDefParam attrDefParam) {
        PageInfo<ProdAttrDef> pageInfo = prodAttrDefAtomSV.selectPageAttrs(attrDefParam);
        List<ProdAttrDef> prodAttrDefList = pageInfo.getResult();
        List<AttrDefInfo> attrDefInfoList = new ArrayList<AttrDefInfo>();
        for(ProdAttrDef prodAttrDef : prodAttrDefList){
            String tenantId = prodAttrDef.getTenantId();
            Long attrId = prodAttrDef.getAttrId();
            AttrDefInfo attrDefInfo = new AttrDefInfo();
            
            attrDefInfo.setAttrId(attrId);
            attrDefInfo.setAttrName(prodAttrDef.getAttrName());
            attrDefInfo.setValueWay(prodAttrDef.getValueWay());
            attrDefInfo.setAttrValNum(prodAttrDefAtomSV.selectAttrvalNum(tenantId,attrId));
            prodAttrDef.setIsAllowCustom("N");//用户自定义输入统一设置为否
            attrDefInfo.setIsAllowCustom(prodAttrDef.getIsAllowCustom());
            attrDefInfo.setOperTime(prodAttrDef.getOperTime());
            attrDefInfo.setOperId(prodAttrDef.getOperId());
            
            attrDefInfoList.add(attrDefInfo);
        }
        PageInfo<AttrDefInfo> AttrDefInfoPage = new PageInfo<>();
        AttrDefInfoPage.setResult(attrDefInfoList);
        AttrDefInfoPage.setPageNo(pageInfo.getPageNo());;
        AttrDefInfoPage.setPageSize(pageInfo.getPageSize());
        
        return AttrDefInfoPage;
    }

    @Override
    public int updateAttr(AttrParam attrParam) {
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        prodAttrDef.setAttrId(attrParam.getAttrId());
        prodAttrDef.setOperId(attrParam.getOperId());
        prodAttrDef.setAttrName(attrParam.getAttrName());
        if(attrParam.getFirstLetter() != null)
            prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
        prodAttrDef.setValueWay(attrParam.getValueWay());
        prodAttrDef.setIsAllowCustom(attrParam.getIsCustom());
        return prodAttrDefAtomSV.updateAttr(prodAttrDef);
    }

    @Override
    public int deleteAttr(String tenantId, Long attrId) {
        if(tenantId == null || attrId == null) 
                throw new BusinessException("","未找到指定的属性信息，租户ID="+tenantId+",属性标识="+attrId);
        return prodAttrDefAtomSV.deleteById(tenantId, attrId);
    }

    @Override
    public int insertAttr(List<AttrParam> attrParamList) {
        int count = 0;
        for(AttrParam attrParam : attrParamList){
            ProdAttrDef prodAttrDef = new ProdAttrDef();
            prodAttrDef.setAttrId(attrParam.getAttrId());
            prodAttrDef.setOperId(attrParam.getOperId());
            prodAttrDef.setAttrName(attrParam.getAttrName());
            if(attrParam.getFirstLetter() != null)
                prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
            prodAttrDef.setValueWay(attrParam.getValueWay());
            prodAttrDef.setIsAllowCustom(attrParam.getIsCustom());
            int ok = prodAttrDefAtomSV.installObj(prodAttrDef);
            if(ok == 0)
                throw new BusinessException("","添加属性失败，属性名称="+attrParam.getAttrName());
            count++;
        }
            
        return count;
    }

}
