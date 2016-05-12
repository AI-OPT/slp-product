package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.productcat.param.AttrDef;
import com.ai.slp.product.api.productcat.param.AttrDefInfo;
import com.ai.slp.product.api.productcat.param.AttrDefParam;
import com.ai.slp.product.api.productcat.param.AttrInfo;
import com.ai.slp.product.api.productcat.param.AttrPam;
import com.ai.slp.product.api.productcat.param.AttrParam;
import com.ai.slp.product.api.productcat.param.AttrVal;
import com.ai.slp.product.api.productcat.param.AttrValDef;
import com.ai.slp.product.api.productcat.param.AttrValInfo;
import com.ai.slp.product.api.productcat.param.AttrValPageQuery;
import com.ai.slp.product.api.productcat.param.AttrValParam;
import com.ai.slp.product.api.productcat.param.AttrValUniqueReq;
import com.ai.slp.product.api.productcat.param.MapForRes;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;
import com.ai.slp.product.vo.AttrAndValPageQueryVo;

@Service
@Transactional
public class AttrAndAttrvalBusiSVImpl implements IAttrAndAttrvalBusiSV {

    @Autowired
    IProdAttrDefAtomSV prodAttrDefAtomSV;
    @Autowired
    IProdAttrValDefAtomSV prodAttrValDefAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;

    @Override
    public AttrInfo queryAttrById(String tenantId, Long attrId) {
        ProdAttrDef prodAttrDef = prodAttrDefAtomSV.selectById(tenantId, attrId);
        if (prodAttrDef == null)
            throw new BusinessException("", "未找到指定的属性信息，租户ID=" + tenantId + ",属性标识=" + attrId);
        AttrInfo attrInfo = new AttrInfo();
        BeanUtils.copyProperties(attrInfo, prodAttrDef);

        return attrInfo;
    }

