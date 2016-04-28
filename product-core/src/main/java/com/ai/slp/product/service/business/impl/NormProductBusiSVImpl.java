package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.api.normproduct.param.*;
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
import com.ai.slp.product.vo.StandedProdPageQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        BeanUtils.copyProperties(standedProduct,normProdct);
        standedProduct.setStandedProductName(normProdct.getProductName());
        if (normProdct.getCreateTime()!=null)
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
            prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
            prodAttr.setAttrValueName(attrValReq.getAttrVal());
            prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
            prodAttr.setState(StandedProdAttrConstants.STATE_ACTIVE);//设置为有效
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
        //若状态是否由可用变更为不可用,则需要检查库存组是否全部都已经停用,

    }

    /**
     * 查询指定标准品信息
     *
     * @param tenantId  租户id
     * @param productId 标准品标识
     * @return
     */
    @Override
    public NormProductInfoResponse queryById(String tenantId, String productId) {
        StandedProduct product = standedProductAtomSV.selectById(tenantId,productId);
        if (product==null) {
            logger.warn("租户[{0}]下不存在标识[{1}]的标准品",tenantId,productId);
            throw new BusinessException("", "未找到对应标准品信息,租户id="+tenantId+",标准品标识="+productId);
        }
        NormProductInfoResponse response = new NormProductInfoResponse();
        //标准品信息填充返回值
        BeanUtils.copyProperties(response,product);
        response.setProductId(product.getStandedProdId());
        response.setProductName(product.getStandedProductName());
        response.setCreateName("");//TODO... 获取创建者名称
        response.setOperName("");//TODO... 获取操作者名称
//        response.setCreateTime(product.getCreateTime());
//        response.setOperTime(product.getOperTime());
        Map<Long,Set<String>> attrAndValueIds = new HashMap<>();
        //查询属性信息
        List<StandedProdAttr> attrList = standedProdAttrAtomSV.queryByNormProduct(tenantId,productId);
        for (StandedProdAttr prodAttr:attrList){
            Set<String> attrVal = attrAndValueIds.get(prodAttr.getAttrId());
            if (attrVal == null) {
                attrVal = new HashSet<>();
            }
            attrVal.add(prodAttr.getAttrvalueDefId());
            if (!attrAndValueIds.containsKey(prodAttr.getAttrId()))
                attrAndValueIds.put(prodAttr.getAttrId(),attrVal);
        }
        response.setAttrAndValueIds(attrAndValueIds);
        return response;
    }

    @Override
    public PageInfo<NormProductResponse> queryForPage(NormProductRequest productRequest) {
        StandedProdPageQueryVo pageQueryVo = new StandedProdPageQueryVo();
        BeanUtils.copyProperties(pageQueryVo,productRequest);
        //查询结果
        PageInfo<StandedProduct> productPageInfo = standedProductAtomSV.queryForPage(pageQueryVo);
        //接口输出接口
        PageInfo<NormProductResponse> normProdPageInfo = new PageInfo<NormProductResponse>();
        BeanUtils.copyProperties(normProdPageInfo,productPageInfo);
        //添加结果集
        List<StandedProduct> productList = productPageInfo.getResult();
        List<NormProductResponse> normProductList = new ArrayList<NormProductResponse>();
        normProdPageInfo.setResult(normProductList);
        for (StandedProduct standedProduct:productList){
            NormProductResponse normProduct = new NormProductResponse();
            BeanUtils.copyProperties(normProduct,standedProduct);
//            normProduct.setCreateName(""); //TODO...
//            normProduct.setOperTime(""); //TODO...
            //TODO... 获取类目名称
            normProduct.setCatId(standedProduct.getProductCatId());
            normProduct.setProductId(standedProduct.getStandedProdId());
            normProduct.setProductName(standedProduct.getStandedProductName());
            normProductList.add(normProduct);
        }
        return normProdPageInfo;
    }

    @Override
    public void discardProduct(String tenantId, String productId, Long operId, Date operTime) {

    }


}
