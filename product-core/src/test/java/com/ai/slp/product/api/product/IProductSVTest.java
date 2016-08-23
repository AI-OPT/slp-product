package com.ai.slp.product.api.product;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.constants.CommonTestConstants;
import com.google.gson.Gson;
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
        infoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        infoQuery.setProductId("1000000000000001");
        ProductInfo productInfo = productSV.queryProductById(infoQuery);
        System.out.println(productInfo.getState());
    }
    
    /**
     * 查询含有地域信息的商品集合
     * 
     */
    @Test
    public void searchProdTargetAreaTest(){
    	ProductEditQueryReq queryReq = new ProductEditQueryReq();
    	queryReq.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
    	queryReq.setSupplierId("-1");//设置商户ID
    	queryReq.setProdId("1000000000000022");//不完整ID
    	PageInfoResponse<TargetAreaForProd> searchProdTargetArea = productSV.searchProdTargetArea(queryReq);
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(searchProdTargetArea.getResult().get(0)));
    }

    @Test
    public void querySkuSetForProductTest(){
        ProductInfoQuery query = new ProductInfoQuery();
        query.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        query.setSupplierId("-1");
        query.setProductId("1");
        SkuSetForProduct skuSetForProduct = productSV.querySkuSetForProduct(query);
        System.out.println(skuSetForProduct.getProdId());
    }
    
}
