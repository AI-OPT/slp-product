package com.ai.slp.product.mds.marketprice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.normproduct.impl.INormProductSVImpl;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.alibaba.fastjson.JSON;


public class UpdateMarketPriceMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(UpdateMarketPriceMessProcessorImpl.class);
	 
	 private INormProductBusiSV normProductBusiSV;
	 
	     public UpdateMarketPriceMessProcessorImpl(INormProductBusiSV normProductBusiSV){
	         this.normProductBusiSV = normProductBusiSV;
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
	         try {
				this.normProductBusiSV.updateMarketPrice(request);
			} catch (BusinessException e) {
				logger.info("消息处理异常"+e.getMessage());
			}        
	     }
}
