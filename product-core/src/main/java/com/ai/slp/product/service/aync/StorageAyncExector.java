package com.ai.slp.product.service.aync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.slp.product.api.flushdata.CreateDataBatSVImpl;
import com.alibaba.fastjson.JSON;

import sun.util.logging.resources.logging;

public class StorageAyncExector {
	private static final Logger logger = LoggerFactory.getLogger(CreateDataBatSVImpl.class);
	
	private static ExecutorService storagePool = Executors.newFixedThreadPool(50);
	private static ExecutorService productPool = Executors.newFixedThreadPool(30);
	
	public static void submitStorage(AyncTask task){
		try{
			storagePool.execute(task);
		}catch(Exception e){
			logger.error("出错了:"+JSON.toJSONString(e));
		}
	}
	
	public static void submitProduct(AyncTask task){
		try{
			productPool.execute(task);
		}catch(Exception e){
			logger.error("出错了:"+JSON.toJSONString(e));
		}
	}
	
}
