package com.easytaxi.location.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import com.easytaxi.bo.CreditRecord;
import com.easytaxi.bo.Driver;
import com.easytaxi.bo.GPSData;
import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;
import com.easytaxi.request.bo.RequestInfo;
import com.easytaxi.request.bo.RequestResult;
import com.easytaxi.request.dao.CallTaxiDao;
import com.easytaxi.request.service.CallTaxiServie;
import com.easytaxi.request.service.CreditRateService;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	//用于接受出租车提供的相关数据，共后续处理
	private static BlockingQueue<Taxi> taxiWorkQueue = new LinkedBlockingQueue<Taxi>();
	
	//出租车登录信息
	private static ConcurrentMap<String , Passenger> taxiLoginInfoMap = new ConcurrentHashMap<String, Passenger>();
	
	//存放出租车GPS数据
	private static ConcurrentMap<String , UploadGPSData> taxiGPSMap = new ConcurrentHashMap<String, UploadGPSData>();
	
	private TaxiDao taxiDao ;
	
	private CallTaxiDao callTaxiDao;
	
	private PassengerDao passengerDao;
	
	private CallTaxiServie callTaxiServie ;
	
	private CreditRateService creditRateService ;
	
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
				//没有重复的用户，email和phone都可以作为账号
				if( !taxiLoginInfoMap.containsKey(cab)){
					Taxi taxi = new Taxi( cab, password, license, company, email,carModel, chargeModel,list, descr);
					//由于需要立即返回userid，则不能异步处理
					String userId = updateTaxiInfo( taxi );
					return getReturnMessage( transCode , userId );
				}else{
					//账号重复
					jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					return jsonString ;
				}
			}else if(transCode.equals(SystemPara.T_LOGIN)){//Taxi Login T002
				String cab = jsonObject.getString("cab");
				String password = jsonObject.getString("password");
				Taxi taxi = taxiDao.getTaxiByPlateNumber(cab);
				if(taxi==null){
					jsonString = getReturnErrorMessage(ErrorCode.USER_NOT_FOUND);
				}else{
					String userid = taxi.getUserid();
					String phone = taxi.getPhone0()+","+ taxi.getPhone0();
					String _password = taxi.getPassword() ;
					if(_password.equals(password)){
						jsonString = getReturnMessage( transCode , userid , phone ); 
					}else{
						//密码不正确
						jsonString = getReturnErrorMessage(ErrorCode.PASSWORD_NOT_ACCURATE);
					}
				}
			}else if(transCode.equals(SystemPara.T_UPLOADGPS)){//上传出租车GPS数据 T003
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				String cab = jsonObject.getString("cab");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				UploadGPSData data = new UploadGPSData();
				data.setCab(cab);
				data.setUserId(userid);
				data.setGpsdata(gpsdata);
				taxiGPSMap.put(cab, data);
			}else if(transCode.equals(SystemPara.T_CONFIRM_CALL)){//Confirm Call T004
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				String requestNo = jsonObject.getString("requestNo");
				RequestResult resulst =callTaxiServie.confirmRequest(userid, requestNo);
				if(resulst.getErrorCode().equals(ErrorCode.SUCCESS)){
					jsonString = getReturnMessage(transCode,requestNo,userid,resulst);
				}else{
					jsonString = getReturnErrorMessage(resulst.getErrorCode());
				}
			}else if(transCode.equals(SystemPara.T_CANCEL_CALL)){//Cancel Call T005
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				String comments = jsonObject.getString("comments");
				callTaxiServie.cancelRequest(userid, requestNo, comments);
				jsonString = getReturnMessage(transCode);
			}else if(transCode.equals(SystemPara.T_CREDIT_RATING)){//Credit Rating T006
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				float credit = Float.valueOf(jsonObject.getString("credit"));
				String comments = jsonObject.getString("comments");
				creditRateService.doCreditRating(userid, requestNo, credit, comments);
				jsonString = getReturnMessage(transCode);
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_CREDIT)){//Query Passenger’s Credit  T007
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				int number = Integer.valueOf(jsonObject.getString("number"));
				List<CreditRecord> list = creditRateService.getCreditDetail(userid, passengerid, number );
				jsonString = getReturnMessage(transCode,list);
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_LOCATION)){//Query Passenger’s Credit  T008
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				RequestInfo info = getCallTaxiDao().getRequestInfo(passengerid);
				GPSData data= new GPSData(info.getStartLat(),info.getStartLong());
				jsonString = getReturnMessage(transCode,data);
			}else if(transCode.equals(SystemPara.T_QUERY_CALL_INFO)){//Query Detail Taxi Call Info  T009
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				RequestInfo info = getCallTaxiDao().getRequestInfo(requestNo);
				jsonString = getReturnMessage(transCode, info);
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_INFO)){//Query Detail Passenger Info  T010
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				Passenger passenger = passengerDao.getPassengerByUserid(userid);
				RequestInfo info = getCallTaxiDao().getRequestInfo(passengerid);
				jsonString = getReturnMessage(transCode, passenger,info);
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
	
	
	public ConcurrentMap<String , UploadGPSData> getTaxiGPSMap(){
		return taxiGPSMap ;
	}
	
	public static String updateTaxiInfo(Taxi taxi){
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(taxiWorkQueue.isEmpty());
	}

	public TaxiDao getTaxiDao() {
		return taxiDao;
	}

	public void setTaxiDao(TaxiDao taxiDao) {
		this.taxiDao = taxiDao;
	}

	public CallTaxiServie getCallTaxiServie() {
		return callTaxiServie;
	}

	public void setCallTaxiServie(CallTaxiServie callTaxiServie) {
		this.callTaxiServie = callTaxiServie;
	}

	public CreditRateService getCreditRateService() {
		return creditRateService;
	}

	public void setCreditRateService(CreditRateService creditRateService) {
		this.creditRateService = creditRateService;
	}

	public CallTaxiDao getCallTaxiDao() {
		return callTaxiDao;
	}

	public void setCallTaxiDao(CallTaxiDao callTaxiDao) {
		this.callTaxiDao = callTaxiDao;
	}

	public PassengerDao getPassengerDao() {
		return passengerDao;
	}

	public void setPassengerDao(PassengerDao passengerDao) {
		this.passengerDao = passengerDao;
	}
	
	
}
