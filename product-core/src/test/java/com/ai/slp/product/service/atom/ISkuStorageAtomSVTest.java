package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ISkuStorageAtomSVTest {
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;

    @Test
    public void queryByIdTest(){
        SkuStorage skuStorage = skuStorageAtomSV.queryById("100000100008",true);
        System.out.println(skuStorage.getUsableNum());
    }
}
