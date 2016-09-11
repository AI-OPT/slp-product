package com.ai.slp.product.search;

import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by xin on 16-5-27.
 */
public class IProductSearchTest {
    public static void main(String[] args) {
        IProductSearch productSearch = new ProductSearchImpl();
        ProductSearchCriteria productSearchCriteria =
                new ProductSearchCriteria.ProductSearchCriteriaBuilder("13","Y",
                        new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE,""))
                .startSize(0).maxSearchSize(100).build();
        Result<Map<String, Object>> result=productSearch.search(productSearchCriteria);
        System.out.println(JSON.toJSONString(result));
        System.out.println(productSearch.search(productSearchCriteria).getCount());
    }
    
}
