package com.ai.slp.product.search;

import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.api.impl.ProductSearchImpl;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;

/**
 * Created by xin on 16-5-27.
 */
public class IProductSearchTest {
    public static void main(String[] args) {
        IProductSearch productSearch = new ProductSearchImpl();
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder("120000",
                        new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL, "1"))
                .attrNameLike("天津").sellPointLike("运营").build();
        System.out.println(productSearch.search(productSearchCriteria).getCount() == 2);
    }
}
