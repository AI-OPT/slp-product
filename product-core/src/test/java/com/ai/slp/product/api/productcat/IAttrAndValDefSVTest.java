package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.util.DataUtils;
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
		//Long longValue = DataUtils.getLongVal("18").longValue();
		//attrDefParam.setAttrId(longValue);
		
		attrDefParam.setValueWay("1");
		PageInfoResponse<AttrDefInfo> queryPageAttrs = attrAndValDefSV.queryPageAttrs(attrDefParam);
		Gson gson = new Gson();
		System.out.println(gson.toJson(queryPageAttrs.getResult().get(0)));

	}

	@Test
	public void queryAllAttrAndVal(){
		BaseInfo baseInfo = new BaseInfo();
		baseInfo.setTenantId("SLP");
		BaseListResponse<AttrDef> defList = attrAndValDefSV.queryAllAttrAndVal(baseInfo);
		System.out.println(defList.getResult().size());
	}

	@Test
	public void queryPageAttrValuesTest(){
		AttrValPageQuery attrVal = new AttrValPageQuery();
		attrVal.setTenantId("SLP");
		attrVal.setAttrId(9l);
		attrVal.setPageSize(10);
		attrVal.setPageNo(1);
		//attrVal.setAttrValueName("");
		
		PageInfoResponse<AttrValInfo> queryPageAttrs = attrAndValDefSV.queryPageAttrvalue(attrVal);
		Gson gson = new Gson();
		System.out.println(queryPageAttrs.getResult().size());
		System.out.println(gson.toJson(queryPageAttrs.getResult().get(0)));

	}

}
