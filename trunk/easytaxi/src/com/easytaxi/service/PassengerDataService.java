package com.easytaxi.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.easytaxi.bo.Passenger;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;

public class PassengerDataService extends BaseService{
	
	Log log = LogFactory.getLog(PassengerDataService.class);
	
	private static PassengerDataService instance = new PassengerDataService ();
	
	//存放乘车信息队列
	private static BlockingQueue<Passenger> passengerWorkQueue = new LinkedBlockingQueue<Passenger>();
	
	private PassengerDataService(){
		
	}
	
	public static PassengerDataService getInstance(){
		synchronized(instance){
			if( instance == null ){
				instance = new PassengerDataService ();
			}
		}
		return instance ;
	}
	
	
	public String offer( String requestString ){
		JSONObject jsonObject = parserRequest( requestString );
		String jsonString = "{}";
		if(jsonObject == null){
			jsonString = getReturnErrorMessage(SystemPara.PARA_ERROR_CODE);
		}else{
			Passenger p = new Passenger();
			passengerWorkQueue.offer( p );
			String transCode = jsonObject.getString("TransCode");
			//根据交易编号获取返回信息
			jsonString = getReturnMessage(transCode);
		}
		
		
		return jsonString ;
	}
	
}
