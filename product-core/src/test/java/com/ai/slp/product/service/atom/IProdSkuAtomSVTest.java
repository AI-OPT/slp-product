package com.ai.slp.product.service.atom;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.dao.mapper.attach.ProdFastSkuAttach;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;

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

    @Test
    public void queryFastProdTest(){
        List<ProdFastSkuAttach> attachList = prodSkuAtomSV.queryNationFastProd(
                CommonTestConstants.COMMON_TENANT_ID,"10000010010000","10","10",null,100002l
        );

        System.out.println("\r======Nation");
        for (ProdFastSkuAttach skuAttach:attachList){
            System.out.println(skuAttach.toString());
        }
        List<ProdFastSkuAttach> localList = prodSkuAtomSV.queryLocalFastProd(
                CommonTestConstants.COMMON_TENANT_ID,"10000010010000","10","10",null,100002l,11
        );
        System.out.println("======Local");
        for (ProdFastSkuAttach skuAttach:localList){
            System.out.println(skuAttach.toString());
        }
    }

    @Test
    public void queryInSaleForSearchTest(){
        List<ProdSkuInfoSes> skuInfoSesList = prodSkuAtomSV.queryInSaleForSearch(0,1000);
        System.out.print(skuInfoSesList.size());
    }

}
