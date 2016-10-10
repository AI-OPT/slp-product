package com.ai.slp.product.api.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.productserver.interfaces.IProductNewServerSV;
import com.ai.slp.product.api.productserver.param.ProdInfoQuery;
import com.ai.slp.product.constants.CommonTestConstants;

/**
 * Created by jiawen on 16/10/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductServerSVTest2 {

	@Autowired
	IProductNewServerSV productServerSV;

    @Test
    public void queryProductSkuByProdCode(){
        ProdInfoQuery query = new ProdInfoQuery();
        query.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        query.setId("1");
        productServerSV.queryProductSkuByProdCode(query);
    }
}
