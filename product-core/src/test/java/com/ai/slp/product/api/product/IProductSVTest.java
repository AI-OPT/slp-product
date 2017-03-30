package com.ai.slp.product.api.product;

import com.ai.opt.base.vo.BaseListResponse;
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
        infoQuery.setProductId("0000000000000623");
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
        query.setTenantId("changhong");
        query.setSupplierId("-1");
        query.setProductId("1");
        SkuSetForProduct skuSetForProduct = productSV.querySkuSetForProduct(query);
        System.out.println(skuSetForProduct.getProdId());
    }

    @Test
    public void querySkuSetForStorageTest(){
        StorageInfoQuery query = new StorageInfoQuery();
        query.setTenantId("changhong");
        query.setSupplierId("-1");
        query.setStorageId("1");
        SkuSetForProduct skuSetForProduct = productSV.querySkuSetForStorage(query);
        System.out.println(skuSetForProduct.getProdId());
    }

    @Test
    public void querySkuSetForGroup(){
        StoGroupInfoQuery query = new StoGroupInfoQuery();
        query.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        query.setGroupId("0000000000120");
        query.setSupplierId("-1");
        SkuSetForProduct skuSetForProduct = productSV.querySkuSetForGroup(query);
        System.out.println(skuSetForProduct.getProdId());
    }

    @Test
    public void queryAreaInfosOfProduct(){
        ProductInfoQuery query = new ProductInfoQuery();
        query.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        query.setSupplierId("-1");
        query.setProductId("1000000000000120");
        BaseListResponse<ProdTargetAreaInfo> listResponse = productSV.queryAreaInfosOfProduct(query);
        System.out.println("======\r\n----"+listResponse.getResponseHeader().isSuccess());
    }
    
    @Test
    public void queryNoKeyAttrInfoTest(){
    	ProductInfoQuery query = new ProductInfoQuery();
    	query.setTenantId("changhong");
    	query.setProductId("0000000000000264");
    	query.setSupplierId("-1");
    	query.setOperId(1l);
    	ProdAttrMap attrMap = productSV.queryNoKeyAttrInfo(query);
    	System.out.println(attrMap.toString());
    }
}
