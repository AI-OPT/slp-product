package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.interfaces.ISearchProductSV;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;
import com.ai.slp.product.constants.ProductHomeConstants;
import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.api.impl.ProductSearchImpl;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.util.ConvertImageUtil;
import com.ai.slp.product.util.ValidateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
@Service(validation = "true")
@Component
public class ISearchProductSVImpl implements ISearchProductSV {

    @Override
    public ProductQueryResponse queryProductPage(ProductQueryRequest request)
            throws BusinessException, SystemException {
        ProductQueryResponse response = new ProductQueryResponse();
        PageInfo<ProductData> pageinfo = new PageInfo<ProductData>();
        List<ProductData> results = new ArrayList<ProductData>();
        IProductSearch productSearch = new ProductSearchImpl();
        String userid="";
        if(!StringUtil.isBlank(request.getUserId())){
            userid = request.getUserId();
        }
        UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,userid);
         if(ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUserType())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE,userid);
        }else if(ProductHomeConstants.UserType.AGENCY.equals(request.getUserType())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY,userid);
        }
         ProductSearchCriteria productSearchCriteria =
                     new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),user)
                     .basicOrgIdIs(request.getBasicOrgIdIs()).categoryIdIs(request.getProductCatId()).attrValueDefID(request.getAttrDefId()).build();
        Results<Map<String, Object>>  result = productSearch.search(productSearchCriteria);
        List<Map<String,Object>> reslist = result.getSearchList();
        String info = JSON.toJSONString(reslist);
        List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
        for(SKUInfo sku:skuList){
            ProductData product = new ProductData();
            product.setSalePrice(sku.getPrice());
            product.setProdName(sku.getProductname());
            product.setProdId(sku.getProductid());
            String imageinfo = JSON.toJSONString(sku.getImageinfo());
            product.setImageinfo(ConvertImageUtil.convert(imageinfo));
            //product.setImageinfo(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            String images = JSON.toJSONString(sku.getThumbnail());
            //List<ProductImage> imageList = JSON.parseObject(images,new TypeReference<List<ProductImage>>(){}); 
            product.setThumbnail(ConvertImageUtil.convertThum(images));
            results.add(product);
        }
        pageinfo.setResult(results);
        pageinfo.setPageSize(request.getPageInfo().getPageSize());
        pageinfo.setPageNo(request.getPageInfo().getPageNo());
        pageinfo.setCount(skuList.size());
        response.setPageInfo(pageinfo);
        return response;
    }

    @Override
    public List<ProductData> queryHotSellProduct(ProductQueryRequest request) throws BusinessException, SystemException {
        //入参校验
        ValidateUtil.validateHotProduct(request);
        List<ProductData> proList = new ArrayList<ProductData>();
        IProductSearch productSearch = new ProductSearchImpl();
        String userid="";
        if(!StringUtil.isBlank(request.getUserId())){
            userid = request.getUserId();
        }
        UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,userid);
         if(ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUserType())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE,userid);
        }else if(ProductHomeConstants.UserType.AGENCY.equals(request.getUserType())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY,userid);
        }
         ProductSearchCriteria productSearchCriteria;
         if(StringUtil.isBlank(request.getProductCatId())){
              productSearchCriteria =
                     new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),user)
                     .orderBy(ProductHomeConstants.ORDER_FILE_NAME).maxSearchSize(ProductHomeConstants.HOT_MAX_SIZE).build();
         }else{
              productSearchCriteria =
                     new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),user)
                     .categoryIdIs(request.getProductCatId()).orderBy(ProductHomeConstants.ORDER_FILE_NAME).maxSearchSize(ProductHomeConstants.HOT_MAX_SIZE).build();
         }
        Results<Map<String, Object>>  result = productSearch.search(productSearchCriteria);
        List<Map<String,Object>> objList = result.getSearchList();
        String info = JSON.toJSONString(objList);
        List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
        for(SKUInfo sku:skuList){
            ProductData product = new ProductData();
            product.setSalePrice(sku.getPrice());
            product.setProdName(sku.getProductname());
            product.setProdId(sku.getProductid());
            String imageinfo = JSON.toJSONString(sku.getImageinfo());
            product.setImageinfo(ConvertImageUtil.convert(imageinfo));
            //product.setImageinfo(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            proList.add(product);
        }
        return proList;
    }

}
