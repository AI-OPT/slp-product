package com.ai.slp.product.search;

import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.alibaba.fastjson.JSON;

/**
 * Created by xin on 16-5-27.
 */
public class IProductSearchTest {
    public static void main(String[] args) {
        IProductSearch productSearch = new ProductSearchImpl();
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder("11","Y",
                        new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,""))
                .tenantID("SLP").skuNameMust("电信").build();
        System.out.println(JSON.toJSONString(productSearch.search(productSearchCriteria)));
        System.out.println(productSearch.search(productSearchCriteria).getCount() == 2);
    }
    
}
