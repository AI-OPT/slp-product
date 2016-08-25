package com.ai.slp.product.api.normproduct;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.NormProdAndKeyAttrRes;
import com.ai.slp.product.api.normproduct.param.NormProdRequest;
import com.ai.slp.product.api.normproduct.param.NormProdResponse;
import com.ai.slp.product.constants.CommonTestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiawen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
//public class IProductSVTest {
public class INormProductSVTest {
    @Autowired
    INormProductSV normProductSV;

    @Test
    public void queryNormProductTest(){
        NormProdRequest infoQuery = new NormProdRequest();
        infoQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        //infoQuery.setProductId("100000000100");
        PageInfoResponse<NormProdResponse> productInfo = normProductSV.queryNormProduct(infoQuery);
        System.out.println(productInfo.toString());
    }

    @Test
    public void queryNormProductAndKeyAttr(){
        NormProdRequest prodRequest = new NormProdRequest();
        prodRequest.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        prodRequest.setSupplierId("-1");
        prodRequest.setProductCatId("1");
        PageInfoResponse<NormProdAndKeyAttrRes> resPage = normProductSV.queryNormProductAndKeyAttr(prodRequest);
        System.out.println(resPage.getCount());
    }
}
