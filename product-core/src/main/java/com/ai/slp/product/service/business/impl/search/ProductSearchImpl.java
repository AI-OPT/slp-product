package com.ai.slp.product.service.business.impl.search;

import java.util.List;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.Sort;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.comment.CommentInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;

/**
 * 商品搜索
 * Created by xin on 16-5-25.
 */
public class ProductSearchImpl implements IProductSearch {
    @Override
    public Result search(ProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory
                .getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.search(criteria.getSearchfieldVos(), criteria.getStartSize(),
                criteria.getMaxSearchSize(), criteria.getSortFields(), SKUInfo.class);
    }
    
    @Override
	public Result search(List<SearchCriteria> searchCriterias, int from, int offset,
			 List<Sort> sorts) {
		ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
		return searchClient.search(searchCriterias, from, offset, sorts, SKUInfo.class);
	}


    @Override
    public Result searchCategory(ProductSearchCriteria criteria) {
        ISearchClient searchClient = SESClientFactory
                .getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.aggregate(criteria.getSearchfieldVos(),
                SearchFieldConfConstants.CATEGORY_ID);
    }

	@Override
	public Result<CommentInfo> searchComment(List<SearchCriteria> criteria, int from, int offset,
			List<Sort> sorts) {
		ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace_COMMENT);
		return searchClient.search(criteria, from, offset, sorts, CommentInfo.class);
	}

	@Override
	public Result<SKUInfo> searchByCriteria(List<SearchCriteria> searchCriterias, int from, int offset,
			List<Sort> sorts) {
		ISearchClient searchClient = SESClientFactory
                .getSearchClient(SearchConstants.SearchNameSpace);
        return searchClient.search(searchCriterias, from,offset, sorts, SKUInfo.class);
	}
}
