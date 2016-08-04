package com.ai.slp.product.service.atom;

import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jackieliu on 16/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdSkuAttrAtomSVTest {
    @Autowired
    IProdSkuAttrAtomSV skuAttrAtomSV;

    @Test
    public void queryAttrValIdByProdIdAndAttrIdTest(){
        List<String> attrValIds = skuAttrAtomSV.queryAttrValIdByProdIdAndAttrId(
                CommonTestConstants.COMMON_TENANT_ID,"1",100010l);
        System.out.println("\r");
        for (String attrValId:attrValIds){
            System.out.print(attrValId+",");
        }
    }
}
