package com.ai.slp.product.service.impl;

import com.ai.slp.product.service.business.interfaces.search.ISKUIndexManage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")

public class UpdateSearchTest {
    @Autowired
    ISKUIndexManage iSKUIndexManage;
    @Test
    public void updateProduct(){
        boolean a = iSKUIndexManage.updateSKUIndex("1000000000002481");
        System.out.println(a);
    }


}
