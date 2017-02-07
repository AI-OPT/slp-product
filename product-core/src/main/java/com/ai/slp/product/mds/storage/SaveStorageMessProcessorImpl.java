package com.ai.slp.product.mds.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.alibaba.fastjson.JSON;


public class SaveStorageMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(SaveStorageMessProcessorImpl.class);
	 
	 private IStorageBusiSV storageBusiSV;
	 
	     public SaveStorageMessProcessorImpl(IStorageBusiSV storageBusiSV){
	         this.storageBusiSV = storageBusiSV;
	     }
	 
	     @Override
	     public void process(MessageAndMetadata message) throws Exception {
	         if (null == message)
	             return;
	         String content = new String(message.getMessage(), "UTF-8");
	         logger.info("--Topic:{}\r\n----key:{}\r\n----content:{}"
	                 , message.getTopic(),new String(message.getKey(), "UTF-8"),content);
	         //转换对象
	         STOStorage request = JSON.parseObject(content,STOStorage.class);
	         if (request==null)
	             return;
	         this.storageBusiSV.saveStorage(request);        
	     }
}
