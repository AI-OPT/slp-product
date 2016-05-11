package com.ai.slp.product.service.business;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatParam;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        BeanUtils.copyProperties(productCat,catParam);
        System.out.println(productCat.getCatLevel()+",operTime="+productCat.getOperTime());
    }

    @Test
    public void copyProTest1(){
        ProductCatInfo productCatInfo = new ProductCatInfo();
        ProductCat productCat = new ProductCat();
        productCat.setOperTime(DateUtils.currTimeStamp());
        productCat.setCatLevel((short) 12);
        productCat.setSerialNumber((short)1);
        BeanUtils.copyProperties(productCatInfo,productCat);
        System.out.println(productCatInfo.getCatLevel());
    }
}
