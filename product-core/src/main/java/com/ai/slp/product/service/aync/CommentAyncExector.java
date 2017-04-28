package com.ai.slp.product.service.aync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.slp.product.api.flushdata.CreateDataBatSVImpl;
import com.alibaba.fastjson.JSON;

public class CommentAyncExector {
	private static final Logger logger = LoggerFactory.getLogger(CreateDataBatSVImpl.class);
	private static ExecutorService pool = Executors.newFixedThreadPool(20);
	
	public static void submit(AyncTask task){
		try{
			pool.execute(task);
		}catch(Exception e){
			logger.error("出错了:"+JSON.toJSONString(e));
		}
	}
	
}
