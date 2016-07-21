package com.ai.slp.product.cache;

import com.ai.slp.product.cache.storage.StorageCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class StorageCacheTest {
    @Autowired
    StorageCache storageCache;

    @Test
    public void flushTest() throws Exception {
        storageCache.write();
        //休眠一分钟
        Thread.sleep(60000);
    }
}
