package com.easytaxi.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.CreditRecord;
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
import com.easytaxi.request.service.CallTaxiServie;
import com.easytaxi.request.service.CreditRateService;
import com.easytaxi.usermgr.bo.LoginRecord;
import com.easytaxi.usermgr.dao.LoginRecordDao;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

public class PassengerDataService extends BaseService{
	

	Log log = LogFactory.getLog(PassengerDataService.class);
	
	private static PassengerDataService instance = new PassengerDataService ();
	
	//存放乘车信息队列
	private static BlockingQueue<Passenger> passengerWorkQueue = new LinkedBlockingQueue<Passenger>();
	
	//存放乘客信息[登录]
	private static ConcurrentMap<String , Passenger> passengerInfoMap = new ConcurrentHashMap<String, Passenger>();
	
	//存放乘客广播打车消息
	private static ConcurrentMap<String , Passenger> broadcastCallTaxiMap = new ConcurrentHashMap<String, Passenger>();
	
	//保存乘客上传GPS数据或经过路线
	private static ConcurrentMap<String , List<UploadGPSData>> passengerTrackingMap = new ConcurrentHashMap<String, List<UploadGPSData> >();
	
	//乘客实时位置信息
	private static ConcurrentMap<String , GPSData> realtimeLocationMap = new ConcurrentHashMap<String, GPSData>();
	
	private PassengerDao passengerDao ;
	
	private TaxiDao taxiDao ;
	
	private LoginRecordDao loginRecordDao ;
	
	private CallTaxiServie callTaxiServie ;
	
