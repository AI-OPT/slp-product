package com.ai.slp.product.search.api.impl;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.slp.product.search.api.IProductSearch;
import com.ai.slp.product.search.constants.Constants;
import com.ai.slp.product.search.dto.ProductSearchCriteria;

import java.util.Map;

/**
 * Created by xin on 16-5-25.
 */
public class ProductSearchImpl implements IProductSearch {
    @Override
    public Results<Map<String, Object>> search(ProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory.getSearchClient(Constants.SearchNameSpace);
        return searchClient.search(criteria.getSearchfieldVos(), criteria.getStartSize(), criteria.getMaxSearchSize(),
                criteria.getOrderByField(), criteria.getSortType().getValue());
    }

    @Override
    public Results<Map<String, Long>> searchCategory(ProductSearchCriteria criteria) {
        return null;
    }


}
