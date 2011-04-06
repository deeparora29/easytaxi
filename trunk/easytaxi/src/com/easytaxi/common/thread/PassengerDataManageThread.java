package com.easytaxi.common.thread;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.service.PassengerDataService;

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
public class PassengerDataManageThread implements Runnable{

	private Log log = LogFactory.getLog(PassengerDataManageThread.class);
	
	
	@Override
	public void run() {
		try {
			while( true ){
				PassengerDataService service = (PassengerDataService)BeanFactoryUtil.getBean("");
				BlockingQueue<Passenger> queue = service.getPassengerWorkQueue() ;
				if( !queue.isEmpty() ){
					//取出一个taxi信息进行处理
					Passenger passenger = queue.poll();
					service.updateData(passenger);
				}else{
					Thread.sleep( 1000 );
				}
			}
		} catch (Exception e) {
			log.error("乘客数据后台处理线程运行失败：", e);
		}
	}
	
	
	
	
}
