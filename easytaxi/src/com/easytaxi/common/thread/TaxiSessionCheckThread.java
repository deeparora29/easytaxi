package com.easytaxi.common.thread;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.Taxi;
import com.easytaxi.location.service.TaxiDataService;

public class TaxiSessionCheckThread implements Runnable{
	private Log log = LogFactory.getLog(TaxiSessionCheckThread.class);
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
			log.error("用户会话检查线程运行失败：", e);
		}
		
	}

}
