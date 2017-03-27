package com.ai.slp.product.api.webfront;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.CommonTestConstants;
import com.alibaba.fastjson.JSON;

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
        skuRequest.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        skuRequest.setSkuId("0000000000000264");
        ProductSKUResponse skuResponse = productDetailSV.queryProducSKUById(skuRequest);
        System.out.println(JSON.toJSONString(skuResponse));
    }

    @Test
    public void querySkuConfBySkuAttr(){
        ProductSKURequest skuRequest = new ProductSKURequest();
        skuRequest.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
//        skuRequest.setSkuAttrs("100010:100082;100004:100015");
        skuRequest.setSkuId("0000000000000264");
        ProductSKUConfigResponse skuResponse = productDetailSV.queryProductSKUConfig(skuRequest);
        if (skuResponse!=null)
            System.out.println(JSON.toJSONString(skuResponse));
    }
}
