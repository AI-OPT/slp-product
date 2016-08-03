package com.ai.slp.product.service.business;

import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductBusiSVTest {
    @Autowired
    IProductBusiSV productBusiSV;

    @Test
    public void queryFastInfoListTest(){
        FastProductReq request = new FastProductReq();
        request.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        request.setBasicOrgId("10");
        request.setProvCode(17);
        request.setUserType("10");
        request.setProductCatId("10000010010000");
        FastProductInfoRes infoRes = productBusiSV.queryFastInfoList(request);
        System.out.println("\rlocal size:"+infoRes.getLocalMap().size());
        System.out.println("nation size:"+infoRes.getNationMap().size());
    }

    @Test
    public void queryNoKeyAttrForEditTest(){
        ProdNoKeyAttr noKeyAttr = productBusiSV.queryNoKeyAttrForEdit(CommonTestConstants.COMMON_TENANT_ID,"3");
        System.out.println(noKeyAttr.toString());
    }
}
