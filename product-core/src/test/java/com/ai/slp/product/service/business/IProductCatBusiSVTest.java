package com.ai.slp.product.service.business;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by jackieliu on 16/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductCatBusiSVTest {

    @Test
    public void copyProTest(){
        ProductCatParam catParam = new ProductCatParam();
        ProductCat productCat = new ProductCat();
        catParam.setOperTime(DateUtils.currTimeStamp());
        catParam.setCatLevel((short) 12);
        BeanUtils.copyProperties(productCat,catParam);
        System.out.println(productCat.getCatLevel()+",operTime="+productCat.getOperTime());
    }
}
