package com.easytaxi.location.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import net.sf.json.JSONObject;
import com.easytaxi.bo.Driver;
import com.easytaxi.bo.GPSData;
import com.easytaxi.bo.Taxi;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	//用于接受出租车提供的相关数据，共后续处理
	private static BlockingQueue<Taxi> taxiWorkQueue = new LinkedBlockingQueue<Taxi>();
	
	//存放出租车信息
	private static ConcurrentMap<String , UploadGPSData> taxiInfoMap = new ConcurrentHashMap<String, UploadGPSData>();
	
	
	public TaxiDataService(){
		
	}
	
	public static TaxiDataService getInstance(){
		return instance == null ? new TaxiDataService():instance ;
	}
	
	
	public String offer( String requestString ){
		JSONObject jsonObject = parserRequest( requestString );
		String jsonString = "{}";
		if(jsonObject == null){
			jsonString = getReturnErrorMessage(ErrorCode.PARA_ERROR_CODE);
		}else{
			String transCode = jsonObject.getString("TransCode");
			if(transCode==null||transCode.length()<4){
				jsonString = getReturnErrorMessage(ErrorCode.TRANS_CODE_ERROR);
				return jsonString ;
			}else if(transCode.equals(SystemPara.T_REGISTER)){//Taxi Register T001
				String cab = jsonObject.getString("cab");
				String password = jsonObject.getString("password");
				String license = jsonObject.getString("license");
				String company = jsonObject.getString("company");
				String email = jsonObject.getString("email");
				String carModel = jsonObject.getString("carModel");
				String chargeModel = jsonObject.getString("chargeModel");
				String drivers = jsonObject.getString("drivers");
				List <Driver> list = JsonUtil.getListByJsonString(drivers, Driver.class);
				String descr  = jsonObject.getString("descr");
				
				
			}else if(transCode.equals(SystemPara.T_LOGIN)){//Taxi Login T002
				String cab = jsonObject.getString("cab");
				String password = jsonObject.getString("password");
				
			}else if(transCode.equals(SystemPara.T_UPLOADGPS)){//上传出租车GPS数据 T003
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				String cab = jsonObject.getString("cab");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				UploadGPSData data = new UploadGPSData();
				data.setCab(cab);
				data.setUserId(userid);
				data.setGpsdata(gpsdata);
				taxiInfoMap.put(userid, data);
			}else if(transCode.equals(SystemPara.T_CONFIRM_CALL)){//Confirm Call T004
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				String requestNo = jsonObject.getString("requestNo");
				
			}else if(transCode.equals(SystemPara.T_CANCEL_CALL)){//Cancel Call T005
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				String comments = jsonObject.getString("comments");
				
			}else if(transCode.equals(SystemPara.T_CREDIT_RATING)){//Credit Rating T006
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				float credit = Float.valueOf(jsonObject.getString("credit"));
				String comments = jsonObject.getString("comments");
				
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_CREDIT)){//Query Passenger’s Credit  T007
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				int number = Integer.valueOf(jsonObject.getString("number"));
				
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_LOCATION)){//Query Passenger’s Credit  T008
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				
			}else if(transCode.equals(SystemPara.T_QUERY_CALL_INFO)){//Query Detail Taxi Call Info  T009
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_INFO)){//Query Detail Passenger Info  T010
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				
			}else if(transCode.equals(SystemPara.T_UPDATE_TAXI_PHONE)){//Update Taxi phone  T011
				String userid = jsonObject.getString("userid");
				String phone = jsonObject.getString("phone");
				
			}else if(transCode.equals(SystemPara.T_VALID_PASSENGER_CALL)){//Get valid passenger’s call  T012
				String userid = jsonObject.getString("userid");
				int status = Integer.valueOf(jsonObject.getString("status"));
			}
			
		}
		return jsonString ;
	}
	
	public BlockingQueue<Taxi> getTaxiWorkQueue(){
		return taxiWorkQueue ;
	}
	
	
	public ConcurrentMap<String , UploadGPSData> getTaxiInfoMap(){
		return taxiInfoMap ;
	}
	
	public void updateTaxiInfo(Taxi taxi){
		//taxiInfoMap.put(taxi.getPlateNumber(), taxi);
	}
	
	public static void main(String[] args) {
		System.out.println(taxiWorkQueue.isEmpty());
	}
}
