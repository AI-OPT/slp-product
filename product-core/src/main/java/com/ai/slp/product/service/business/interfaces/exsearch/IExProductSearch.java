package com.ai.slp.product.service.business.interfaces.exsearch;


import com.ai.paas.ipaas.search.vo.Result;
import com.ai.slp.product.exsearch.dto.ExProductSearchCriteria;

import java.util.Map;
/**
 * 搜索
 */
public interface IExProductSearch {
	//商品搜索的接口
    Result<Map<String, Object>> search(ExProductSearchCriteria criteria);
    //商品相关收索
    Result<Map<String, Long>> searchCategory(ExProductSearchCriteria criteria);
}
