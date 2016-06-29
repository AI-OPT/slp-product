package com.ai.slp.product.api.product;

import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.constants.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/6/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductSVTest {
    @Autowired
    IProductSV productSV;

    @Test
    public void queryProductByIdTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId(CommonConstants.COMMON_TENANT_ID);
        infoQuery.setProductId("1000000000000001");
        ProductInfo productInfo = productSV.queryProductById(infoQuery);
        System.out.println(productInfo.getState());
    }
}
