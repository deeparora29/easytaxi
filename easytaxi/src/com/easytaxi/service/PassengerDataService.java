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
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.JsonUtil;
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
	
	private PassengerDao passengerDao ;
	

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
					//passengerWorkQueue.offer( p );
					String userId = updateData( p );
					return getReturnMessage( transCode , userId );
				}else{
					//账号重复
					jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					return jsonString ;
				}
			}else if(transCode.equals(SystemPara.P_REGISTER)){//Login
				String account = jsonObject.getString("account");
				Passenger p = passengerDao.getPassengerByPhone(account);
				if(p!=null){
					String userid = p.getUserid();
					String phone = p.getPhone();
					jsonString = getReturnMessage( transCode , userid , phone ); 
				}else{
					p = passengerDao.getPassengerByEmail(account);
					if(p==null){
						jsonString = getReturnErrorMessage(ErrorCode.REGISTER_ERROR);
					}else{
						String userid = p.getUserid();
						String phone = p.getPhone();
						jsonString = getReturnMessage( transCode , userid , phone ); 
					}
				}
				return jsonString ;
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
	
	/**
	 * 更新数据
	 * @param passenger
	 */
	public String updateData( Passenger passenger ){
		String TransCode = passenger.getTransCode() ;
		String res = null ;
		if( TransCode.equals(SystemPara.P_REGISTER) ){
			res = getSerialNum("p_user_id", 5, "false");
			passenger.setUserid(res);
			passengerDao.register( passenger );
		}
		return res ;
	}
	
	
	public PassengerDao getPassengerDao() {
		return passengerDao;
	}

	public void setPassengerDao(PassengerDao passengerDao) {
		this.passengerDao = passengerDao;
	}
}
