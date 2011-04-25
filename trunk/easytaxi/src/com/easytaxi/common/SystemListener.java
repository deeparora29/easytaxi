package com.easytaxi.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.common.thread.RecordTrackingThread;
import com.easytaxi.common.thread.ThreadPoolManager;
import com.easytaxi.common.thread.UserSessionCheckThread;

/**
 * 
 * @Package com.easytaxi.common
 * 
 * @Title: SystemListener.java
 * 
 * @Description: 服务器启动时初始化系统级实例、系统变量、启动后台线程等
 * 
 * @Copyright: Copyright (c)2010
 * 
 * @author: 付奎
 * 
 * @date 2011-3-27 下午09:54:14
 * 
 * @version V1.0
 * 
 */
public class SystemListener implements ServletContextListener {

	private Log log = LogFactory.getLog(SystemListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("初始化系统级实例、系统变量、启动后台线程....................");

		//当前系统的CPU的数目
		int poolSize = Runtime.getRuntime().availableProcessors();
		ThreadPoolManager.getInstance().initThreadPool( poolSize );
		
		//启动出租车数据后台线程
		//new Thread( new TaxiDataManageThread() ).start();
		//log.info("出租车数据后台处理线程启动  ** OK...................");
		
		
		//加载乘客数据后台处理线程
		//new Thread( new PassengerDataManageThread() ).start();
		//log.info("乘客数据后台处理线程启动 ** OK...................");
		
		//加载检查账号登录是否过期线程
		new Thread(new UserSessionCheckThread()).start();
		log.info("启动检查账号登录是否过期线程 ** OK...................");
		
		
		new Thread(new RecordTrackingThread()).start();
		log.info("启动记录行驶线路线程 ** OK...................");
		
	}

}
