package com.ai.slp.product.mds.marketprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.normproduct.impl.INormProductSVImpl;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.alibaba.fastjson.JSON;


public class UpdateMarketPriceMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(UpdateMarketPriceMessProcessorImpl.class);
	 
	 private INormProductSVImpl normProductSVImpl;
	 
	     public UpdateMarketPriceMessProcessorImpl(INormProductSVImpl normProductSVImpl){
	         this.normProductSVImpl = normProductSVImpl;
	     }
	 
	     @Override
	     public void process(MessageAndMetadata message) throws Exception {
	         if (null == message)
	             return;
	         String content = new String(message.getMessage(), "UTF-8");
	         logger.info("--Topic:{}\r\n----key:{}\r\n----content:{}"
	                 , message.getTopic(),new String(message.getKey(), "UTF-8"),content);
	         //转换对象
	         MarketPriceUpdate request = JSON.parseObject(content,MarketPriceUpdate.class);
	         if (request==null)
	             return;
	         this.normProductSVImpl.updateMarketPrice(request);        
	     }
}
