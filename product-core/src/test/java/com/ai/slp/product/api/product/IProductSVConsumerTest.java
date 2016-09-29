package com.ai.slp.product.api.product;

import org.junit.Test;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.api.product.param.StoGroupInfoQuery;
import com.ai.slp.product.constants.CommonTestConstants;

/**
 * Created by jackieliu on 16/7/8.
 */
public class IProductSVConsumerTest {
    @Test
    public void queryProductByIdTest(){
        IProductSV productSV = DubboConsumerFactory.getService(IProductSV.class);
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        infoQuery.setSupplierId("-1");
        infoQuery.setProductId("1000000000000001");
        ProductInfo productInfo = productSV.queryProductById(infoQuery);
        System.out.println(productInfo.getState());
    }

    @Test
    public void querySkuSetForGroupTest(){
        IProductSV productSV = DubboConsumerFactory.getService(IProductSV.class);
        StoGroupInfoQuery infoQuery = new StoGroupInfoQuery();
        infoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        infoQuery.setGroupId("1");
        infoQuery.setSupplierId("-1");
        SkuSetForProduct skuSetForProduct = productSV.querySkuSetForGroup(infoQuery);
        System.out.println(skuSetForProduct.getProdId());
    }
}
