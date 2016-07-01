package com.ai.slp.product.exsearch;

import org.junit.Test;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.exproduct.interfaces.IExSearchProductSV;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;
import com.alibaba.fastjson.JSON;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:context/core-context.xml")
public class ProductSearchTest {
    /*@Autowired
	protected ApplicationContext ctx;

	public <T> T getBean(Class<T> type) {
		return ctx.getBean(type);
	}

	public Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}*/
	
/*	@Autowired
	private IExSearchProductSV iSearchProductSV;*/
	
    @Test
    public void serachProduct(){
        QueryProductRequest request = new QueryProductRequest();
       request.setTenantId("SLP");
       request.setUserId("000000000000000292");
       request.setUserType("12");
       request.setProductCatId("10000010010000");
       request.setProdRangeType("-1");
       request.setRechargeType("D");
       request.setPageNo(1);
       request.setPageSize(10);
       IExSearchProductSV iSearchProductSV=DubboConsumerFactory.getService(IExSearchProductSV.class);
       QueryProductResponse response = iSearchProductSV.queryProductPage(request);
       System.out.println("count="+JSON.toJSONString(response.getPageInfo().getCount()));
        System.out.println("result="+JSON.toJSONString(response.getPageInfo().getResult()));
    }
}
