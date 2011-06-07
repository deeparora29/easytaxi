package com.mobilesoft.smarttaxi.common.thread;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mobilesoft.smarttaxi.bo.Passenger;
import com.mobilesoft.smarttaxi.bo.Taxi;
import com.mobilesoft.smarttaxi.common.utils.BeanFactoryUtil;
import com.mobilesoft.smarttaxi.location.service.PassengerDataService;

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
				PassengerDataService service = PassengerDataService.getInstance();
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
			try {
				Thread.sleep( 3*60*1000 );
			} catch (InterruptedException e1) {
			}
		}
	}
	
	
	
	
}
