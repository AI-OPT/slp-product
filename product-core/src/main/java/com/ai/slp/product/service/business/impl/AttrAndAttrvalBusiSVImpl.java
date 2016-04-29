package com.ai.slp.product.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefParam;
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
        
        return null;
    }

}
