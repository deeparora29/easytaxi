package com.easytaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.Passenger;
import com.easytaxi.location.service.PassengerDataService;

public class PassengerSessionCheckThread implements Runnable{
	private Log log = LogFactory.getLog(PassengerSessionCheckThread.class);
	@Override
	public void run() {
		try {
			while( true ){
				PassengerDataService service = PassengerDataService.getInstance();
				List<Passenger> passengerList = service.getModifiedInner24HoursData();
				if(passengerList!=null&&passengerList.size()>0){
					log.info("系统中会话存在超过24小时的乘客数量为：" + passengerList.size());
					for (Passenger passenger : passengerList) {
						if(passenger!=null){
							ConcurrentMap<String, Passenger>  temp = service.getPassengerLoginInfoMap() ;
							temp.remove(passenger.getUserid());
							log.info("系统检测到用户： " + passenger.getUserid() +"登录时间超过24小时，需要重新登录" );
						}
					}
				}else{
					Thread.sleep( 60 * 1000 );
				}
			}
		} catch (Exception e) {
			log.error("乘客会话检查线程运行失败：", e);
		}
		
	}

}
