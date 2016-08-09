package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Test
    public void queryByPriorityNum(){
        List<SkuStorage> skuStoList = skuStorageAtomSV.queryPriorityOfGroup("1",null,new Short("1"));
        Gson gson = new Gson();
        System.out.println("\r");
        for (SkuStorage storage:skuStoList){
            System.out.println(gson.toJson(storage));
        }
    }

    @Test
    public void countOfNoPrice(){
        System.out.println("\r===="+skuStorageAtomSV.countOfNoPrice("SLP","1"));
    }
}
