package com.ai.slp.product.api.webfront.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.*;
import com.ai.slp.product.constants.ProductHomeConstants;
import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.api.impl.ProductSearchImpl;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.ai.slp.product.util.ConvertImageUtil;
import com.ai.slp.product.util.ValidateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service(validation = "true")
@Component
public class IProductHomeSVImpl implements IProductHomeSV {
    @Autowired
    IProductBusiSV productBusiSV;

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
            String imageinfo = JSON.toJSONString(sku.getImageinfo());
            product.setProductImage(ConvertImageUtil.convert(imageinfo));
            //product.setProductImage(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            productList.add(product);
        }
        return productList;
    }

   

    @Override
    public List<ProductHomeResponse> queryHotProduct(ProductHomeRequest request) throws BusinessException, SystemException {
        //入参校验
        ValidateUtil.validateHomeHotProduct(request);
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
                .addOrderBy(ProductHomeConstants.ORDER_FILE_NAME).maxSearchSize(ProductHomeConstants.MAX_SIZE).build();
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
            String imageinfo = JSON.toJSONString(sku.getImageinfo());
            product.setProductImage(ConvertImageUtil.convert(imageinfo));
            //product.setProductImage(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
            productList.add(product);
        }
        return productList;
    }

    /**
     * 获取快充产品
     *
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode
     */
    @Override
    public ListWrapperForRes<FastProductInfoRes> queryFastProduct(FastProductReq request) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(request.getTenantId(),"");
        ListWrapperForRes<FastProductInfoRes> resList = new ListWrapperForRes<FastProductInfoRes>();
//        resList.setObjList(productBusiSV.queryFastInfoList(request));
        resList.setObjList(getDemoFastInfo());
        return resList;
    }

    private List<FastProductInfoRes> getDemoFastInfo(){
        List<FastProductInfoRes> resList = new ArrayList<>();

        //SKU信息
        FastProductInfoRes infoRes0 = new FastProductInfoRes();
        resList.add(infoRes0);
        infoRes0.setSalePrice(1000l);
        infoRes0.setSkuId("123");
        //SKU关键属性信息
        List<ProductSKUAttr> skuAttrList00 = new ArrayList<>();
        infoRes0.setSkuAttrList(skuAttrList00);
        //使用范围属性
        ProductSKUAttr skuAttr000 = new ProductSKUAttr();
        skuAttrList00.add(skuAttr000);
        skuAttr000.setAttrName("使用范围");
        skuAttr000.setAttrId(100005l);
        skuAttr000.setAttrType("1");
        //使用范围属性值
        List<ProductSKUAttrValue> attrValueList0000 = new ArrayList<>();
        skuAttr000.setAttrValueList(attrValueList0000);
        ProductSKUAttrValue attrValue00000 = new ProductSKUAttrValue();
        attrValueList0000.add(attrValue00000);
        attrValue00000.setAttrvalueDefId("123");
        attrValue00000.setAttrValueName("全国");
        //流量面额属性
        ProductSKUAttr skuAttr001 = new ProductSKUAttr();
        skuAttrList00.add(skuAttr001);
        skuAttr001.setAttrName("流量面额");
        skuAttr001.setAttrId(100003l);
        skuAttr001.setAttrType("1");
        //流量面额属性值
        List<ProductSKUAttrValue> attrValueList0001 = new ArrayList<>();
        skuAttr001.setAttrValueList(attrValueList0001);
        ProductSKUAttrValue attrValue00001 = new ProductSKUAttrValue();
        attrValueList0001.add(attrValue00001);
        attrValue00001.setAttrvalueDefId("123");
        attrValue00001.setAttrValueName("10M");


        //SKU信息
        FastProductInfoRes infoRes1 = new FastProductInfoRes();
        resList.add(infoRes1);
        infoRes1.setSalePrice(998l);
        infoRes1.setSkuId("234");
        //SKU关键属性信息
        List<ProductSKUAttr> skuAttrList10 = new ArrayList<>();
        infoRes1.setSkuAttrList(skuAttrList10);
        //使用范围属性
        ProductSKUAttr skuAttr100 = new ProductSKUAttr();
        skuAttrList10.add(skuAttr100);
        skuAttr100.setAttrName("使用范围");
        skuAttr100.setAttrId(100005l);
        skuAttr100.setAttrType("1");
        //使用范围属性值
        List<ProductSKUAttrValue> attrValueList1000 = new ArrayList<>();
        skuAttr100.setAttrValueList(attrValueList1000);
        ProductSKUAttrValue attrValue10000 = new ProductSKUAttrValue();
        attrValueList1000.add(attrValue10000);
        attrValue10000.setAttrvalueDefId("1234");
        attrValue10000.setAttrValueName("省内");
        //流量面额属性
        ProductSKUAttr skuAttr101 = new ProductSKUAttr();
        skuAttrList10.add(skuAttr001);
        skuAttr101.setAttrName("流量面额");
        skuAttr101.setAttrId(100003l);
        skuAttr101.setAttrType("1");
        //流量面额属性值
        List<ProductSKUAttrValue> attrValueList1001 = new ArrayList<>();
        skuAttr101.setAttrValueList(attrValueList1001);
        ProductSKUAttrValue attrValue10001 = new ProductSKUAttrValue();
        attrValueList1001.add(attrValue10001);
        attrValue10001.setAttrvalueDefId("12334");
        attrValue10001.setAttrValueName("20M");

        return resList;
    }
}
