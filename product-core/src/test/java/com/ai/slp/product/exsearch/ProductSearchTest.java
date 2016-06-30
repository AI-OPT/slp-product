package com.ai.slp.product.exsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.exproduct.interfaces.IExSearchProductSV;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ProductSearchTest {
    @Autowired
    IExSearchProductSV iSearchProductSV;
    @Test
    public void serachProduct(){
        QueryProductRequest request = new QueryProductRequest();
       request.setTenantId("SLP");
       request.setUserId("000000000000001453");
       request.setUserType("11");
       request.setProductCatId("10000010020000");
       request.setProdRangeType("-1");
       request.setRechargeType("D");
       request.setPageNo(2);
       request.setPageSize(10);
       QueryProductResponse response = iSearchProductSV.queryProductPage(request);
       System.out.println("count="+JSON.toJSONString(response.getPageInfo().getCount()));
        System.out.println("result="+JSON.toJSONString(response.getPageInfo().getResult()));
    }
}
