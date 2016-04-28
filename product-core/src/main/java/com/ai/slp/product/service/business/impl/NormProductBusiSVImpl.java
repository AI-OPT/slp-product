package com.ai.slp.product.service.business.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.normproduct.param.NormProductAttrValRequest;
import com.ai.slp.product.api.normproduct.param.NormProductSaveRequest;
import com.ai.slp.product.constants.StandedProdAttrConstants;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.interfaces.StandedProdAttrLogMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductLogAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Transactional
public class NormProductBusiSVImpl implements INormProductBusiSV {
    private static Logger logger = LoggerFactory.getLogger(NormProductBusiSVImpl.class);
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStandedProductLogAtomSV standedProductLogAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;
    @Autowired
    IStandedProdAttrLogAtomSV standedProdAttrLogAtomSV;
    /**
     * 添加标准品
     * @param normProdct
     * @return 添加成功后的标准品的标识
     */
    @Override
    public String installNormProd(NormProductSaveRequest normProdct) {
        //添加标准品
        StandedProduct standedProduct = new StandedProduct();
        standedProduct.setTenantId(normProdct.getTenantId());
        standedProduct.setProductCatId(normProdct.getCatId());
        standedProduct.setStandedProductName(normProdct.getProductName());
        standedProduct.setProductType(normProdct.getProductType());
        standedProduct.setState(normProdct.getProductStatus());
        standedProduct.setCreateId(normProdct.getCreateId());
        standedProduct.setCreateTime(DateUtils.toTimeStamp(normProdct.getCreateTime()));
        //添加成功,添加标准品日志
        if (standedProductAtomSV.installObj(standedProduct)>0){
            StandedProductLog productLog = new StandedProductLog();
            BeanUtils.copyProperties(productLog,standedProduct);
            standedProductLogAtomSV.insert(productLog);
        }
        //添加标准品属性值
        List<NormProductAttrValRequest> attrValList = normProdct.getAttrValList();
        for (NormProductAttrValRequest attrValReq:attrValList){
            StandedProdAttr prodAttr = new StandedProdAttr();
            BeanUtils.copyProperties(prodAttr,attrValReq);
            prodAttr.setStandedProdId(standedProduct.getStandedProdId());
            prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
            prodAttr.setAttrValueName(attrValReq.getAttrVal());
            prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
            prodAttr.setState(StandedProdAttrConstants.STATE_ACTIVE);
            if (attrValReq.getOperTime()!=null)
                prodAttr.setOperTime(DateUtils.toTimeStamp(attrValReq.getOperTime()));
            //添加成功,添加日志
            if (standedProdAttrAtomSV.installObj(prodAttr)>0){
                StandedProdAttrLog prodAttrLog = new StandedProdAttrLog();
                BeanUtils.copyProperties(prodAttrLog,prodAttr);
                standedProdAttrLogAtomSV.installObj(prodAttrLog);
            }
        }
        return standedProduct.getStandedProdId();
    }

    /**
     * 更新标准品
     * @param normProdct
     */
    @Override
    public void updateNormProd(NormProductSaveRequest normProdct) {

    }
}
