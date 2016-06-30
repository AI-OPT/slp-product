package com.ai.slp.product.service.business.interfaces.exsearch;


import java.util.Map;

import com.ai.paas.ipaas.search.vo.Results;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;

public interface IExProductSearch {
    Results<Map<String, Object>> search(ExProductSearchCriteria criteria);

    Results<Map<String, Long>> searchCategory(ExProductSearchCriteria criteria);
}
