package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

/**
 * 商品属性操作测试类
 * Created by jackieliu on 16/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdAttrDefAtomSVTest {
    @Autowired
    IProdAttrDefAtomSV prodAttrDefAtomSV;

    @Test
    public void installTest(){
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        prodAttrDef.setTenantId("SLP");
        prodAttrDef.setAttrId(123125l);
        prodAttrDef.setAttrName("测试属性");
        prodAttrDef.setFirstLetter("C");
        prodAttrDef.setOperId(1l);
        prodAttrDef.setOperTime(new Timestamp(System.currentTimeMillis()));
        prodAttrDef.setState("1");
        prodAttrDef.setValueWay("1");
        prodAttrDefAtomSV.installObj(prodAttrDef);
    }
}
