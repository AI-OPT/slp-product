package com.ai.slp.product.mds.normproduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.normproduct.impl.INormProductSVImpl;
import com.ai.slp.product.api.normproduct.param.NormProdSaveRequest;
import com.alibaba.fastjson.JSON;


public class INormProductSVMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(INormProductSVMessProcessorImpl.class);
	 
	 private INormProductSVImpl normProductSVImpl;
	 
	     public INormProductSVMessProcessorImpl(INormProductSVImpl normProductSVImpl){
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
	         NormProdSaveRequest request = JSON.parseObject(content,NormProdSaveRequest.class);
	         if (request==null)
	             return;
	         this.normProductSVImpl.updateProductAndStoGroup(request);        
	     }
}
