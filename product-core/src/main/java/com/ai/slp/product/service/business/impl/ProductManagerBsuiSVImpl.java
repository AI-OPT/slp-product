package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.param.AudiencesSetOfProduct;
import com.ai.slp.product.api.product.param.ProdAudiencesInfo;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAudiencesAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBsuiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/6/6.
 */
@Service
@Transactional
public class ProductManagerBsuiSVImpl implements IProductManagerBsuiSV {
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;
    @Autowired
    IProdPictureAtomSV pictureAtomSV;
    @Autowired
    IProdAudiencesAtomSV prodAudiencesAtomSV;
    /**
     * 商品管理中分页查询商品信息
     *
     * @param queryReq
     * @return
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryPageForEdit(ProductEditQueryReq queryReq) {
        String tenantId = queryReq.getTenantId();
        //查询所有符合条件商品
        PageInfo<Product> productPage = productAtomSV.selectPageForEdit(queryReq);
        List<ProductEditUp> editUpList = new ArrayList<>();
        for (Product product:productPage.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null)
                productEditUp.setProductCatName(cat.getProductCatName());
            //查询主预览图
            ProdPicture prodPicture = pictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
            editUpList.add(productEditUp);
        }
        PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
        BeanUtils.copyProperties(response,productPage);
        response.setResult(editUpList);
        return response;
    }

    /**
     * 查询商品的受众信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public AudiencesSetOfProduct queryAudiencesOfProd(String tenantId, String prodId) {
        AudiencesSetOfProduct audiencesSet = new AudiencesSetOfProduct();
        //查询个人
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId, prodId,ProductConstants.ProdAudiences.userType.PERSON,null,false);
        if (CollectionUtil.isEmpty(boList)){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,boList.get(0));
            audiencesSet.setPersonAudiences(audiencesInfo);
        }
        //企业
        audiencesSet.setEnterpriseMap(getAudiencesInfo(tenantId,prodId,ProductConstants.ProdAudiences.userType.ENTERPRISE));
        //代理商
        audiencesSet.setAgentsMap(getAudiencesInfo(tenantId,prodId,ProductConstants.ProdAudiences.userType.AGENT));
        return audiencesSet;
    }

    private Map<String,ProdAudiencesInfo> getAudiencesInfo(String tenantId,String prodId,String userType){
        //企业
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId,prodId, userType,null,false);
        Map<String,ProdAudiencesInfo> audiencesMap = new HashMap<>();
        for (ProdAudiences audiences:boList){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,audiences);
            audiencesMap.put(audiences.getUserId(),audiencesInfo);
        }
        return audiencesMap;
    }
}
