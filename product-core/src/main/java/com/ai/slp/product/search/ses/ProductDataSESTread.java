package com.ai.slp.product.search.ses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.search.bo.SKUInfo;

public class ProductDataSESTread extends Thread{
	private static final Logger LOG=LoggerFactory.getLogger(ProductDataSESTread.class);
    private List<SKUInfo> resultList;
    ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
    
    public ProductDataSESTread(List<SKUInfo> resultList){
        this.resultList=resultList;
    }
    @Override
    public void run() {
    	searchClient.bulkInsert(resultList);
    	searchClient.refresh();
    	LOG.info("【"+Thread.currentThread().getName()+"】");
    } 
}
