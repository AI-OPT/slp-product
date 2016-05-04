//package com.ai.slp.product.service.business.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ai.opt.base.exception.BusinessException;
//import com.ai.opt.base.vo.PageInfo;
//import com.ai.opt.sdk.util.BeanUtils;
//import com.ai.slp.product.api.common.param.MapForRes;
//import com.ai.slp.product.api.common.param.PageInfoForRes;
//import com.ai.slp.product.api.normproduct.param.AttrDef;
//import com.ai.slp.product.api.normproduct.param.AttrDefInfo;
//import com.ai.slp.product.api.normproduct.param.AttrDefParam;
//import com.ai.slp.product.api.normproduct.param.AttrPam;
//import com.ai.slp.product.api.normproduct.param.AttrParam;
//import com.ai.slp.product.api.normproduct.param.AttrValDef;
//import com.ai.slp.product.api.normproduct.param.AttrValInfo;
//import com.ai.slp.product.api.normproduct.param.AttrValPageQuery;
//import com.ai.slp.product.api.normproduct.param.AttrValParam;
//import com.ai.slp.product.api.normproduct.param.AttrValUniqueReq;
//import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
//import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
//import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
//import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
//import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;
//
//@Service
//@Transactional
//public class AttrAndAttrvalBusiSVImpl implements IAttrAndAttrvalBusiSV {
//
//    @Autowired 
//    IProdAttrDefAtomSV prodAttrDefAtomSV;
//    @Autowired
//    IProdAttrValDefAtomSV prodAttrValDefAtomSV;
//    
//    @Override
//    public AttrDefInfo selectAttrById(String tenantId, Long attrId) {
//        ProdAttrDef prodAttrDef = prodAttrDefAtomSV.selectById(tenantId, attrId);
//        if(prodAttrDef == null)
//            throw new BusinessException("","未找到指定的属性信息，租户ID="+tenantId+",属性标识="+attrId);
//        AttrDefInfo attrDefInfo = new AttrDefInfo();
////        BeanUtils.copyProperties(attrDefInfo, prodAttrDef);
//        attrDefInfo.setAttrId(prodAttrDef.getAttrId());
//        attrDefInfo.setAttrName(prodAttrDef.getAttrName());
//        if(prodAttrDef.getFirstLetter() != null)
//            attrDefInfo.setFirstLetter(prodAttrDef.getFirstLetter());
//        attrDefInfo.setValueWay(prodAttrDef.getValueWay());
//        attrDefInfo.setIsAllowCustom(prodAttrDef.getIsAllowCustom());//一期不做 Y是 N否
//        
//        return attrDefInfo;
//    }
//
//    @Override
//    public PageInfoForRes<AttrDefInfo> selectAttrs(AttrDefParam attrDefParam) {
//        PageInfo<ProdAttrDef> pageInfo = prodAttrDefAtomSV.selectPageAttrs(attrDefParam);
//        List<ProdAttrDef> prodAttrDefList = pageInfo.getResult();
//        List<AttrDefInfo> attrDefInfoList = new ArrayList<AttrDefInfo>();
//        for(ProdAttrDef prodAttrDef : prodAttrDefList){
//            String tenantId = prodAttrDef.getTenantId();
//            Long attrId = prodAttrDef.getAttrId();
//            AttrDefInfo attrDefInfo = new AttrDefInfo();
//            
//            attrDefInfo.setAttrId(attrId);
//            attrDefInfo.setAttrName(prodAttrDef.getAttrName());
//            attrDefInfo.setValueWay(prodAttrDef.getValueWay());
//            attrDefInfo.setAttrValNum(prodAttrDefAtomSV.selectAttrvalNum(tenantId,attrId));
//            prodAttrDef.setIsAllowCustom("N");//用户自定义输入统一设置为否
//            attrDefInfo.setIsAllowCustom(prodAttrDef.getIsAllowCustom());
//            attrDefInfo.setOperTime(prodAttrDef.getOperTime());
//            attrDefInfo.setOperId(prodAttrDef.getOperId());
//            
//            attrDefInfoList.add(attrDefInfo);
//        }
//        PageInfoForRes<AttrDefInfo> AttrDefInfoPage = new PageInfoForRes<>();
//        AttrDefInfoPage.setResult(attrDefInfoList);
//        AttrDefInfoPage.setPageNo(pageInfo.getPageNo());;
//        AttrDefInfoPage.setPageSize(pageInfo.getPageSize());
//        
//        return null;
//    }
//
//    @Override
//    public int updateAttr(AttrParam attrParam) {
//        ProdAttrDef prodAttrDef = new ProdAttrDef();
//        prodAttrDef.setAttrId(attrParam.getAttrId());
//        prodAttrDef.setOperId(attrParam.getOperId());
//        prodAttrDef.setAttrName(attrParam.getAttrName());
//        if(attrParam.getFirstLetter() != null)
//            prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
//        prodAttrDef.setValueWay(attrParam.getValueWay());
//        prodAttrDef.setIsAllowCustom(attrParam.getIsCustom());
//        return prodAttrDefAtomSV.updateAttr(prodAttrDef);
//    }
//
//    @Override
//    public int deleteAttr(AttrPam attrPam) {
//        if(tenantId == null || attrId == null) 
//                throw new BusinessException("","未找到指定的属性信息，租户ID="+tenantId+",属性标识="+attrId);
//        //TODU 通过属性标识下所有属性信息
////        prodAttrValDefAtomSV.deleteProdAttrVal(tenantId, attrValueId);
//        return prodAttrDefAtomSV.deleteById(tenantId, attrId);
//    }
//
//    @Override
//    public int insertAttr(List<AttrParam> attrParamList) {
//        int count = 0;
//        for(AttrParam attrParam : attrParamList){
//            ProdAttrDef prodAttrDef = new ProdAttrDef();
//            prodAttrDef.setAttrId(attrParam.getAttrId());
//            prodAttrDef.setOperId(attrParam.getOperId());
//            prodAttrDef.setAttrName(attrParam.getAttrName());
//            if(attrParam.getFirstLetter() != null)
//                prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
//            prodAttrDef.setValueWay(attrParam.getValueWay());
//            prodAttrDef.setIsAllowCustom(attrParam.getIsCustom());
//            int ok = prodAttrDefAtomSV.installObj(prodAttrDef);
//            if(ok == 0)
//                throw new BusinessException("","添加属性失败，属性名称="+attrParam.getAttrName());
//            count++;
//        }
//            
//        return count;
//    }
//
//    @Override
//    public PageInfoForRes<AttrValInfo> selectAttrvals(AttrValPageQuery attrValPageQuery) {
//        PageInfo<ProdAttrvalueDef> attrValPage = prodAttrValDefAtomSV.selectAttrValPage(attrValPageQuery);
//        List<ProdAttrvalueDef> attrValList = attrValPage.getResult();
//        List<AttrValInfo> attrValInfoList = new ArrayList<AttrValInfo>();
//        for(ProdAttrvalueDef attrVal : attrValList){
//            AttrValInfo attrValInfo = new AttrValInfo();
//            attrValInfo.setAttrvalueDefId(attrVal.getAttrvalueDefId());
//            attrValInfo.setAttrValueName(attrVal.getAttrValueName());
//            attrValInfo.setFirstLetter(attrVal.getFirstLetter());
//            attrValInfo.setOperTime(attrVal.getOperTime());
//            attrValInfo.setOperId(attrVal.getOperId());
//            
//            attrValInfoList.add(attrValInfo);
//        }
//        PageInfoForRes<AttrValInfo> attrValInfo = new PageInfoForRes<AttrValInfo>();
//        attrValInfo.setResult(attrValInfoList);
//        attrValInfo.setPageNo(attrValPage.getPageNo());
//        attrValInfo.setPageSize(attrValPage.getPageSize());
//        
//        return null;
//    }
//
//    @Override
//    public AttrValInfo queryAttrVal(AttrValUniqueReq attrValParam) {
//        String attrvalueDefId = attrValParam.getAttrvalueDefId();
//        String tenantId = attrValParam.getTenantId();
//        ProdAttrvalueDef prodAttrvalueDef = prodAttrValDefAtomSV.selectById(tenantId, attrvalueDefId);
//        
//        AttrValInfo attrValInfo = new AttrValInfo();
//        attrValInfo.setAttrvalueDefId(attrvalueDefId);
////        attrValInfo.setTenantId(tenantId);
//        attrValInfo.setAttrValueName(prodAttrvalueDef.getAttrValueName());
//        attrValInfo.setFirstLetter(prodAttrvalueDef.getFirstLetter());
//        
//        return attrValInfo;
//    }
//
//    @Override
//    public int deleteAttrVal(AttrValUniqueReq attrValUniqueReq) {
//        String tenantId = attrValUniqueReq.getTenantId();
//        String attrValueId = attrValUniqueReq.getAttrvalueDefId();
//        if(tenantId == null || attrValueId == null) 
//            throw new BusinessException("","未找到指定的属性信息，租户ID="+attrValUniqueReq.getTenantId()+",属性标识="+attrValUniqueReq.getAttrvalueDefId());
//        return prodAttrValDefAtomSV.deleteProdAttrVal(tenantId, attrValueId);
//    }
//
//    @Override
//    public int updateAttrVal(AttrValParam attrValParam) {
//        ProdAttrvalueDef prodAttrvalueDef = new ProdAttrvalueDef();
//        prodAttrvalueDef.setAttrId(attrValParam.getAttrId());
//        prodAttrvalueDef.setAttrvalueDefId(attrValParam.getAttrvalueDefId());
//        prodAttrvalueDef.setAttrValueName(attrValParam.getAttrValueName());
//        prodAttrvalueDef.setFirstLetter(attrValParam.getFirstLetter());
//        prodAttrvalueDef.setOperId(attrValParam.getOperId());
//        prodAttrvalueDef.setTenantId(attrValParam.getTenantId());
//        
//        return prodAttrValDefAtomSV.updateProdAttrVal(prodAttrvalueDef);
//    }
//
//    @Override
//    public int insertAttrVal(List<AttrValParam> attrValParamList) {
//        int count = 0;
//        for(AttrValParam attrValParam : attrValParamList){
//            ProdAttrvalueDef prodAttrvalueDef = new ProdAttrvalueDef();
//            
//            prodAttrvalueDef.setAttrId(attrValParam.getAttrId());
//            prodAttrvalueDef.setAttrvalueDefId(attrValParam.getAttrvalueDefId());
//            prodAttrvalueDef.setAttrValueName(attrValParam.getAttrValueName());
//            prodAttrvalueDef.setFirstLetter(attrValParam.getFirstLetter());
//            prodAttrvalueDef.setOperId(attrValParam.getOperId());
//            //设置状态有效：1有效，0无效。
//            prodAttrvalueDef.setState("1");
//            int ok = prodAttrValDefAtomSV.insertProdAttrVal(prodAttrvalueDef);
//            if(ok == 0)
//                throw new BusinessException("","添加属性值失败，属性值名称="+attrValParam.getAttrValueName());
//            count ++;
//        }
//        return count;
//    }
//
//    @Override
//    public MapForRes<AttrDef, List<AttrValDef>> queryAttrAndAttVals() {
//        MapForRes<AttrDef, List<AttrValDef>> attrAndValues = new MapForRes<>();
//        List<ProdAttrDef> prodAttrList = prodAttrDefAtomSV.selectAllAttrs();
//        for(ProdAttrDef prodAttr : prodAttrList){
//            AttrDef attrDef = new AttrDef();
//            BeanUtils.copyProperties(attrDef, prodAttr);
//            //属性值集合
//            List<ProdAttrvalueDef> prodAttrValList = prodAttrValDefAtomSV.selectAttrValForAttr(prodAttr.getAttrId());
//            List<AttrValDef> attrValList = new ArrayList<AttrValDef>();
//            for(ProdAttrvalueDef prodAttrVal : prodAttrValList){
//                AttrValDef attrVal = new AttrValDef();
//                BeanUtils.copyProperties(prodAttrVal, prodAttrVal);
//                attrValList.add(attrVal);
//            }
//            attrAndValues.put(attrDef, attrValList);
//        }
//        return attrAndValues;
//    }
//    
//    
//    
//
//}
