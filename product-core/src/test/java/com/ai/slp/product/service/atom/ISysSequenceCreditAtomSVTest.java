package com.ai.slp.product.service.atom;

import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ISysSequenceCreditAtomSVTest {
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;

    @Test
    public void genSeqTest(){
        System.out.println(sequenceCreditAtomSV.getSeqByName(ISysSequenceCreditAtomSV.PROD_ATTR_ID));
    }
}
