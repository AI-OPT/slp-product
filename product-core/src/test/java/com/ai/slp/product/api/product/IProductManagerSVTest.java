package com.ai.slp.product.api.product;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
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
public class IProductManagerSVTest {
    @Autowired
    IProductManagerSV productManagerSV;

    @Test
    public void queryOtherSetOfProductTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000004");
        OtherSetOfProduct otherSet = productManagerSV.queryOtherSetOfProduct(infoQuery);
        System.out.println(otherSet.toString());
    }

    @Test
    public void queryNoKeyAttrOfProd(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000004");
        ProdNoKeyAttr noKeyAttr = productManagerSV.queryNoKeyAttrOfProd(infoQuery);
        System.out.println(noKeyAttr.getAttrInfoForProdList().size());
    }

    /**
     * 上架测试
     */
    @Test
    public void changeToInSaleTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000093");
        BaseResponse response = productManagerSV.changeToInSale(infoQuery);
        ResponseHeader header = response.getResponseHeader();
        System.out.println(header!=null?header.isSuccess():false);
    }
    
    /**
     * 手动下架测试
     */
    @Test
    public void prodInStoreTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000093");
        BaseResponse response = productManagerSV.changeToInStore(infoQuery);
        ResponseHeader header = response.getResponseHeader();
        System.out.println(header!=null?header.isSuccess():false);
    }
    
    /**
     * 查询待编辑
     */
    @Test
    public void queryProductEditTest(){
    	ProductEditQueryReq req = new ProductEditQueryReq();
    	req.setTenantId("changhong");
    	req.setSupplierId("-1");
    	PageInfoResponse<ProductEditUp> queryProductEdit = productManagerSV.queryProductEdit(req);
    	
        Gson gson = new Gson();
		System.out.println(gson.toJson(queryProductEdit));
    	
    }
}
