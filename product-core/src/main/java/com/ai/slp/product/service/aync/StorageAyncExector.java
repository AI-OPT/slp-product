package com.ai.slp.product.service.aync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StorageAyncExector {
	private static ExecutorService storagePool = Executors.newFixedThreadPool(50);
	private static ExecutorService productPool = Executors.newFixedThreadPool(30);
	
	public static void submitStorage(AyncTask task){
		try{
			storagePool.execute(task);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void submitProduct(AyncTask task){
		try{
			productPool.execute(task);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
