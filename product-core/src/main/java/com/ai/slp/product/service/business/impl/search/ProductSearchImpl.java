package com.ai.slp.product.service.business.impl.search;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.paas.ipaas.search.vo.Results;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;

import java.util.Map;

/**
 * 商品搜索
 * Created by xin on 16-5-25.
 */
public class ProductSearchImpl implements IProductSearch {
    @Override
    public Results<Map<String, Object>> search(ProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.search(criteria.getSearchfieldVos(), criteria.getStartSize(), criteria.getMaxSearchSize(),
                criteria.getSortFields());
    }

    @Override
    public Results<Map<String, Long>> searchCategory(ProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.simpleAggregation(criteria.getSearchfieldVos(), SearchFieldConfConstants.CATEGORY_ID);
    }


}
