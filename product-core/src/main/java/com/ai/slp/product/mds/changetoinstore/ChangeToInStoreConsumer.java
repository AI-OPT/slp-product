package com.ai.slp.product.mds.changetoinstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.mds.base.AbstractMdsConsumer;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.slp.product.api.normproduct.impl.INormProductSVImpl;
import com.ai.slp.product.api.product.impl.IProductManagerSVImpl;
import com.ai.slp.product.constants.NormProdConstants;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

//@Component
	public class ChangeToInStoreConsumer extends AbstractMdsConsumer {
		private static Logger logger = LoggerFactory.getLogger(ChangeToInStoreConsumer.class);
	
		@Autowired
		 private IProductBusiSV productBusiSV;
		@Override
		public void startMdsConsumer() throws Exception {
			logger.error("开始启动ChangeToInStoreConsumer。。。。。");
			IMsgProcessorHandler msgProcessorHandler=new IMsgProcessorHandler() {
	            @Override
	            public IMessageProcessor[] createInstances(int paramInt) {
	                List<IMessageProcessor> processors = new ArrayList<>();
	                IMessageProcessor processor = null;
	                for (int i = 0; i < paramInt; i++) {
	                    processor = new ChangeToInStoreMessProcessorImpl(productBusiSV);
	                    processors.add(processor);
	                }
	                return processors.toArray(new IMessageProcessor[processors.size()]);
	            }
	        };
	        IMessageConsumer msgConsumer= MDSClientFactory.getConsumerClient(
	        		NormProdConstants.MDSNS.MDS_NS_CHANGETOINSTORE_TOPIC, msgProcessorHandler);
	        msgConsumer.start();
	        logger.error("成功启动ChangeToInStoreConsumer。。。。。");
	
		}
	
	}

