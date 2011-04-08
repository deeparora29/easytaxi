package com.easytaxi.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import com.easytaxi.bo.DestLocation;
import com.easytaxi.bo.GPSData;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	//用于接受出租车提供的相关数据，共后续处理
	private static BlockingQueue<Taxi> taxiWorkQueue = new LinkedBlockingQueue<Taxi>();
	
	//存放出租车信息
	private static ConcurrentMap<String , Taxi> taxiInfoMap = new ConcurrentHashMap<String, Taxi>();
	
	//保存出租车上传GPS数据
	private static ConcurrentMap<String , List<GPSData>> taxiGPSDataMap = new ConcurrentHashMap<String, List<GPSData> >();
	
	
	
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
			}else if(transCode.equals(SystemPara.T_UPLOADGPS)){//上传出租车GPS数据
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				DestLocation destLocation = (DestLocation)JsonUtil.getObjectByJsonString(userGPS, DestLocation.class);
				List<GPSData>passengerTrackingList = null;
				GPSData data = new GPSData();
				data.setUserId(userid);
				data.setDestLocation(destLocation);
				if( taxiGPSDataMap.containsKey(userid) ){
					passengerTrackingList = taxiGPSDataMap.get(userid);
					passengerTrackingList.add( data );
				}else{
					passengerTrackingList = new ArrayList<GPSData>();
					passengerTrackingList.add( data );
					taxiGPSDataMap.put(userid , passengerTrackingList);
				}
			}
		}
		return jsonString ;
	}
	
	public BlockingQueue<Taxi> getTaxiWorkQueue(){
		return taxiWorkQueue ;
	}
	
	
	public ConcurrentMap<String , Taxi> getTaxiInfoMap(){
		return taxiInfoMap ;
	}
	
	public void updateTaxiInfo(Taxi taxi){
		//taxiInfoMap.put(taxi.getPlateNumber(), taxi);
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(taxiWorkQueue.isEmpty());
	}
}
