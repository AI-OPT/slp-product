package com.ai.slp.product.service.aync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 仓库异步提交服务
 * Date: 2017年5月3日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class StorageAyncExector {
	private static final Logger logger = LoggerFactory.getLogger(StorageAyncExector.class);
	
	private static ExecutorService storagePool = Executors.newFixedThreadPool(50);
	private static ExecutorService productPool = Executors.newFixedThreadPool(30);
	
	/**
	 * 提交
	 * @param task
	 * @author
	 */
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
