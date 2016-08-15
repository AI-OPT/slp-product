package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.productcat.param.AttrDefInfo;
import com.ai.slp.product.api.productcat.param.AttrDefParam;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author jiawen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IAttrAndValDefSVTest {
	@Autowired
	IAttrAndValDefSV attrAndValDefSV;
	@Test
	public void queryPageAttrsTest(){
		AttrDefParam attrDefParam = new AttrDefParam();
		attrDefParam.setTenantId("SLP");
		//attrDefParam.setAttrId();
		attrDefParam.setAttrName("");
		
		attrDefParam.setValueWay("1");
		PageInfoResponse<AttrDefInfo> queryPageAttrs = attrAndValDefSV.queryPageAttrs(attrDefParam);
		Gson gson = new Gson();
		System.out.println(gson.toJson(queryPageAttrs.getResult().get(0)));
		
	}

}
