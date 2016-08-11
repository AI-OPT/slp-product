package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatPageQuery;
import com.ai.slp.product.constants.CommonTestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductCatSVTest {
    @Autowired
    IProductCatSV productCatSV;

    @Test
    public void queryPageProductCat(){
        ProductCatPageQuery pageQuery = new ProductCatPageQuery();
        pageQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        PageInfoResponse<ProductCatInfo> catInfoRes = productCatSV.queryPageProductCat(pageQuery);
        System.out.println(catInfoRes.getResponseHeader().getIsSuccess());
    }
}
