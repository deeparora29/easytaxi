package com.easytaxi.common.thread;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.Taxi;
import com.easytaxi.location.service.TaxiDataService;

/**
 * 
 * @Package com.easytaxi.common.thread
 *
 * @Title: TaxiDataManageThread.java 
 *	
 * @Description: 出租车数据后台处理线程
 *
 * @Copyright:   Copyright (c)2010
 *
 * @author:      付奎
 *
 * @date 2011-3-27 下午10:27:34
 *
 */
public class TaxiDataManageThread implements Runnable{

	private Log log = LogFactory.getLog(TaxiDataManageThread.class);
	
	
	@Override
	public void run() {
		try {
			while( true ){
				TaxiDataService service = TaxiDataService.getInstance();
				BlockingQueue<Taxi> queue = service.getTaxiWorkQueue() ;
				if( !queue.isEmpty() ){
					//取出一个taxi信息进行处理
					Taxi taxi = queue.poll();
					//TODO 处理相关业务
				}else{
					Thread.sleep( 1000 );
				}
			}
		} catch (Exception e) {
			log.error("出租车数据后台处理线程运行失败：", e);
		}
		
	}

}
