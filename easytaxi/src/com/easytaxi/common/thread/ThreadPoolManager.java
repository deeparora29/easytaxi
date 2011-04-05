package com.easytaxi.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class ThreadPoolManager {

	private Log log = LogFactory.getLog(ThreadPoolManager.class);
	
	private static ThreadPoolManager instance =  new ThreadPoolManager();
	
	private static ExecutorService  executor = null ;
	
	//单个CPU时线程池中工作线程的数目
	private final int SIZE = 2;  
	
	
	private ThreadPoolManager(){
		
	}
	
	/**
	 * 
	* @Description: 获取线程池管理类实例
	* 
	* @return ThreadPoolManager
	*
	* @author: 付奎
	*
	* @datatime: 2011-3-27 下午09:26:24
	 */
	public static ThreadPoolManager getInstance(){
		if( instance == null ){
			instance =  new ThreadPoolManager();
		}
		return instance ;
	}
	
	
	/**
	 * 
	* @Description: 初始化线程池
	* 
	* @param poolSize 一般为服务器处理器个数加1
	*
	* @author: 付奎
	*
	* @datatime: 2011-3-27 下午09:43:20
	 */
	public void initThreadPool( int poolSize ){
		executor = Executors.newFixedThreadPool( poolSize * SIZE  );  
		log.info("线程池初始化成功,线程数为 "+(poolSize * SIZE)+".............");
	}
	
	
	public ExecutorService getExecutorService(){
		return executor ;
	}
	
	
	
	
	
}
