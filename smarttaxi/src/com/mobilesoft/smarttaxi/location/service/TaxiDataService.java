package com.mobilesoft.smarttaxi.location.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import com.mobilesoft.smarttaxi.bo.CreditRecord;
import com.mobilesoft.smarttaxi.bo.Driver;
import com.mobilesoft.smarttaxi.bo.GPSData;
import com.mobilesoft.smarttaxi.bo.Passenger;
import com.mobilesoft.smarttaxi.bo.Taxi;
import com.mobilesoft.smarttaxi.bo.UploadGPSData;
import com.mobilesoft.smarttaxi.common.ErrorCode;
import com.mobilesoft.smarttaxi.common.SystemPara;
import com.mobilesoft.smarttaxi.common.service.BaseService;
import com.mobilesoft.smarttaxi.common.utils.JsonUtil;
import com.mobilesoft.smarttaxi.request.bo.RequestInfo;
import com.mobilesoft.smarttaxi.request.bo.RequestResult;
import com.mobilesoft.smarttaxi.request.dao.CallTaxiDao;
import com.mobilesoft.smarttaxi.request.service.CallTaxiServie;
import com.mobilesoft.smarttaxi.request.service.CreditRateService;
import com.mobilesoft.smarttaxi.usermgr.bo.LoginRecord;
import com.mobilesoft.smarttaxi.usermgr.dao.LoginRecordDao;
import com.mobilesoft.smarttaxi.usermgr.dao.PassengerDao;
import com.mobilesoft.smarttaxi.usermgr.dao.TaxiDao;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	//用于接受出租车提供的相关数据，共后续处理
	private static BlockingQueue<Taxi> taxiWorkQueue = new LinkedBlockingQueue<Taxi>();
	
	//出租车登录信息
	private static ConcurrentMap<String , Taxi> taxiLoginInfoMap = new ConcurrentHashMap<String, Taxi>();
	
	//存放出租车GPS数据
    // cab, gpsdata
	private static ConcurrentMap<String , UploadGPSData> taxiGPSMap = new ConcurrentHashMap<String, UploadGPSData>();
	
	private TaxiDao taxiDao ;
	
	private CallTaxiDao callTaxiDao;
	
	private PassengerDao passengerDao;
	
	private LoginRecordDao loginRecordDao ;
	
	private CallTaxiServie callTaxiServie ;
	
	private CreditRateService creditRateService ;
	
	public TaxiDataService(){
		
	}
	
	public static TaxiDataService getInstance(){
		return instance == null ? new TaxiDataService():instance ;
	}
	
	
	@SuppressWarnings("unchecked")
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
				Taxi t = taxiDao.getTaxiByPlateNumber(cab);
				if(t==null){
					t = taxiDao.getTaxiByEmail( email );
				}
				if(t==null){
					Taxi taxi = new Taxi( cab, password, license, company, email,carModel, chargeModel,list, descr);
					if( list != null && list.size()>0 ){
						Driver temp = list.get(0);
						taxi.setDriver0(temp.getName());
						taxi.setPhone0(temp.getPhone());
					}
					if( list != null && list.size()>1 ){
						Driver temp = list.get(1);
						taxi.setDriver1(temp.getName());
						taxi.setPhone1(temp.getPhone());
					}
					//由于需要立即返回userid，则不能异步处理
					String userId = taxiDao.getSerialNum("t_user_id", 5, "true");
					taxi.setUserid(userId);
					updateTaxiInfo( taxi );
					return getReturnMessage( transCode , userId );
				}else{
					jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					return jsonString ;
				}
			}else if(transCode.equals(SystemPara.T_LOGIN)){//Taxi Login T002
				String cab = jsonObject.getString("account");
				String password = jsonObject.getString("password");
				Taxi taxi = taxiDao.getTaxiByPlateNumber(cab);
				if(taxi==null){
					taxi = taxiDao.getTaxiByEmail( cab );
					if(taxi==null)
						jsonString = getReturnErrorMessage(ErrorCode.USER_NOT_FOUND);
				}
				if(taxi!=null){
					String userid = taxi.getUserid();
					String phone = taxi.getPhone0()+","+ taxi.getPhone0();
					String _password = taxi.getPassword() ;
					if(_password.equals(password)){
						jsonString = getReturnMessage( transCode , userid , phone ); 
					}else{
						//密码不正确
						jsonString = getReturnErrorMessage(ErrorCode.PASSWORD_NOT_ACCURATE);
					}

                    if (taxiLoginInfoMap.containsKey(userid)) {// 账号已登录
                        // jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_HAS_LOGIN);
                        // return jsonString;
                        // cancel the relogin check
                        // taxiLoginInfoMap.remove(userid);
                    } else
					//保存用户登录信息
                        taxiLoginInfoMap.put(userid, taxi);
					
					//更新登录时间
					//taxiDao.updateTaxiLoginTime();
					
					//保存登录信息
					LoginRecord loginRecord = new LoginRecord(userid, cab);
					loginRecordDao.doSaveLoginRecord(loginRecord);
				}
				return jsonString ;
			}else if(transCode.equals(SystemPara.T_UPLOADGPS)){//上传出租车GPS数据 T003
				String userid = jsonObject.getString("userid");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				
				String userGPS = jsonObject.getString("cabGPS");
				String cab = jsonObject.getString("cab");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				UploadGPSData data = new UploadGPSData();
				data.setCab(cab);
				data.setUserId(userid);
				data.setGpsdata(gpsdata);
				taxiGPSMap.put(cab, data);
				jsonString = getReturnMessage( transCode  );
				return jsonString ;
			}else if(transCode.equals(SystemPara.T_CONFIRM_CALL)){//Confirm Call T004
				String userid = jsonObject.getString("userid");
                Taxi taxi = taxiDao.getTaxiByUserid(userid);
				String userGPS = jsonObject.getString("cabGPS");
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
                UploadGPSData data = TaxiDataService.getInstance().getTaxiGPSMap().get(taxi.getCab());
                if (data != null)
                    data.setGpsdata(gpsdata);
                else {
                    data = new UploadGPSData();
                    data.setCab(taxi.getCab());
                    data.setUserId(userid);
                    data.setGpsdata(gpsdata);
                    taxiGPSMap.put(taxi.getCab(), data);
                }

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
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_LOCATION)){//Query Passenger Real-time location  T008
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				//RequestInfo info = getCallTaxiDao().getRequestInfo(passengerid);
				//GPSData data= new GPSData(info.getStartLat(),info.getStartLong());
				ConcurrentMap<String , GPSData> passengerRealtimeLocation = PassengerDataService.getInstance().getRealtimeLocationMap();
				GPSData data = passengerRealtimeLocation.get(passengerid);
				if(data!=null){
					jsonString = getReturnMessage(transCode,data);
				}else{
					jsonString = getReturnErrorMessage(ErrorCode.NOT_FOUND_DATA);
				}
			}else if(transCode.equals(SystemPara.T_QUERY_CALL_INFO)){//Query Detail Taxi Call Info  T009
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				RequestInfo info = getCallTaxiDao().getRequestInfo(requestNo);
				if(info!=null){
					jsonString = getReturnMessage(transCode, info);
				}else{
					jsonString = getReturnErrorMessage(ErrorCode.NOT_FOUND_DATA);
				}
			}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_INFO)){//Query Detail Passenger Info  T010
				String userid = jsonObject.getString("userid");
				String passengerid = jsonObject.getString("passengerid");
				Passenger passenger = passengerDao.getPassengerByUserid(passengerid);
				if(passenger==null){
					jsonString = getReturnErrorMessage(ErrorCode.NOT_FOUND_DATA);
				}else{
					RequestInfo info = getCallTaxiDao().getRequestInfo(passengerid);
					jsonString = getReturnMessage(transCode, passenger,info);
				}
			}else if(transCode.equals(SystemPara.T_UPDATE_TAXI_PHONE)){//Update Taxi phone  T011
				String userid = jsonObject.getString("userid");
				String phone = jsonObject.getString("phone");
				taxiDao.updateTaxiPhone(userid, phone);
				jsonString = getReturnMessage(transCode);
			}else if(transCode.equals(SystemPara.T_VALID_PASSENGER_CALL)){//Get valid passenger’s call  T012
				String userid = jsonObject.getString("userid");
				int status = Integer.valueOf(jsonObject.getString("status"));
				List<RequestInfo> list = getCallTaxiServie().getValidRequest();
				jsonString = getReturnMessage(transCode, list);
			}
			
		}
		return jsonString ;
	}
	
	private boolean checkPassengerIsLogin(String account) {
        return taxiLoginInfoMap.containsKey(account);
	}
	
	
	public List<Taxi> getModifiedInner24HoursData() {
		return this.taxiDao.getModifiedInner24HoursData();
	}
	
	public BlockingQueue<Taxi> getTaxiWorkQueue(){
		return taxiWorkQueue ;
	}
	
	public ConcurrentMap<String , Taxi> getTaxiLoginInfoMap(){
		return taxiLoginInfoMap ;
	}
	
	public ConcurrentMap<String , UploadGPSData> getTaxiGPSMap(){
		return taxiGPSMap ;
	}
	
	public  void updateTaxiInfo(Taxi taxi){
		
		taxiDao.doSaveTaxi(taxi);
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

	public LoginRecordDao getLoginRecordDao() {
		return loginRecordDao;
	}

	public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
		this.loginRecordDao = loginRecordDao;
	}
	
	
}
