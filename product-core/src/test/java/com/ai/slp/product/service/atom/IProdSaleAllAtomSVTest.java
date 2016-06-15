package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.attach.ProdSaleAllAttachMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdSaleAllAtomSVTest {
    @Autowired
    ProdSaleAllAttachMapper prodSaleAllAttachMapper;

    @Test
    public void attachTest(){
        System.out.println("============================\r\n"
                +prodSaleAllAttachMapper.selectCatAttr("SLP","123"));
    }
}
