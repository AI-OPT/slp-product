package com.ai.slp.product.service.business.interfaces.search;

import com.ai.paas.ipaas.search.vo.Results;
import com.ai.slp.product.search.dto.ProductSearchCriteria;

import java.util.Map;

public interface IProductSearch {
    Results<Map<String, Object>> search(ProductSearchCriteria criteria);

    Results<Map<String, Long>> searchCategory(ProductSearchCriteria criteria);
}
