package com.ai.slp.product.api.webfront;

import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jackieliu on 16/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductHomeSVTest {

    @Autowired
    IProductHomeSV productHomeSV;

    @Test
    public void queryFastProductTest(){
        FastProductReq fastProductReq = new FastProductReq();
        fastProductReq.setTenantId("SLP");
        fastProductReq.setUserType("10");
        fastProductReq.setProductCatId("10000010010000");
        fastProductReq.setBasicOrgId("10");
        fastProductReq.setProvCode(38);
        FastProductInfoRes infoRes = productHomeSV.queryFastProduct(fastProductReq);
        System.out.println(JSON.toJSONString(infoRes));
        System.out.println(infoRes.getLocalMap().size());
    }
    @Test
    public void queryHotTest(){
        ProductHomeRequest request = new ProductHomeRequest();
        request.setTenantId("SLP");
        request.setBasicOrgIdIs("10");
        request.setAreaCode("10");
        List<ProductHomeResponse> list = productHomeSV.queryHotProduct(request);
        System.out.println(JSON.toJSONString(list));
        System.out.println(list.size());
    }

}
