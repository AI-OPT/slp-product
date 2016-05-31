package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.constants.ProductHomeConstants;
import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.api.impl.ProductSearchImpl;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.util.ValidateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
@Service(validation = "true")
@Component
public class IProductHomeSVImpl implements IProductHomeSV {

    @Override
    public List<ProductHomeResponse> queryHomeDataProduct(ProductHomeRequest request) throws BusinessException, SystemException {
        //入参校验
        ValidateUtil.validateHomeProduct(request);
        IProductSearch productSearch = new ProductSearchImpl();
        String userid="";
        if(!StringUtil.isBlank(request.getUserid())){
            userid = request.getUserid();
        }
        UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,userid);
         if(ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUsertype())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE,userid);
        }else if(ProductHomeConstants.UserType.AGENCY.equals(request.getUsertype())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY,userid);
        }
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),user)
                .categoryIdIs(request.getProductCatId()).basicOrgIdIs(request.getBasicOrgIdIs()).build();
        Results<Map<String, Object>>  result = productSearch.search(productSearchCriteria);
        List<Map<String,Object>> list = result.getSearchList();
        String info = JSON.toJSONString(list);
        List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
        List<ProductHomeResponse> productList = new ArrayList<ProductHomeResponse>();
        for(SKUInfo sku:skuList){
            ProductHomeResponse product = new ProductHomeResponse();
            product.setSalePrice(sku.getPrice());
            product.setProdName(sku.getProductname());
            product.setProductSellPoint(sku.getProductsellpoint());
            product.setProdId(sku.getProductid());
            product.setProductImage(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            productList.add(product);
        }
        return productList;
    }

   

    @Override
    public List<ProductHomeResponse> queryHotProduct(ProductHomeRequest request) throws BusinessException, SystemException {
        IProductSearch productSearch = new ProductSearchImpl();
        String userid="";
        if(!StringUtil.isBlank(request.getUserid())){
            userid = request.getUserid();
        }
        UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,userid);
         if(ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUsertype())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE,userid);
        }else if(ProductHomeConstants.UserType.AGENCY.equals(request.getUsertype())){
            user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY,userid);
        }
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),user)
                .orderBy(ProductHomeConstants.ORDER_FILE_NAME).maxSearchSize(ProductHomeConstants.MAX_SIZE).build();
        Results<Map<String, Object>>  result = productSearch.search(productSearchCriteria);
        List<Map<String,Object>> list = result.getSearchList();
        String info = JSON.toJSONString(list);
        List<SKUInfo> skuList = JSON.parseObject(info,new TypeReference<List<SKUInfo>>(){}); 
        List<ProductHomeResponse> productList = new ArrayList<ProductHomeResponse>();
        for(SKUInfo sku:skuList){
            ProductHomeResponse product = new ProductHomeResponse();
            product.setSalePrice(sku.getPrice());
            product.setProdName(sku.getProductname());
            product.setProductSellPoint(sku.getProductsellpoint());
            product.setProdId(sku.getProductid());
            product.setProductImage(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            productList.add(product);
        }
        return productList;
    }
}