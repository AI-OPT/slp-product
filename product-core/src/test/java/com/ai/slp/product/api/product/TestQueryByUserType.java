package com.ai.slp.product.api.product;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.service.atom.interfaces.product.IProdAudiencesAtomSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class TestQueryByUserType {
	@Autowired
	IProdAudiencesAtomSV prodAudiencesAtomSV;
	
	@Test
	public void queryProductByIdTest(){
		List<ProdAudiences> list = prodAudiencesAtomSV.queryByUserType("SLP", "1000000000000002", "10", null, false);
		System.out.println(JSonUtil.toJSon(list));
	}
}
