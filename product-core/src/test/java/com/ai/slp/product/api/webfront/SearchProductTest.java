package com.ai.slp.product.api.webfront;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.webfront.interfaces.ISearchProductSV;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class SearchProductTest {
    @Autowired
    ISearchProductSV iSearchProductSV;
    @Test
    public void serachProduct(){
        ProductQueryRequest request = new ProductQueryRequest();
        PageInfo<ProductData> pageInfo = new PageInfo<ProductData>();
        pageInfo.setPageNo(1);
       pageInfo.setPageSize(10);
       request.setTenantId("SLP");
        request.setAreaCode("11");
        request.setProductCatId("10000010020000");
        //request.setUserType("11");
        request.setBasicOrgIdIs("10");
       //request.setAttrDefId("100004");
        request.setPageInfo(pageInfo);
        //request.setPriceOrderFlag("12");
        //request.setSaleNumOrderFlag("lll");
       // request.setDistributionArea("100013");
        ProductQueryResponse response = iSearchProductSV.queryProductPage(request);
        System.out.println("result="+JSON.toJSONString(response.getPageInfo().getResult()));
    }
    @Test
    public void serachHotProduct(){
        ProductQueryRequest request = new ProductQueryRequest();
        request.setProductCatId("10000010010000");
        request.setAreaCode("11");
        request.setTenantId("SLP");
        request.setUserType("12");
        List<ProductData> response = iSearchProductSV.queryHotSellProduct(request);
        System.out.println("result="+JSON.toJSONString(response));
    }
    @Test
    public void serach(){
        ProductQueryRequest request = new ProductQueryRequest();
        PageInfo<ProductData> pageInfo = new PageInfo<ProductData>();
        pageInfo.setPageNo(2);
        pageInfo.setPageSize(10);
        request.setAreaCode("11");
        request.setTenantId("SLP");
        request.setSkuName("电信");
        request.setPageInfo(pageInfo);
        //request.setPriceOrderFlag("DESC");
        ProductQueryResponse response = iSearchProductSV.searchProduct(request);
        System.out.println("result="+JSON.toJSONString(response.getPageInfo().getResult()));
    }
}
