package com.ai.slp.product.dubbo;

import com.ai.opt.sdk.appserver.DubboServiceStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DubboStartTest {
    @Test
    public void testDubboStart(){
        DubboServiceStart.main(null);
    }
}
