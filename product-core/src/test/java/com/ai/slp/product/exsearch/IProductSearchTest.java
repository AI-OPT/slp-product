package com.ai.slp.product.exsearch;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.slp.product.api.exproduct.interfaces.IExSearchProductSV;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;
import com.ai.slp.product.service.business.impl.exsearch.ExProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.exsearch.IExProductSearch;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductSearchTest {
	@Autowired
	private IExSearchProductSV iSearchProductSV;
    public static void main(String[] args) {
        IExProductSearch exProductSearch = new ExProductSearchImpl();
        ExProductSearchCriteria exProductSearchCriteria =
                new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                .startSize(10).maxSearchSize(20).rechargeTypeIs("C").build();
        System.out.println(JSON.toJSONString(exProductSearch.search(exProductSearchCriteria)));
        System.out.println(exProductSearch.search(exProductSearchCriteria).getCount() == 2);
    }
    
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
       String param = JSON.toJSONString(request);
       String url  ="http://127.0.0.1:10882/slp-product/productsearch/search";
       String result = HttpClientUtil.sendPost(url, param);
      // QueryProductResponse response = iSearchProductSV.queryProductPage(request);
       //System.out.println("count="+JSON.toJSONString(response.getPageInfo().getCount()));
        System.out.println("result="+result);
        //System.out.println("response="+JSON.toJSONString(response));
    }
    
}
