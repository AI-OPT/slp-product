package com.ai.slp.product.service.business;

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

    }
}
