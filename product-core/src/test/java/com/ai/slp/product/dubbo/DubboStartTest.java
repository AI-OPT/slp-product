package com.ai.slp.product.dubbo;

import com.ai.opt.sdk.appserver.DubboServiceStart;
import org.junit.Test;

public class DubboStartTest {
    @Test
    public void testDubboStart(){
        DubboServiceStart.main(null);
    }

    @Test
    public void test1(){
        int num = 9;
        System.out.println(num+":"+(-num));
    }
}
