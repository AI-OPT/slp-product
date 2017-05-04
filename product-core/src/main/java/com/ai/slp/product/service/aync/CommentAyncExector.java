package com.ai.slp.product.service.aync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 评论异步提交服务
 * Date: 2017年5月3日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class CommentAyncExector {
	private static final Logger logger = LoggerFactory.getLogger(CommentAyncExector.class);
	private static ExecutorService pool = Executors.newFixedThreadPool(20);
	
	/**
	 * 提交
	 * @param task
	 * @author
	 */
	public static void submit(AyncTask task){
		try{
			pool.execute(task);
		}catch(Exception e){
			logger.error("出错了:"+JSON.toJSONString(e));
		}
	}
	
}
