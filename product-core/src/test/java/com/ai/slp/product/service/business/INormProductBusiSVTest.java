package com.ai.slp.product.service.business;

import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.util.DateUtils;

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
}
