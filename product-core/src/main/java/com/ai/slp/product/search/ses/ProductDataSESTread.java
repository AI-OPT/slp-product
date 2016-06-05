package com.ai.slp.product.search.ses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.slp.product.search.constants.Constants;

public class ProductDataSESTread extends Thread{
	private static final Logger LOG=LoggerFactory.getLogger(ProductDataSESTread.class);
    private List<String> resultList;
    ISearchClient searchClient = SESClientFactory.getSearchClient(Constants.SearchNameSpace);
    
    public ProductDataSESTread(List<String> resultList){
        this.resultList=resultList;
    }
    @Override
    public void run() {
    	searchClient.bulkInsertData(resultList);
    	LOG.info("【"+Thread.currentThread().getName()+"】");
    } 
}
