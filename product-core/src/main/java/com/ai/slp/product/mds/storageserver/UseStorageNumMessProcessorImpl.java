package com.ai.slp.product.mds.storageserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.base.exception.BusinessException;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.slp.product.api.storageserver.impl.IStorageNumSVImpl;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.alibaba.fastjson.JSON;


public class UseStorageNumMessProcessorImpl implements IMessageProcessor{
	 private static Logger logger = LoggerFactory.getLogger(UseStorageNumMessProcessorImpl.class);
	 
	 private IStorageNumBusiSV storageNumBusiSV;
	 
	     public UseStorageNumMessProcessorImpl(IStorageNumBusiSV storageNumBusiSV){
	         this.storageNumBusiSV = storageNumBusiSV;
	     }
	 
	     @Override
	     public void process(MessageAndMetadata message) throws Exception {
	         if (null == message)
	             return;
	         String content = new String(message.getMessage(), "UTF-8");
	         logger.info("--Topic:{}\r\n----key:{}\r\n----content:{}"
	                 , message.getTopic(),new String(message.getKey(), "UTF-8"),content);
	         //转换对象
	         StorageNumUserReq request = JSON.parseObject(content,StorageNumUserReq.class);
	         if (request==null)
	             return;
	         try {
				this.storageNumBusiSV.userStorageNum(request.getTenantId(),request.getSkuId(),request.getSkuNum(),null);
			} catch (BusinessException e) {
				logger.info("消息处理异常"+e.getMessage());
			}        
	     }
}
