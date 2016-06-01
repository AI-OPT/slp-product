package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jackieliu on 16/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdSkuAtomSVTest {
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;

    @Test
    public void querySkuOfProd(){
        List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd("SLP","1000000000000001");
        System.out.println("\r"+skuList.size());
    }

    @Test
    public void querySkuByIdTest(){
        ProdSku prodSku = prodSkuAtomSV.querySkuById("SLP","1000000000002404");
        System.out.println("=====\r"+prodSku.getProdId());
    }
}
