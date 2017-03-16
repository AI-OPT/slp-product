package com.ai.slp.product.service.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.alibaba.fastjson.JSON;

/**
 * Created by jackieliu on 16/6/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdSkuBusiSVTest {

    @Autowired
    IProdSkuBusiSV prodSkuBusiSV;

    @Test
    public void querySkuDetailTest(){
        ProductSKUResponse skuResponse = prodSkuBusiSV.querySkuDetail("SLP","4",null);
        System.out.println(skuResponse.toString());
    }

    @Test
    public void querySkuAttrTest(){
        List<AttrInfo> skuConfigResponse = prodSkuBusiSV.querySkuAttr("SLP","1000000000002401",null);
        System.out.println(JSON.toJSONString(skuConfigResponse));
    }
}
