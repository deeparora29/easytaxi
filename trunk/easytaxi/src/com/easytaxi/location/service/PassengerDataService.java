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

import com.easytaxi.bo.GPSData;
import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;
import com.easytaxi.request.bo.RequestInfo;
import com.easytaxi.request.bo.RequestResult;
import com.easytaxi.request.service.CallTaxiServie;
import com.easytaxi.usermgr.dao.PassengerDao;

public class PassengerDataService extends BaseService{
	

	Log log = LogFactory.getLog(PassengerDataService.class);
	
	private static PassengerDataService instance = new PassengerDataService ();
	
	//存放乘车信息队列
	private static BlockingQueue<Passenger> passengerWorkQueue = new LinkedBlockingQueue<Passenger>();
	
	//存放乘客信息
	private static ConcurrentMap<String , Passenger> passengerInfoMap = new ConcurrentHashMap<String, Passenger>();
	
	//存放乘客广播打车消息
	private static ConcurrentMap<String , Passenger> broadcastCallTaxiMap = new ConcurrentHashMap<String, Passenger>();
	
	//保存乘客上传GPS数据或经过路线
	private static ConcurrentMap<String , List<UploadGPSData>> passengerTrackingMap = new ConcurrentHashMap<String, List<UploadGPSData> >();
	
	private PassengerDao passengerDao ;
	
	private CallTaxiServie callTaxiServie ;
	
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
			jsonString = getReturnErrorMessage(ErrorCode.PARA_ERROR_CODE);
		}else{
			String transCode = jsonObject.getString("TransCode");
			if(transCode==null||transCode.length()<4){
				jsonString = getReturnErrorMessage(ErrorCode.TRANS_CODE_ERROR);
				return jsonString ;
			}else if(transCode.equals(SystemPara.P_REGISTER)){//乘车注册
				//获取用户信息
				String firstname = jsonObject.getString("firstname");
				String lastname = jsonObject.getString("lastname");
				String password = jsonObject.getString("password");
				String phone = jsonObject.getString("phone");
				String email = jsonObject.getString("email");
				String nickName = jsonObject.getString("nickName");
				String gender = jsonObject.getString("gender");
				String descr = jsonObject.getString("descr");
				//没有重复的用户，email和phone都可以作为账号
				if( !passengerInfoMap.containsKey(phone) && !passengerInfoMap.containsKey(email) ){
					Passenger p = new Passenger(transCode ,firstname, lastname, password, phone, email, nickName, gender, descr);
					//由于需要立即返回userid，则不能异步处理
					String userId = updateData( p );
					return getReturnMessage( transCode , userId );
				}else{
					//账号重复
					jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					return jsonString ;
				}
			}else if(transCode.equals(SystemPara.P_REGISTER)){//Login
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
					//TODO 更新内存中乘客信息
					
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
				
				RequestInfo requestInfo = new RequestInfo(userid,phone,start_long,start_lat,start_text,end_long,
						end_lat,end_text,number,luggage,comments,share);
				String res = callTaxiServie.requestTaxi(requestInfo);
				if("".equals(res)){
					jsonString = getReturnErrorMessage(ErrorCode.REQUEST_ISVALID);
				}else{
					jsonString = getReturnMessage(transCode,res);
				}
			}else if(transCode.equals(SystemPara.P_GETCONFIRM)){//获取出租车响应 P004
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				//从内存中获取出租车实时GPS数据
				UploadGPSData taxiGPSData = TaxiDataService.getInstance().getTaxiInfoMap().get( userid );
				RequestResult resulst = callTaxiServie.getConfirmedTaxiInfo(userid, requestNo);
				if(resulst.getErrorCode().equals(ErrorCode.SUCCESS)){
					jsonString = getReturnMessage(transCode,requestNo,resulst,taxiGPSData);
				}else{
					jsonString = getReturnErrorMessage(resulst.getErrorCode());
				}
			}else if(transCode.equals(SystemPara.P_CANCELREQUEST)){//取消用车请求 P005
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				String comments = jsonObject.getString("comments");
				callTaxiServie.cancelRequest(userid, requestNo, comments);
			}else if(transCode.equals(SystemPara.P_CREDITRATING)){//信用评价P006
				String userid = jsonObject.getString("userid");
				String requestNo = jsonObject.getString("requestNo");
				float credit = Float.valueOf(jsonObject.getString("credit"));
				String comments = jsonObject.getString("comments");
				//TODO callTaxiServie.
			}else if(transCode.equals(SystemPara.P_QUERYCREDIT)){//查询Taxi信誉度P007
				String userid = jsonObject.getString("userid");
				String cab = jsonObject.getString("cab");
				String number = jsonObject.getString("number");
				//TODO callTaxiServie.
			}else if(transCode.equals(SystemPara.P_QUERYTAXIGPS)){//查询Taxi GPSP008
				
				//TODO 
				
			}else if(transCode.equals(SystemPara.P_UPLOADGPS_TRACK)){//上传GPS数据或经过路线
				String userid = jsonObject.getString("userid");
				String userGPS = jsonObject.getString("userGPS");
				GPSData destLocation = (GPSData)JsonUtil.getObjectByJsonString(userGPS, GPSData.class);
				List<UploadGPSData>passengerTrackingList = null;
				UploadGPSData data = new UploadGPSData();
				data.setUserId(userid);
				data.setDestLocation(destLocation);
				if( passengerTrackingMap.containsKey(userid) ){
					passengerTrackingList = passengerTrackingMap.get(userid);
					passengerTrackingList.add( data );
				}else{
					passengerTrackingList = new ArrayList<UploadGPSData>();
					passengerTrackingList.add( data );
					passengerTrackingMap.put(userid , passengerTrackingList);
				}
			}else if(transCode.equals(SystemPara.P_QUERYTAXIDETAILINFO)){//查询出租车详细信息 GPSP010
				//TODO 
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
			res = getSerialNum("p_user_id", 5, "false");
			passenger.setUserid(res);
            passengerDao.doSavePassenger(passenger);
		}else if( TransCode.equals(SystemPara.P_REGISTER) ){
			
		}
		return res ;
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
}
