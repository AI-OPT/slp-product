package com.ai.slp.product.cache;

import com.ai.slp.product.cache.prodCat.ProdCatCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
