package com.ai.slp.product.service.business.interfaces.search;

import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.search.dto.ProductSearchCriteria;

import java.util.Map;
/**
 * 商品搜索
 */
public interface IProductSearch {
	//商品相关查询
    Result<Map<String, Object>> search(ProductSearchCriteria criteria);
    //商品搜索返回map集合
    Result<Map<String, Long>> searchCategory(ProductSearchCriteria criteria);
}
