package com.ai.slp.product.api.webfront;

import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        System.out.print(infoRes.getLocalMap().size());
    }
}
