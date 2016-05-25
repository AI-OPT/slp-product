package com.ai.slp.product.service.impl;

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
        request.setTenantId("432");
        request.setSkuId("31312");
        PageInfo<ProductData> pageInfo = new PageInfo<ProductData>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(10);
        request.setPageInfo(pageInfo);
        ProductQueryResponse response = iSearchProductSV.queryProductPage(request);
        System.out.println("result="+JSON.toJSONString(response.getPageInfo().getResult()));

    }
}
