package com.ai.slp.product.service.business.interfaces.exsearch;


import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;

import java.util.Map;
/**
 * 搜索
 */
public interface IExProductSearch {
    Result<Map<String, Object>> search(ExProductSearchCriteria criteria);

    Result<Map<String, Long>> searchCategory(ExProductSearchCriteria criteria);
}
