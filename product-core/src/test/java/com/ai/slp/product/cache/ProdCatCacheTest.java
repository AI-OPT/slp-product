package com.ai.slp.product.cache;

import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.cache.prodCat.ProdCatCache;
import com.ai.slp.product.util.IPaasCatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by jackieliu on 16/7/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ProdCatCacheTest {
    @Autowired
    ProdCatCache catCache;

    @Test
    public void writeTest(){
        try {
            catCache.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void levelTest(){
        Short level = new Short("2");
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        String parentKey = IPaasCatUtils.genMcsCatLevelKey("SLP");
        Set<String> catLevel = cacheClient.zrangeByScore(parentKey,level,level);
        for (String catId:catLevel){
            System.out.print(catId+",");
        }
        System.out.println();
    }
}
