package com.ai.slp.product.service.business;

import com.ai.slp.product.service.business.interfaces.search.ISKUIndexManage;
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
public class ISKUIndexManageTest {
    @Autowired
    ISKUIndexManage iskuIndexManage;

    @Test
    public void deleteSKUIndexByProductId(){
        //搜索引擎中删除上信息
        iskuIndexManage.deleteSKUIndexByProductId("1000000000000078");
    }
}
