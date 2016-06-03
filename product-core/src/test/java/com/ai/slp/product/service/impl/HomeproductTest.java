package com.ai.slp.product.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class HomeproductTest {
    @Autowired
    IProductHomeSV iProductHomeSV;
    @Test
    public void serachProduct(){
        ProductHomeRequest request = new ProductHomeRequest();
        request.setTenantId("SLP");
        request.setAreaCode("81");
        request.setProductCatId("10000010010000");
        request.setBasicOrgIdIs("12");
        List<ProductHomeResponse>  response = iProductHomeSV.queryHomeDataProduct(request);
        System.out.println("result="+JSON.toJSONString(response));

    }
    @Test
    public void serachHotProduct(){
        ProductHomeRequest request = new ProductHomeRequest();
        request.setTenantId("SLP");
        request.setAreaCode("120000");
        List<ProductHomeResponse>  response = iProductHomeSV.queryHotProduct(request);
        System.out.println("result="+JSON.toJSONString(response));

    }
}
