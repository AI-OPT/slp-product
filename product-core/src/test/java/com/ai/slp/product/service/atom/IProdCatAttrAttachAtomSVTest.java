package com.ai.slp.product.service.atom;

import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jackieliu on 16/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdCatAttrAttachAtomSVTest {
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;

    @Test
    public void queryAttrOfByIdAndTypeTest(){
        List<ProdCatAttrAttch> attrAttchList =
                catAttrAttachAtomSV.queryAttrOfByIdAndType("SLP","123","1");
        System.out.println("============================");
        System.out.println(attrAttchList==null?"null":attrAttchList.size());
    }
}
