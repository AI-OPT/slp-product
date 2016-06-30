package com.ai.slp.product.exsearch;



import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;
import com.ai.slp.product.service.business.impl.exsearch.ExProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.exsearch.IExProductSearch;
import com.alibaba.fastjson.JSON;


public class IProductSearchTest {
    public static void main(String[] args) {
        IExProductSearch exProductSearch = new ExProductSearchImpl();
        ExProductSearchCriteria exProductSearchCriteria =
                new ExProductSearchCriteria.ExProductSearchCriteriaBuilder()
                .startSize(10).maxSearchSize(20).rechargeTypeIs("C").build();
        System.out.println(JSON.toJSONString(exProductSearch.search(exProductSearchCriteria)));
        System.out.println(exProductSearch.search(exProductSearchCriteria).getCount() == 2);
    }
    
}
