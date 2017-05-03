package com.ai.slp.product.service.business.interfaces.search;

import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.Sort;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.comment.CommentInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;

import java.util.List;
import java.util.Map;
/**
 * 商品搜索
 */
public interface IProductSearch {
	//商品相关查询
    Result<Map<String, Object>> search(ProductSearchCriteria criteria);
    //商品搜索返回map集合
    Result<Map<String, Long>> searchCategory(ProductSearchCriteria criteria);
    
    //商品相关查询
    Result<SKUInfo> searchByCriteria(List<SearchCriteria> searchCriterias, int from, int offset,
			 List<Sort> sorts);
    //商品搜索
    Result<SKUInfo> search(List<SearchCriteria> searchCriterias, int from, int offset,
			 List<Sort> sorts);
    
    //商品评价查询
    Result<CommentInfo> searchComment(List<SearchCriteria> searchCriterias, int from, int offset,
			 List<Sort> sorts);
}
