package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.platform.common.api.sysuser.interfaces.ISysUserQuerySV;
import com.ai.platform.common.api.sysuser.param.SysUserQueryRequest;
import com.ai.platform.common.api.sysuser.param.SysUserQueryResponse;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.business.interfaces.IAttrAndAttrvalBusiSV;
import com.ai.slp.product.vo.AttrPageQueryVo;
import com.ai.slp.product.vo.AttrValPageQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        if (prodAttrDef == null){
        	throw new BusinessException("", "未找到指定的属性信息，租户ID=" + tenantId + ",属性标识=" + attrId);
        }
        AttrInfo attrInfo = new AttrInfo();
        BeanUtils.copyProperties(attrInfo, prodAttrDef);

        return attrInfo;
    }

    @Override
    public PageInfoResponse<AttrDefInfo> queryAttrs(AttrDefParam attrDefParam) {
        AttrPageQueryVo attrPageQueryVo = new AttrPageQueryVo();
        BeanUtils.copyProperties(attrPageQueryVo, attrDefParam);
        PageInfo<ProdAttrDef> pageInfo = prodAttrDefAtomSV.selectPageAttrs(attrPageQueryVo);
        List<ProdAttrDef> prodAttrDefList = pageInfo.getResult();
        List<AttrDefInfo> attrDefInfoList = new ArrayList<AttrDefInfo>();
        for (ProdAttrDef prodAttrDef : prodAttrDefList) {
        	Long attrId = prodAttrDef.getAttrId();
        	String tenantId = prodAttrDef.getTenantId();
            AttrDefInfo attrDefInfo = new AttrDefInfo();
            BeanUtils.copyProperties(attrDefInfo, prodAttrDef);
            //根据当前的属性ID 查询当前ID下的属性值的数量
            int attrValNum = prodAttrValDefAtomSV.selectAttrValNum(tenantId, attrId);
            attrDefInfo.setAttrValNum(attrValNum);
            attrDefInfoList.add(attrDefInfo);
        }
        PageInfoResponse<AttrDefInfo> attrDefInfoPage = new PageInfoResponse<>();
        attrDefInfoPage.setResult(attrDefInfoList);
        attrDefInfoPage.setPageNo(pageInfo.getPageNo());
        attrDefInfoPage.setPageSize(pageInfo.getPageSize());
        attrDefInfoPage.setCount(pageInfo.getCount());
        return attrDefInfoPage;
    }

    @Override
    public int updateAttr(AttrParam attrParam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrParam.getTenantId(), attrParam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrParam.getTenantId(), attrParam.getAttrId()) > 0){
        	throw new BusinessException("", "该属性已被使用，不能修改");
        }
        
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        BeanUtils.copyProperties(prodAttrDef, attrParam);
        if (attrParam.getFirstLetter() != null){
        	prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
        }
        return prodAttrDefAtomSV.updateAttr(prodAttrDef);
    }

    @Override
    public int deleteAttr(AttrPam attrPam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrPam.getTenantId(), attrPam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrPam.getTenantId(), attrPam.getAttrId()) > 0){
        	throw new BusinessException("", "该属性已被使用，不能删除");
        }
        return prodAttrDefAtomSV.deleteById(attrPam.getTenantId(), attrPam.getAttrId(),attrPam.getOperId());
    }

    @Override
    public int addAttr(List<AttrParam> attrParamList) {
        int count = 0;
        for (AttrParam attrParam : attrParamList) {
            ProdAttrDef prodAttrDef = new ProdAttrDef();
            BeanUtils.copyProperties(prodAttrDef, attrParam);
            prodAttrDef.setState(ProductConstants.ProdAttr.State.ACTIVE);
            if (attrParam.getFirstLetter() != null){
            	prodAttrDef.setFirstLetter(attrParam.getFirstLetter());
            }
            int ok = prodAttrDefAtomSV.installObj(prodAttrDef);
            if (ok == 0){
            	throw new BusinessException("", "添加属性失败，属性名称=" + attrParam.getAttrName());
            }
            count++;
        }
        return count;
    }

    @Override
    public PageInfoResponse<AttrValInfo> queryAttrvals(AttrValPageQuery attrValPageQuery) {
        AttrValPageQueryVo attrPageQueryVo = new AttrValPageQueryVo();
        BeanUtils.copyProperties(attrPageQueryVo, attrValPageQuery);
        PageInfo<ProdAttrvalueDef> attrValPage = prodAttrValDefAtomSV
                .selectAttrValPage(attrPageQueryVo);
        List<ProdAttrvalueDef> attrValList = attrValPage.getResult();
        List<AttrValInfo> attrValInfoList = new ArrayList<AttrValInfo>();
        //获取服务
        ISysUserQuerySV userQuerySv = DubboConsumerFactory.getService(ISysUserQuerySV.class);
        SysUserQueryRequest queryReq = new SysUserQueryRequest();
        queryReq.setTenantId(attrValPageQuery.getTenantId());
        for (ProdAttrvalueDef attrVal : attrValList) {
            AttrValInfo attrValInfo = new AttrValInfo();
            BeanUtils.copyProperties(attrValInfo, attrVal);
            attrValInfo.setCatAttrValId("");
            //设置工号
            queryReq.setId(Long.toString(attrVal.getOperId()));
            //查询
            SysUserQueryResponse queryRes = userQuerySv.queryUserInfo(queryReq);
            if(queryRes!=null){
            	//设置操作人名字
            	attrValInfo.setOperName(queryRes.getName());
            }
            attrValInfoList.add(attrValInfo);
        }
        PageInfoResponse<AttrValInfo> attrValInfo = new PageInfoResponse<AttrValInfo>();
        attrValInfo.setResult(attrValInfoList);
        attrValInfo.setPageNo(attrValPage.getPageNo());
        attrValInfo.setPageSize(attrValPage.getPageSize());
        //设置数据总条数
        attrValInfo.setCount(attrValPage.getCount());
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
        if(prodCatAttrAtomSV.selectCatNumByAttrValueId(attrValUniqueReq.getTenantId(), attrValUniqueReq.getAttrvalueDefId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttrValue(attrValUniqueReq.getTenantId(), attrValUniqueReq.getAttrvalueDefId()) > 0){
        	throw new BusinessException("", "该属性值已被使用，不能删除");
        }
        
        return prodAttrValDefAtomSV.deleteProdAttrVal(attrValUniqueReq.getTenantId(),
                attrValUniqueReq.getAttrvalueDefId(), attrValUniqueReq.getOperId());
    }

    @Override
    public int updateAttrVal(AttrValParam attrValParam) {
        if(prodCatAttrAtomSV.selectCatNumByAttrId(attrValParam.getTenantId(), attrValParam.getAttrId()) > 0
                || standedProdAttrAtomSV.queryProdNumOfAttr(attrValParam.getTenantId(), attrValParam.getAttrId()) > 0){
        	throw new BusinessException("", "该属性已被使用，不能修改");
        }

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
            if (ok == 0){
            	throw new BusinessException("", "添加属性值失败，属性值名称=" + attrValParam.getAttrValueName());
            }
            count++;
        }
        return count;
    }

    @Override
    public List<AttrDef> queryAllAttrAndVals(String tenantId) {
        List<AttrDef> attrAndValues = new ArrayList<>();
        //属性集合
        List<ProdAttrDef> prodAttrList = prodAttrDefAtomSV.selectAllAttrsOfFirstLetter(tenantId);
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
            attrDef.setValDefList(attrValList);
            attrAndValues.add(attrDef);
        }
        return attrAndValues;
    }

}
