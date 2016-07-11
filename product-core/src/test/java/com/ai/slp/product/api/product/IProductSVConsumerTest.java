package com.ai.slp.product.api.product;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.constants.CommonConstants;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/8.
 */
public class IProductSVConsumerTest {
    @Test
    public void queryProductByIdTest(){
        IProductSV productSV = DubboConsumerFactory.getService(IProductSV.class);
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId(CommonConstants.COMMON_TENANT_ID);
        infoQuery.setProductId("1000000000000001");
        ProductInfo productInfo = productSV.queryProductById(infoQuery);
        System.out.println(productInfo.getState());
    }
}
