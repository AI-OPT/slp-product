package com.ai.slp.product.api.webfront;

import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductDetailSVTest {
    @Autowired
    IProductDetailSV productDetailSV;

    @Test
    public void queryProducSKUById(){
        ProductSKURequest skuRequest = new ProductSKURequest();
        skuRequest.setTenantId("SLP");
        skuRequest.setSkuId("1");
        ProductSKUResponse skuResponse = productDetailSV.queryProducSKUById(skuRequest);
        if (skuResponse!=null)
            System.out.println(skuResponse.getProdName());
    }
}
