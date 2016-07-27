package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseListResponse;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatUniqueReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductCatCacheSVTest {
    @Autowired
    IProductCatCacheSV catCacheSV;

    @Test
    public void queryLink(){
        ProductCatUniqueReq uniqueReq = new ProductCatUniqueReq();
        uniqueReq.setTenantId("SLP");
        uniqueReq.setProductCatId("10000010020000");
        BaseListResponse<ProductCatInfo> catInfoList =  catCacheSV.queryLinkOfCatById(uniqueReq);
        for (ProductCatInfo catInfo:catInfoList.getResult()){
            System.out.print(catInfo.getProductCatName()+">");
        }
        System.out.println();
    }
}
