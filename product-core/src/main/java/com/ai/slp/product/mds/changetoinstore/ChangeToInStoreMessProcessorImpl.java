package com.ai.slp.product.mds.changetoinstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.alibaba.fastjson.JSON;


public class ChangeToInStoreMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(ChangeToInStoreMessProcessorImpl.class);
	 
	 private IProductBusiSV productBusiSV;
	 
	     public ChangeToInStoreMessProcessorImpl(IProductBusiSV productBusiSV){
	         this.productBusiSV = productBusiSV;
	     }
	 
	     @Override
	     public void process(MessageAndMetadata message) throws Exception {
	         if (null == message)
	             return;
	         String content = new String(message.getMessage(), "UTF-8");
	         logger.info("--Topic:{}\r\n----key:{}\r\n----content:{}"
	                 , message.getTopic(),new String(message.getKey(), "UTF-8"),content);
	         //转换对象
	         ProductInfoQuery request = JSON.parseObject(content,ProductInfoQuery.class);
	         if (request==null)
	             return;
	         try {
				this.productBusiSV.changeSaleToStore(request.getTenantId(),request.getSupplierId(),request.getProductId(),request.getOperId());
			} catch (BusinessException e) {
				logger.info("消息处理异常"+e.getMessage());
			}
	         
	     }
}
