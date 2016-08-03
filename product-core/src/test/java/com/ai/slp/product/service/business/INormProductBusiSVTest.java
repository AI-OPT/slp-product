package com.ai.slp.product.service.business;

import com.ai.slp.product.api.normproduct.param.AttrMap;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class INormProductBusiSVTest {
    @Autowired
    INormProductBusiSV normProductBusiSV;

    @Test
    public void updateMarketPrice(){
        MarketPriceUpdate marketPrice4Update = new MarketPriceUpdate();
        marketPrice4Update.setMarketPrice(123123124L);
        marketPrice4Update.setProductId("2");
        marketPrice4Update.setOperId(2L);
        marketPrice4Update.setTenantId("2");
        int count = normProductBusiSV.updateMarketPrice(marketPrice4Update);
        System.out.println(count);
        

    }

    @Test
    public void queryAttrOfProductTest(){
        //标准品关键属性
        AttrMap attrMap = normProductBusiSV.queryAttrOfProduct(
                CommonTestConstants.COMMON_TENANT_ID,"2",ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY);
        System.out.println(attrMap.toString());
    }
}
