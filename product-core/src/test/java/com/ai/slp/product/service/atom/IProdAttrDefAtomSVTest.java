package com.ai.slp.product.service.atom;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import org.junit.Assert;
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
        String tenantId = "SLP";
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        prodAttrDef.setTenantId(tenantId);
        prodAttrDef.setAttrName("测试属性");
        prodAttrDef.setFirstLetter("C");
        prodAttrDef.setOperId(1l);
        prodAttrDef.setOperTime(new Timestamp(System.currentTimeMillis()));
        prodAttrDef.setState(CommonConstants.STATE_ACTIVE);
        prodAttrDef.setValueWay("1");
//        int ret = prodAttrDefAtomSV.installObj(prodAttrDef);
//        Assert.assertEquals(1,ret);
        Long attrDefId = prodAttrDef.getAttrId();
        attrDefId = 100001l;
//        //查询验证是否存在
        ProdAttrDef selRet = prodAttrDefAtomSV.selectById(tenantId,attrDefId);
        Assert.assertNotNull(selRet);
        Assert.assertEquals(attrDefId.longValue(),selRet.getAttrId().longValue());
//        //删除
//        prodAttrDefAtomSV.deleteById(tenantId,attrDefId,1l);
//
//        //查询验证是否存在
//        selRet = prodAttrDefAtomSV.selectById(tenantId,attrDefId);
//        Assert.assertNull(selRet);
    }

}