	private CreditRateService creditRateService ;
	
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
        log.info("Request:::" + requestString);
		JSONObject jsonObject = parserRequest( requestString );
		String jsonString = "{}";
		if(jsonObject == null){
			jsonString = getReturnErrorMessage(ErrorCode.PARA_ERROR_CODE);
		}else{
            log.info("Passenger:::" + jsonObject.toString());
			String transCode = jsonObject.getString("TransCode");
			if(transCode==null||transCode.length()<4){
				jsonString = getReturnErrorMessage(ErrorCode.TRANS_CODE_ERROR);
				return jsonString ;
			}else if(transCode.equals(SystemPara.P_REGISTER)){//乘客注册
				//获取用户信息
				String firstname = jsonObject.getString("firstname");
				String lastname = jsonObject.getString("lastname");
				String password = jsonObject.getString("password");
				String phone = jsonObject.getString("phone");
				String email = jsonObject.getString("email");
				String nickName = jsonObject.getString("nickname");
				String gender = jsonObject.getString("gender");
				String descr = jsonObject.getString("descr");
				String province = jsonObject.getString("province");
				String agreement = jsonObject.getString("agreement");
				//没有重复的用户，email和phone都可以作为账号
				Passenger p = passengerDao.getPassengerByPhone(phone);
				if( p == null ){
					p = passengerDao.getPassengerByEmail(email);
				}
				if(p == null ){
					Passenger temp = new Passenger(transCode ,firstname, lastname, password, phone, email, 
							nickName, gender, descr , province , agreement);
					String userId = updateData( temp );
					return getReturnMessage( transCode , userId );
				}else{
					//账号重复
					jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					return jsonString ;
				}
				
			}else if(transCode.equals(SystemPara.P_LOGIN)){//Login
				String account = jsonObject.getString("account");
				String password = jsonObject.getString("password");
				Passenger p = passengerDao.getPassengerByPhone(account);
				if( p == null ){
					p = passengerDao.getPassengerByEmail(account);
				}
				if(p != null ){
					String userid = p.getUserid();
					String phone = p.getPhone();
					String db_password = p.getPassword();
					if(password!=null&&password.equals(db_password)){
						jsonString = getReturnMessage( transCode , userid , phone ); 
					}else{
						jsonString = getReturnErrorMessage(ErrorCode.PASSWORD_NOT_ACCURATE);
					}

                    if (passengerInfoMap.containsKey(userid)) {// 账号已登录
                        // jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_HAS_LOGIN);
                        // return jsonString;
                        // cancel the relogin check
                        // passengerInfoMap.remove(userid);
                        log.info("User=" + userid + "login again");
                    } else
                        passengerInfoMap.put(userid, p);
					//修改登录时间
					//passengerDao.doUpdatePassengerLoginTime();
					
					//保存登录信息
					LoginRecord loginRecord = new LoginRecord(userid, account);
					loginRecordDao.doSaveLoginRecord(loginRecord);
					
				}else{
					jsonString = getReturnErrorMessage(ErrorCode.USER_NOT_FOUND);
				}
				return jsonString ;
			}else if( transCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求 P0003
				String userid = jsonObject.getString("userid");
				String phone = jsonObject.getString("phone");
				String userGPS = jsonObject.getString("userGPS");
				GPSData startLocation = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				double start_long =  startLocation.getLng();
				double start_lat = startLocation.getLat();
				String start_text = startLocation.getText();
				String dstGPS = jsonObject.getString("dstGPS");
				GPSData destLocation = (GPSData)JsonUtil.getObjectByJsonString(dstGPS, GPSData.class);
				double end_long =  destLocation.getLng();
				double end_lat = destLocation.getLat();
				String end_text = destLocation.getText();
				int number = jsonObject.getInt("number");
				int luggage = jsonObject.getInt("luggage");
				String comments = jsonObject.getString("comments");
				String share = jsonObject.getString("share");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
                // save passenger's location
                if (realtimeLocationMap.get(userid) != null)
                    realtimeLocationMap.remove(userid);
                realtimeLocationMap.put(userid, new GPSData(start_lat, start_long));
				RequestInfo requestInfo = new RequestInfo(userid,phone,start_long,start_lat,start_text,end_long,
						end_lat,end_text,number,luggage,comments,share);
				String res = callTaxiServie.requestTaxi(requestInfo);
				if("".equals(res)){
					jsonString = getReturnErrorMessage(ErrorCode.REQUEST_ISVALID);
				}else{
					jsonString = getReturnMessage(transCode,res);
				}
				return jsonString;
			}else if(transCode.equals(SystemPara.P_GETCONFIRM)){//获取出租车响应 P004
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				
				RequestResult resulst = callTaxiServie.getConfirmedTaxiInfo(userid, requestNo);
				if(resulst.getErrorCode().equals(ErrorCode.SUCCESS)){

                    // 从内存中获取出租车实时GPS数据
                    String taxiid = resulst.getTaxi().getUserid();
                    UploadGPSData taxiGPSData = TaxiDataService.getInstance().getTaxiGPSMap().get(taxiid);
                    // hardcode taxi gps data
                    if (taxiGPSData == null) {
                        taxiGPSData = new UploadGPSData();
                        taxiGPSData.setUserId(taxiid);
                        taxiGPSData.setStatus(0);
                        RequestInfo requestInfo = callTaxiServie.getRequestInfoByRequestId(requestNo);
                        taxiGPSData.setGpsdata(new GPSData(requestInfo.getStartLat() + 0.5,
                            requestInfo.getStartLong() + 0.5));
                    }
					jsonString = getReturnMessage(transCode,requestNo,resulst,taxiGPSData);
				}else{
					jsonString = getReturnErrorMessage(resulst.getErrorCode());
				}
				return jsonString;
			}else if(transCode.equals(SystemPara.P_CANCELREQUEST)){//取消用车请求 P005
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				String comments = jsonObject.getString("comments");
				
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				callTaxiServie.cancelRequest(userid, requestNo, comments);
				jsonString = getReturnMessage(transCode);
				return jsonString;
			}else if(transCode.equals(SystemPara.P_CREDITRATING)){//信用评价P006
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				float credit = Float.valueOf(jsonObject.getString("credit"));
				String comments = jsonObject.getString("comments");
				creditRateService.doCreditRating(userid, requestNo, credit, comments);
				jsonString = getReturnMessage(transCode);
				return jsonString;
			}else if(transCode.equals(SystemPara.P_QUERYCREDIT)){//查询Taxi信誉度P007
				String userid = jsonObject.getString("userid");
				String cab = jsonObject.getString("cab");
				int number = Integer.valueOf(jsonObject.getString("number"));
				List<CreditRecord> list = creditRateService.getCreditDetail(userid, cab, number );
				jsonString = getReturnMessage(transCode,list);
				return jsonString;
			}else if(transCode.equals(SystemPara.P_QUERYTAXIGPS)){//查询Taxi GPSP008
				String userid = jsonObject.getString("userid");
				String cab = jsonObject.getString("cab");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				GPSData passengerGPSData = realtimeLocationMap.get(userid);
				if(passengerGPSData!=null){
					jsonString = getReturnMessage(transCode,passengerGPSData);
				}else{
					jsonString = getReturnErrorMessage(ErrorCode.NOT_FOUND_DATA);
				}
				return jsonString;
			}else if(transCode.equals(SystemPara.P_UPLOADGPS_TRACK)){//上传GPS数据或经过路线
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				String track = jsonObject.getString("track");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				GPSData gpsdata = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				realtimeLocationMap.put(userid, gpsdata);
				List<UploadGPSData>passengerTrackingList = null;
				UploadGPSData uploadGPSData = new UploadGPSData();
				uploadGPSData.setUserId(userid);
				if( passengerTrackingMap.containsKey(userid) ){
					passengerTrackingList = passengerTrackingMap.get(userid);
					uploadGPSData.setGpsdata(gpsdata);
					passengerTrackingList.add( uploadGPSData );
				}else{
					passengerTrackingList = new ArrayList<UploadGPSData>();
					passengerTrackingList.add(uploadGPSData);
				}
				passengerTrackingMap.put(userid , passengerTrackingList);
				jsonString = getReturnMessage(transCode);
				return jsonString ;
			}else if(transCode.equals(SystemPara.P_QUERYTAXIDETAILINFO)){//查询出租车详细信息 GPSP010
				String cab = jsonObject.getString("cab");
				String userid = jsonObject.getString("userid");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				Taxi taxi = taxiDao.getTaxiByPlateNumber(cab);
				if(taxi==null){
					jsonString = getReturnErrorMessage(ErrorCode.NOT_FOUND_DATA);
				}else{
					//获取出租车实时位置信息
					UploadGPSData taxiGPSMap = TaxiDataService.getInstance().getTaxiGPSMap().get(cab);
					if(taxiGPSMap!=null){
						double lat = taxiGPSMap.getGpsdata().getLat();
						double lng = taxiGPSMap.getGpsdata().getLng();
						taxi.setLat(lat);
						taxi.setLng(lng);
						int status = taxiGPSMap.getStatus();
						// 出租车状态，空车，负载，未知
						taxi.setStatus(status);
					}
					jsonString = getReturnMessage(transCode,taxi);
				}
				return jsonString ;
			}else if(transCode.equals(SystemPara.P_UPDATE_PHONE_NUMBER)){
				String userid = jsonObject.getString("userid");
				if(!checkPassengerIsLogin(userid)){//验证是否登录
					jsonString = getReturnErrorMessage(ErrorCode.ACCOUNT_NOT_LOGIN);
					return jsonString;
				}
				String phone = jsonObject.getString("phone");
				passengerDao.doUpdatePassengerPhone(userid, phone);
				jsonString = getReturnMessage(transCode);
			}
			
			//根据交易编号获取返回信息
			jsonString = getReturnMessage(transCode);
		}
		
