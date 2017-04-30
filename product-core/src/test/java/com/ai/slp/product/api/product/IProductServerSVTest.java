package com.ai.slp.product.api.product;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.api.product.param.ProductSkuInfo;
import com.ai.slp.product.api.product.param.RouteGroupQuery;
import com.ai.slp.product.api.product.param.SkuInfoQuery;
import com.ai.slp.product.constants.CommonTestConstants;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductServerSVTest {
    @Autowired
    IProductServerSV productServerSV;

    @Test
    public void queryProductSkuById(){
        SkuInfoQuery skuInfoQuery = new SkuInfoQuery();
        skuInfoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
       // skuInfoQuery.setSkuId("0000000000000264");
        skuInfoQuery.setSkuId("0000000000002123");
        ProductSkuInfo skuById = productServerSV.queryProductSkuById(skuInfoQuery);
        
        Gson gson = new Gson();
		System.out.println(gson.toJson(skuById));
    }
    @Test
    public void queryProductSkuById4ShopCartTest(){
    	SkuInfoQuery skuInfoQuery = new SkuInfoQuery();
    	skuInfoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
    	skuInfoQuery.setSkuId("1000000000002405");
    	ProductSkuInfo skuById = productServerSV.queryProductSkuById4ShopCart(skuInfoQuery);
    	
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(skuById));
    }
    @Test
    public void queryProductAndRouteGroupTest(){
    	RouteGroupQuery query = new RouteGroupQuery();
    	query.setTenantId("changhong");
    	query.setSupplierId("-1");
    	query.setStandedProdId("221120");
    	PageInfoResponse<ProductRouteGroupInfo> response = productServerSV.queryProductAndRouteGroup(query);
    	
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(response.getResult()));
    }
}
