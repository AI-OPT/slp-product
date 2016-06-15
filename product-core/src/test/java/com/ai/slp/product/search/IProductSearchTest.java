package com.ai.slp.product.search;

import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.api.impl.ProductSearchImpl;
import com.ai.slp.product.search.constants.SearchMetaFieldConfig;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.alibaba.fastjson.JSON;

/**
 * Created by xin on 16-5-27.
 */
public class IProductSearchTest {
    public static void main(String[] args) {
        IProductSearch productSearch = new ProductSearchImpl();
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder("11",
                        new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL,""))
                .attrValueDefID("100050").categoryIdIs("10000010020000").basicOrgIdIs("10").build();
        System.out.println(JSON.toJSONString(productSearch.search(productSearchCriteria)));
        System.out.println(productSearch.search(productSearchCriteria).getCount() == 2);
    }
    
}
