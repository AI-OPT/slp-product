package com.ai.slp.product.service.business.impl.exsearch;


import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;
import com.ai.slp.product.service.business.interfaces.exsearch.IExProductSearch;

import java.util.Map;

/**
 * 商品搜索
 * Date: 2016年6月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
public class ExProductSearchImpl implements IExProductSearch {
    @Override
    public Result search(ExProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.search(criteria.getSearchfieldVos(), criteria.getStartSize(), criteria.getMaxSearchSize(),
                criteria.getSortFields(),Result.class);
    }

    @Override
    public Result<Map<String, Long>> searchCategory(ExProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.aggregate(criteria.getSearchfieldVos(), SearchFieldConfConstants.CATEGORY_ID);
    }


}
