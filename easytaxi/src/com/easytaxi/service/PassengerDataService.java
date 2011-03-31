package com.easytaxi.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.DestLocation;
import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.StartLocation;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;

public class PassengerDataService extends BaseService{
	
	Log log = LogFactory.getLog(PassengerDataService.class);
	
	private static PassengerDataService instance = new PassengerDataService ();
	
	//存放乘车信息队列
	private static BlockingQueue<Passenger> passengerWorkQueue = new LinkedBlockingQueue<Passenger>();
	
	//存放乘客信息
	private static ConcurrentMap<String , Passenger> passengerInfoMap = new ConcurrentHashMap<String, Passenger>();
	
	//存放乘客广播打车消息
	private static ConcurrentMap<String , Passenger> broadcastCallTaxiMap = new ConcurrentHashMap<String, Passenger>();
	
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
			String transCode = jsonObject.getString("TransCode");
			if(transCode==null||transCode.length()<4){
				jsonString = getReturnErrorMessage(SystemPara.TRANS_CODE_ERROR);
				return jsonString ;
			}else if(transCode.equals(SystemPara.P_REGISTER)){//乘车注册
				String firstname = jsonObject.getString("firstname");
				String lastname = jsonObject.getString("lastname");
				String password = jsonObject.getString("password");
				String phone = jsonObject.getString("phone");
				String email = jsonObject.getString("email");
				String nickName = jsonObject.getString("nickName");
				String gender = jsonObject.getString("gender");
				String descr = jsonObject.getString("descr");
				Passenger p = new Passenger(transCode ,firstname, lastname, password, phone, email, nickName, gender, descr);
				passengerWorkQueue.offer( p );
			}else if( transCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求
				String Id = jsonObject.getString("Id");
				String phone = jsonObject.getString("phone");
				String userGPS = jsonObject.getString("userGPS");
				String dstGPS = jsonObject.getString("dstGPS");
				int number = jsonObject.getInt("number");
				int luggage = jsonObject.getInt("luggage");
				String otherinfos = jsonObject.getString("otherinfo");
				String share = jsonObject.getString("share");
				StartLocation startLocation = (StartLocation)JsonUtil.getObjectByJsonString(userGPS, StartLocation.class);
				DestLocation destLocation = (DestLocation)JsonUtil.getObjectByJsonString(dstGPS, DestLocation.class);
				Passenger p = new Passenger(transCode ,phone, startLocation, destLocation, number, luggage, otherinfos , share);
				passengerWorkQueue.offer( p );
			}else if(transCode.equals(SystemPara.P_CREDITRATING)){//信用评价
				
			}
			
			
			
			
			//根据交易编号获取返回信息
			jsonString = getReturnMessage(transCode);
		}
		
		
		return jsonString ;
	}
	
	
	
	public BlockingQueue<Passenger> getPassengerWorkQueue(){
		return passengerWorkQueue ;
	}
}
