package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.atom.interfaces.*;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IStandedProdAttrAtomSV prodAttrAtomSV;

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
                        prodCatAttrAtomSV.queryAttrsByCatId(productCat.getTenantId(),productCat.getProductCatId());
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
                prodCatAttrAtomSV.queryAttrsByCatId(tenantId,productCatId);
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

    /**
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    @Override
    public Map<ProdCatAttrDef, List<AttrValInfo>> querAttrOfCatByIdAndType(
            String tenantId, String productCatId, String attrType) {
        Map<ProdCatAttrDef, List<AttrValInfo>> catAttrDefListMap = new HashMap<>();
        //查询类目属性集合
        List<ProdCatAttrAttch> attrAttchList = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,productCatId,attrType);
        //查询属性对应的属性值
        for (ProdCatAttrAttch attrAttch:attrAttchList){
            ProdCatAttrDef catAttrDef = new ProdCatAttrDef();
            BeanUtils.copyProperties(catAttrDef,attrAttch);
            //查询此属性是否关联标准品
            int prodNum = prodAttrAtomSV.queryProdNumOfAttr(tenantId,attrAttch.getAttrId());
            catAttrDef.setHasProduct(prodNum>0?true:false);
            //查询属性对应的属性值
            List<ProdAttrvalueDef> catAttrValList =
                    catAttrAttachAtomSV.queryValListByCatAttr(tenantId,attrAttch.getCatAttrId());
            List<AttrValInfo> valInfoList = new ArrayList<>();
            catAttrDefListMap.put(catAttrDef,valInfoList);
            for (ProdAttrvalueDef attrvalueDef:catAttrValList){
                AttrValInfo attrValInfo = new AttrValInfo();
                BeanUtils.copyProperties(attrValInfo,attrvalueDef);
                valInfoList.add(attrValInfo);
            }
        }
        return catAttrDefListMap;
    }

    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     *
     * @param tenantId
     * @param productCatId
     * @param attrType
     * @return
     */
    @Override
    public Map<Long, Set<String>> queryAttrAndValIdByCatIdAndType(String tenantId, String productCatId, String attrType) {
        Map<Long,Set<String>> idMap = new HashMap<>();
        //查询类目和属性的关联关系
        List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId,productCatId,attrType);
        for (ProdCatAttr catAttr:catAttrList){
            Set<String> attrValIds = idMap.get(catAttr.getAttrId());
            if (attrValIds==null){
                attrValIds = new HashSet<>();
                idMap.put(catAttr.getAttrId(),attrValIds);
            }
            //查询关联关系对应属性值集合
            List<ProdCatAttrValue> attrValueList = prodCatAttrValAtomSV.queryByCatAttrId(tenantId,catAttr.getCatAttrId());
            for (ProdCatAttrValue attrValue:attrValueList){
                attrValIds.add(attrValue.getAttrvalueDefId());
            }
        }
        return idMap;
    }


    private void queryCatFoLinkById(List<ProductCatInfo> catInfoList,String tenantId, String productCatId){
        ProductCatInfo catInfo = queryByCatId(tenantId,productCatId);
        if (catInfo==null)
            return;
        //已经达到根目录
        if (catInfo.getParentProductCatId()==null){
            catInfoList.add(catInfo);
            return;
        //若不是跟类目,则继续查询
        }else
            queryCatFoLinkById(catInfoList,tenantId,productCatId);
            catInfoList.add(catInfo);
    }
}
