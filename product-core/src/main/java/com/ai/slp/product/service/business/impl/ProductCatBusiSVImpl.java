package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.atom.interfaces.*;
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
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IStandedProductAtomSV productAtomSV;
    @Autowired
    IProdCatAttrValAtomSV prodCatAttrValAtomSV;

    @Override
    public PageInfoForRes<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery) {
        PageInfo<ProductCat> catInfoPageInfo = prodCatDefAtomSV.queryForPage(
                pageQuery.getPageNo(),pageQuery.getPageSize(),pageQuery.getParentProductCatId(),
                pageQuery.getTenantId(),pageQuery.getProductCatId(),
                pageQuery.getProductCatName(),pageQuery.getIsChild()
        );
        PageInfoForRes<ProductCatInfo> pageInfoWrapper = new PageInfoForRes<>();
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
        //查询分类
        ProductCat productCat = prodCatDefAtomSV.selectById(catParam.getTenantId(),catParam.getProductCatId());
        if (productCat==null)
            throw new BusinessException("","没有找到对应的类目信息,租户id:"+catParam.getTenantId()
                    +",类目标识:"+catParam.getProductCatId());
        //判断是否变更了"是否有子分类"选项
        if (!productCat.getIsChild().equals(catParam.getIsChild())){
            //若把有改成无
            if (ProductCatConstants.HAS_CHILD.equals(productCat.getIsChild())
                    && ProductCatConstants.NO_CHILD.equals(catParam.getIsChild())){
                //判断是否有子类目
                if (prodCatDefAtomSV.queryOfParent(productCat.getParentProductCatId())>0)
                    throw new BusinessException("","此类目下存在子类目,无法变更为[无子分类]");
            }else{
                //判断是否有关联属性
                List<ProdCatAttr> catAttrList =
                        prodCatAttrAtomSV.queryNumByCatId(productCat.getTenantId(),productCat.getProductCatId());
                if (catAttrList!=null && catAttrList.size()>0){
                    throw new BusinessException("","此类目已关联属性,无法变更为[有子分类]");
                }
            }
        }
        BeanUtils.copyProperties(productCat,catParam);
        prodCatDefAtomSV.updateProductCat(productCat);
    }

    @Override
    public void deleteByCatId(String tenantId, String productCatId) {
        //查询是否存在类目
        ProductCat productCat = prodCatDefAtomSV.selectById(tenantId,productCatId);
        if (productCat==null)
            throw new BusinessException("","未找到要删除类目,租户id:"+tenantId+",类目标识:"+productCatId);
        //判断是否关联了标准品
        if (productAtomSV.queryByCatId(productCatId)>0)
            throw new BusinessException("","已关联了标准品，不可删除");
        //判断是否有子类目
        if (prodCatDefAtomSV.queryOfParent(Long.parseLong(productCatId))>0)
            throw new BusinessException("","此类目下存在子类目,不可删除");
        List<ProdCatAttr> catAttrList =
                prodCatAttrAtomSV.queryNumByCatId(tenantId,productCatId);
        //删除属性对应关系
        if (catAttrList!=null && catAttrList.size()>0){
            for (ProdCatAttr catAttr:catAttrList){
                //删除类目下属性与属性值的对应关系
                prodCatAttrValAtomSV.deleteByCat(tenantId,catAttr.getCatAttrId());
                //删除类目与属性对应关系
                prodCatAttrAtomSV.deleteByCatId(catAttr.getCatAttrId());
            }
        }
        //删除类目
        prodCatDefAtomSV.deleteProductCat(tenantId,productCatId);
    }

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId, String productCatId) {
        List<ProductCatInfo> catInfoList = new ArrayList<ProductCatInfo>();
        queryCatFoLinkById(catInfoList,tenantId,productCatId);
        return catInfoList;
    }

    private void queryCatFoLinkById(List<ProductCatInfo> catInfoList,String tenantId, String productCatId){
        ProductCatInfo catInfo = queryByCatId(tenantId,productCatId);
        if (catInfo==null)
            return;
        //已经达到根目录
        if (catInfo.getParentProductCatId()==null){
            catInfoList.add(catInfo);
            return;
        }
        //若不是跟类目,则继续查询
        if (catInfo!=null && catInfo.getParentProductCatId()!=null)
            queryCatFoLinkById(catInfoList,tenantId,productCatId);
        catInfoList.add(catInfo);
    }
}