		return jsonString ;
	}
	
	
	public BlockingQueue<Passenger> getPassengerWorkQueue(){
		return passengerWorkQueue ;
	}
	
	/**
	 * 更新数据
	 * @param passenger
	 */
	public String updateData( Passenger passenger ){
		String TransCode = passenger.getTransCode() ;
		String res = null ;
		if( TransCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求 P003
			res = passengerDao.getSerialNum("p_user_id", 5, "false");
			passenger.setUserid(res);
            passengerDao.doSavePassenger(passenger);
		}else if( TransCode.equals(SystemPara.P_REGISTER) ){
			
		}
		return res ;
	}
	
	private boolean checkPassengerIsLogin(String account) {
        return passengerInfoMap.containsKey(account);
	}
	
	public List<Passenger> getModifiedInner24HoursData() {
		return this.passengerDao.getModifiedInner24HoursData();
	}
	
	public ConcurrentMap<String , Passenger> getPassengerLoginInfoMap(){
		return passengerInfoMap ;
	}
	
	public ConcurrentMap<String , GPSData> getRealtimeLocationMap(){
		return realtimeLocationMap;
	}
	
	
	public ConcurrentMap<String , List<UploadGPSData>> getPassengerTrackingMap(){
		return passengerTrackingMap ;
	}
	public PassengerDao getPassengerDao() {
		return passengerDao;
	}

	public void setPassengerDao(PassengerDao passengerDao) {
		this.passengerDao = passengerDao;
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

	public TaxiDao getTaxiDao() {
		return taxiDao;
	}

	public void setTaxiDao(TaxiDao taxiDao) {
		this.taxiDao = taxiDao;
	}

	public LoginRecordDao getLoginRecordDao() {
		return loginRecordDao;
	}

	public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
		this.loginRecordDao = loginRecordDao;
	}
	
	
}
