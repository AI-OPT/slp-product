package com.ai.slp.product.api.exproduct.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.exproduct.interfaces.IExSearchProductSV;
import com.ai.slp.product.api.exproduct.param.ProductAttrDef;
import com.ai.slp.product.api.exproduct.param.ProductAttrValueDef;
import com.ai.slp.product.api.exproduct.param.ProductDataResponse;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;
import com.ai.slp.product.api.exproduct.param.SaleArea;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.search.bo.ProdAudiences;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.SaleAreaInfo;
import com.ai.slp.product.service.business.impl.exsearch.ExProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.exsearch.IExProductSearch;
import com.ai.slp.product.util.ExProductValidata;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
@Service(validation = "true")
@Component
public class IExSearchProductSVImpl implements IExSearchProductSV{

    @Override
    public QueryProductResponse queryProductPage(QueryProductRequest request)
            throws BusinessException, SystemException {
        //有效性校验
        ExProductValidata.validateSearch(request);
        QueryProductResponse response = new QueryProductResponse();
        PageInfo<ProductDataResponse> pageinfo = new PageInfo<ProductDataResponse>();
        List<ProductDataResponse> results = new ArrayList<ProductDataResponse>();
        IExProductSearch exProductSearch = new ExProductSearchImpl();
        int startSize=0;
        int maxSize=1;
         //最大条数设置
             int pageNo = request.getPageNo();
             int size = request.getPageSize();
             if(request.getPageNo()==1){
                 startSize=0;
             }else{
                 startSize = (pageNo-1)*size;
             }
         maxSize=size;
         ExProductSearchCriteria exProductSearchCriteria;
         Results<Map<String, Object>>  result = new  Results<Map<String, Object>>();
          //如果若为空，默认查全部user_id专属
           if(request.getProdRangeType()=="" || request.getProdRangeType()==null){
               //判断充值类型
               if(StringUtil.isBlank(request.getRechargeType())){
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .startSize(startSize).maxSearchSize(maxSize).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userIdMust(request.getUserId()).build();
                       result = exProductSearch.search(exProductSearchCriteria); 
               }else{
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .startSize(startSize).maxSearchSize(maxSize).rechargeTypeIs(request.getRechargeType()).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userIdMust(request.getUserId()).build();
                   result = exProductSearch.search(exProductSearchCriteria);
               }
           }else if(request.getProdRangeType()=="-1"){
               //判断充值类型
               if(StringUtil.isBlank(request.getRechargeType())){
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .startSize(startSize).maxSearchSize(maxSize).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userTypeMust(request.getUserType()).build();
                    result = exProductSearch.search(exProductSearchCriteria);
               }else{
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .startSize(startSize).maxSearchSize(maxSize).rechargeTypeIs(request.getRechargeType()).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userTypeMust(request.getUserType()).build();
                   result = exProductSearch.search(exProductSearchCriteria);
               }
           }
           List<Map<String,Object>> reslist = result.getSearchList();
           String info = JSON.toJSONString(reslist);
           List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
           for(SKUInfo sku:skuList){
               ProductDataResponse product = new ProductDataResponse();
               product.setSalePrice(sku.getPrice());
               product.setSkuName(sku.getSkuname());
               product.setSkuId(sku.getSkuid());
               product.setBasicOrgId(sku.getBasicorgid());
               //获取受众id
               List<ProdAudiences> list = sku.getAudiences();
               if(!CollectionUtil.isEmpty(list)){
            	   ProdAudiences p = list.get(0);
                   product.setProdRangeType( p.getUserid());
                   product.setSaleNationWide(sku.getSalenationwide());
               }
               //省份列表
               List<SaleArea> proList = new ArrayList<SaleArea>();
               List<SaleAreaInfo>areaList = sku.getSaleareainfos();
               if(!CollectionUtil.isEmpty(areaList)){
                   for(SaleAreaInfo areaInfo:areaList){
                       SaleArea area = new SaleArea();
                       area.setProvCode(areaInfo.getProvcode());
                       proList.add(area);
                   }
               }
               //属性、属性值列表
               List<ProductAttrDef> attrList = new ArrayList<ProductAttrDef>();
               List<ProductAttrValueDef> attrValueList = new ArrayList<ProductAttrValueDef>();
               List<AttrInfo> attrInfos = sku.getAttrInfos();
               if(!CollectionUtil.isEmpty(attrInfos)){
                   for(AttrInfo attr:attrInfos){
                       ProductAttrDef attrDef = new ProductAttrDef();
                       ProductAttrValueDef attrValueDef = new ProductAttrValueDef();
                       attrDef.setAttrId(attr.getAttrid());
                       attrDef.setAttrName(attr.getAttrname());
                       attrValueDef.setAttrValueId(attr.getAttrvaluedefid());
                       attrValueDef.setAttrValueName(attr.getAttrvalue());
                       attrList.add(attrDef);
                       attrValueList.add(attrValueDef);
                   }
               }
               product.setAttrList(attrList);
               product.setAttrValueList(attrValueList);
               product.setSaleAreaInfos(proList);
               results.add(product);
           }
           pageinfo.setResult(results);
           pageinfo.setCount(getProductCount(request));
           response.setPageInfo(pageinfo);
           return response;
    }
    //获取查询数量
    public int getProductCount(QueryProductRequest request)
            throws BusinessException, SystemException {
        IExProductSearch exProductSearch = new ExProductSearchImpl();
         ExProductSearchCriteria exProductSearchCriteria;
         Results<Map<String, Object>>  result = new  Results<Map<String, Object>>();
          //如果若为空，默认查全部user_id专属
           if(request.getProdRangeType()=="" || request.getProdRangeType()==null){
               //判断充值类型
               if(StringUtil.isBlank(request.getRechargeType())){
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userIdMust(request.getUserId()).build();
                       result = exProductSearch.search(exProductSearchCriteria); 
               }else{
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .rechargeTypeIs(request.getRechargeType()).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userIdMust(request.getUserId()).build();
                   result = exProductSearch.search(exProductSearchCriteria);
               }
           }else if(request.getProdRangeType()=="-1"){
               //判断充值类型
               if(StringUtil.isBlank(request.getRechargeType())){
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userTypeMust(request.getUserType()).build();
                    result = exProductSearch.search(exProductSearchCriteria);
               }else{
                   exProductSearchCriteria =
                           new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                           .rechargeTypeIs(request.getRechargeType()).categoryIdIs(request.getProductCatId()).tenantID(request.getTenantId()).userTypeMust(request.getUserType()).build();
                   result = exProductSearch.search(exProductSearchCriteria);
               }
           }
           List<Map<String,Object>> reslist = result.getSearchList();
           String info = JSON.toJSONString(reslist);
           List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
           if(!CollectionUtil.isEmpty(skuList)){
               return skuList.size();
           }else{
               return 0;
           }
    }
}
