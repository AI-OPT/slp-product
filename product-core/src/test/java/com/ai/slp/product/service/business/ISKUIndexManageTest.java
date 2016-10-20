package com.ai.slp.product.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;

/**
 * Created by jackieliu on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ISKUIndexManageTest {
    @Autowired
    ISKUIndexBusiSV iskuIndexManage;

    @Test
    public void deleteSKUIndexByProductId(){
        //搜索引擎中删除上信息
        iskuIndexManage.deleteSKUIndexByProductId("1000000000000007");
    }

    @Test
    public void updateSKUIndex(){
        //搜索引擎中添加信息
        iskuIndexManage.updateSKUIndex("0000000000000183", DateUtil.getCurrentTimeMillis());
    }
}
