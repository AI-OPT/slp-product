package com.ai.slp.product.search.ses;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.slp.product.constants.SearchConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductDataSESTread extends Thread{
	private static final Logger LOG=LoggerFactory.getLogger(ProductDataSESTread.class);
    private List<String> resultList;
    ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
    
    public ProductDataSESTread(List<String> resultList){
        this.resultList=resultList;
    }
    @Override
    public void run() {
    	searchClient.bulkJsonInsert(resultList);
    	LOG.info("【"+Thread.currentThread().getName()+"】");
    } 
}
