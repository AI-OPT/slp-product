package com.ai.slp.product.product;

import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class ProductEditUpTest {
	    @Autowired
		IProductManagerBusiSV productManagerBsuiSV;
	    @Autowired
	    IProductManagerSV productManagerSV;
	    @Test
	    public void queryProdEdit() {
	    	ProductEditQueryReq queryReq = new ProductEditQueryReq();
	    	queryReq.setTenantId("SLP");
	    	queryReq.setProductCatId("1");
			// 设置商品状态为新增和未编辑
			List<String> stateList = new ArrayList<>();
			// 设置状态，新增：0；未编辑1.
			stateList.add("0");
			stateList.add("1");
			queryReq.setStateList(stateList);
	    	productManagerBsuiSV.queryPageForEdit(queryReq);
	    }
	    @Test
	    public void prodUp(){
	    	ProductInfoQuery query = new ProductInfoQuery();
	    	query.setOperId(1l);
	    	query.setProductId("1000000000000004");
	    	query.setTenantId("SLP");
	    	productManagerSV.changeToInSale(query);
	    }
}