    @Override
    public PageInfoResponse<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam) {
        AttrAndValPageQueryVo attrAndValPageQueryVo = new AttrAndValPageQueryVo();
        BeanUtils.copyProperties(attrAndValPageQueryVo, attrDefParam);
        PageInfo<ProdAttrDef> pageInfo = prodAttrDefAtomSV.selectPageAttrs(attrAndValPageQueryVo);
        List<ProdAttrDef> prodAttrDefList = pageInfo.getResult();
        List<AttrDefInfo> attrDefInfoList = new ArrayList<AttrDefInfo>();
        for (ProdAttrDef prodAttrDef : prodAttrDefList) {
            AttrDefInfo attrDefInfo = new AttrDefInfo();
            BeanUtils.copyProperties(attrDefInfo, prodAttrDef);

            attrDefInfoList.add(attrDefInfo);
        }
        PageInfoResponse<AttrDefInfo> AttrDefInfoPage = new PageInfoResponse<>();
        AttrDefInfoPage.setResult(attrDefInfoList);
        AttrDefInfoPage.setPageNo(pageInfo.getPageNo());
        AttrDefInfoPage.setPageSize(pageInfo.getPageSize());

        return AttrDefInfoPage;
    }

    @Override
    public int updateAttr(AttrParam attrParam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrParam.getTenantId(), attrParam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrParam.getTenantId(), attrParam.getAttrId()) > 0)
            throw new BusinessException("", "该属性已被使用，不能修改");
        
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        BeanUtils.copyProperties(prodAttrDef, attrParam);
        if (attrParam.getFirstLetter() != null)
            prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
        return prodAttrDefAtomSV.updateAttr(prodAttrDef);
    }

    @Override
    public int deleteAttr(AttrPam attrPam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrPam.getTenantId(), attrPam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrPam.getTenantId(), attrPam.getAttrId()) > 0)
            throw new BusinessException("", "该属性已被使用，不能删除");
        
        if (attrPam.getTenantId() == null)
            throw new BusinessException("", "未找到指定的属性信息，租户ID=" + attrPam.getTenantId());
        return prodAttrDefAtomSV.deleteById(attrPam.getTenantId(), attrPam.getAttrId(),attrPam.getOperId());
    }

    @Override
    public int addAttr(List<AttrParam> attrParamList) {
        int count = 0;
        for (AttrParam attrParam : attrParamList) {
            ProdAttrDef prodAttrDef = new ProdAttrDef();
            BeanUtils.copyProperties(prodAttrDef, attrParam);
            if (attrParam.getFirstLetter() != null)
                prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
            int ok = prodAttrDefAtomSV.installObj(prodAttrDef);
            if (ok == 0)
                throw new BusinessException("", "添加属性失败，属性名称=" + attrParam.getAttrName());
            count++;
        }
        return count;
    }

    @Override
    public PageInfoResponse<AttrValInfo> queryAttrvals(AttrValPageQuery attrValPageQuery) {
        AttrAndValPageQueryVo attrAndValPageQueryVo = new AttrAndValPageQueryVo();
        BeanUtils.copyProperties(attrAndValPageQueryVo, attrValPageQuery);
        PageInfo<ProdAttrvalueDef> attrValPage = prodAttrValDefAtomSV
                .selectAttrValPage(attrAndValPageQueryVo);
        List<ProdAttrvalueDef> attrValList = attrValPage.getResult();
        List<AttrValInfo> attrValInfoList = new ArrayList<AttrValInfo>();
        for (ProdAttrvalueDef attrVal : attrValList) {
            AttrValInfo attrValInfo = new AttrValInfo();
            BeanUtils.copyProperties(attrValInfo, attrVal);

            attrValInfoList.add(attrValInfo);
        }
        PageInfoResponse<AttrValInfo> attrValInfo = new PageInfoResponse<AttrValInfo>();
        attrValInfo.setResult(attrValInfoList);
        attrValInfo.setPageNo(attrValPage.getPageNo());
        attrValInfo.setPageSize(attrValPage.getPageSize());

        return attrValInfo;
    }

    @Override
    public AttrVal queryAttrVal(AttrValUniqueReq attrValParam) {
        ProdAttrvalueDef prodAttrvalueDef = prodAttrValDefAtomSV
                .selectById(attrValParam.getTenantId(), attrValParam.getAttrvalueDefId());

        AttrVal attrVal = new AttrVal();
        BeanUtils.copyProperties(attrVal, prodAttrvalueDef);

        return attrVal;
    }

    @Override
    public int deleteAttrVal(AttrValUniqueReq attrValUniqueReq) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrValUniqueReq.getTenantId(), attrValUniqueReq.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrValUniqueReq.getTenantId(), attrValUniqueReq.getAttrId()) > 0)
            throw new BusinessException("", "该属性已被使用，不能删除");
        
        return prodAttrValDefAtomSV.deleteProdAttrVal(attrValUniqueReq.getTenantId(),
                attrValUniqueReq.getAttrvalueDefId(), attrValUniqueReq.getOperId());
    }

    @Override
    public int updateAttrVal(AttrValParam attrValParam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrValParam.getTenantId(), attrValParam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrValParam.getTenantId(), attrValParam.getAttrId()) > 0)
            throw new BusinessException("", "该属性已被使用，不能修改");

        ProdAttrvalueDef prodAttrvalueDef = new ProdAttrvalueDef();
        BeanUtils.copyProperties(prodAttrvalueDef, attrValParam);

        return prodAttrValDefAtomSV.updateProdAttrVal(prodAttrvalueDef);
    }

    @Override
    public int addAttrVal(List<AttrValParam> attrValParamList) {
        int count = 0;
        for (AttrValParam attrValParam : attrValParamList) {
            ProdAttrvalueDef prodAttrvalueDef = new ProdAttrvalueDef();
            BeanUtils.copyProperties(prodAttrvalueDef, attrValParam);
            int ok = prodAttrValDefAtomSV.insertProdAttrVal(prodAttrvalueDef);
            if (ok == 0)
                throw new BusinessException("", "添加属性值失败，属性值名称=" + attrValParam.getAttrValueName());
            count++;
        }
        return count;
    }

    @Override
    public MapForRes<AttrDef, List<AttrValDef>> queryAllAttrAndVals(String tenantId) {
        MapForRes<AttrDef, List<AttrValDef>> attrAndValues = new MapForRes<>();
        //属性集合
        List<ProdAttrDef> prodAttrList = prodAttrDefAtomSV.selectAllAttrs(tenantId);
        for (ProdAttrDef prodAttr : prodAttrList) {
            AttrDef attrDef = new AttrDef();
            BeanUtils.copyProperties(attrDef, prodAttr);
            // 属性值集合
            List<ProdAttrvalueDef> prodAttrValList = prodAttrValDefAtomSV
                    .selectAttrValForAttr(prodAttr.getTenantId(), prodAttr.getAttrId());
            List<AttrValDef> attrValList = new ArrayList<AttrValDef>();
            for (ProdAttrvalueDef prodAttrVal : prodAttrValList) {
                AttrValDef attrVal = new AttrValDef();
                BeanUtils.copyProperties(attrVal, prodAttrVal);
                attrValList.add(attrVal);
            }
            attrAndValues.put(attrDef, attrValList);
        }
        return attrAndValues;
    }

}
