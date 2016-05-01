package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.normproduct.param.PageInfoWrapper;
import com.ai.slp.product.api.normproduct.param.ProductCatInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatPageQuery;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieliu on 16/4/29.
 */
@Service
@Transactional
public class ProductCatBusiSVImpl implements IProductCatBusiSV {
    @Autowired
    IProdCatDefAtomSV prodCatDefAtomSV;

    @Override
    public PageInfoWrapper<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery) {
        PageInfo<ProductCat> catInfoPageInfo = prodCatDefAtomSV.queryForPage(
                pageQuery.getPageNo(),pageQuery.getPageSize(),pageQuery.getParentProductCatId(),
                pageQuery.getTenantId(),pageQuery.getProductCatId(),
                pageQuery.getProductCatName(),pageQuery.getIsChild()
        );
        PageInfoWrapper<ProductCatInfo> pageInfoWrapper = new PageInfoWrapper<>();
        pageInfoWrapper.setCount(catInfoPageInfo.getCount());
        pageInfoWrapper.setPageSize(catInfoPageInfo.getPageSize());
        pageInfoWrapper.setPageCount(catInfoPageInfo.getPageCount());
        pageInfoWrapper.setPageNo(catInfoPageInfo.getPageNo());
        List<ProductCat> productCatList = catInfoPageInfo.getResult();
        List<ProductCatInfo> catInfoList = new ArrayList<>();
        pageInfoWrapper.setResult(catInfoList);
        for (ProductCat productCat:productCatList){
            ProductCatInfo productCatInfo = new ProductCatInfo();
            BeanUtils.copyProperties(productCatInfo,productCat);
            productCatInfo.setSerialNumber(productCat.getSerialNumber());
            catInfoList.add(productCatInfo);
        }
        return pageInfoWrapper;
    }

    @Override
    public void addCatList(List<ProductCatParam> pcpList) {
        if (pcpList==null || pcpList.isEmpty())
            return;
        for(ProductCatParam catParam:pcpList){
            ProductCat productCat = new ProductCat();
            BeanUtils.copyProperties(productCat,catParam);
            prodCatDefAtomSV.insertProductCat(productCat);
        }
    }

    @Override
    public ProductCatInfo queryByCatId(String tenantId, String productCatId) {
        ProductCatInfo productCatInfo = null;
        ProductCat productCat = prodCatDefAtomSV.selectById(tenantId,productCatId);
        if (productCat!=null){
            productCatInfo = new ProductCatInfo();
            BeanUtils.copyProperties(productCatInfo,productCat);
        }
        return productCatInfo;
    }

    @Override
    public void updateByCatId(ProductCatParam catParam) {
        if (catParam==null)
            throw new BusinessException("","请求信息为空,无法更新");
        ProductCat productCat = new ProductCat();
        BeanUtils.copyProperties(productCat,catParam);
        prodCatDefAtomSV.updateProductCat(productCat);
    }
}
