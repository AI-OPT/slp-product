package com.ai.slp.product.api.storageserver;

import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IStorageNumSVTest {
    @Autowired
    IStorageNumSV storageNumSV;


}
