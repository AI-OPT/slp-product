package com.ai.slp.product.service.atom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;

/**
 * Created by jackieliu on 16/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProdCommentAtomSVTest {
    @Autowired
    IProdCommentAtomSV prodCommentAtomSV;

    @Test
    public void countBySkuId(){
        System.out.println("====="+prodCommentAtomSV.countBySkuId("0000000000000153",false));
    }
}
