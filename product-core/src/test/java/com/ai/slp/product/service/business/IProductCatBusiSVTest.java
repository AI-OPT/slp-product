package com.ai.slp.product.service.business;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.productcat.param.ProdCatAttrVal;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatParam;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jackieliu on 16/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductCatBusiSVTest {
    @Autowired
    IProductCatBusiSV productCatBusiSV;
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

    @Test
    public void queryLinkOfCatById(){
        List<ProductCatInfo> catInfoList = productCatBusiSV.queryLinkOfCatById(CommonTestConstants.COMMON_TENANT_ID,"10000010010000");
        for (ProductCatInfo catInfo:catInfoList){
            System.out.print(catInfo.getProductCatName()+"-->");
        }
    }

    @Test
    public void deleteAttrVal(){
        ProdCatAttrVal attrVal = new ProdCatAttrVal();
        attrVal.setTenantId("SLP");
        attrVal.setOperId(1l);
        attrVal.setId("1000000047");
        attrVal.setObjType("2");
        attrVal.setProductCatId("10000010010000");
        productCatBusiSV.deleteAttrVal(attrVal);
    }
}
