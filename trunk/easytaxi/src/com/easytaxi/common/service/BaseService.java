package com.easytaxi.common.service;


import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.easytaxi.bo.CreditRecord;
import com.easytaxi.bo.GPSData;
import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
import com.easytaxi.request.bo.RequestInfo;
import com.easytaxi.request.bo.RequestResult;

public class BaseService {

	protected Log logger = LogFactory.getLog(getClass());

	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	/**
	 * 生成异常描述信息
	 * 
	 * @param msg
	 * @param e
	 * @return
	 */
	protected String buildError(String msg, Exception e) {
		StringBuffer error = new StringBuffer(500);
		error.append(msg).append(":");
		if (e != null) {
			error.append(e.getMessage());
		}
		return error.toString();
	}

	/**
	 * 
	 * @Description:
	 * 
	 * @param requestString
	 * @return JSONObject
	 * 
	 * @version: 2011-3-29 下午10:48:11
	 */
	public JSONObject parserRequest(String requestString) {
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(requestString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 
	 * @Description: 当处理失败时返回错误信息
	 * 
	 * @param errorCode
	 * @return String
	 * 
	 * @version: 2011-3-29 下午11:00:47
	 */
	public String getReturnErrorMessage(String errorCode) {
		String jsonString = "{ErrorCode:" + errorCode + "}";

		return jsonString;
	}

	public String getReturnMessage( Object ... args ) {
		String transCode = (String)args[0];
		StringBuffer jsonString = new StringBuffer("{");
		if( transCode.equals(SystemPara.P_REGISTER) ){//乘车注册
			String userId = (String)args[1];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userid:'"+userId+"'");
		}else if( transCode.equals(SystemPara.P_LOGIN) ){//乘客登录
			String userId = (String)args[1];
			String phone = (String)args[2];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userid:'"+userId+"'").append(",phone:'"+phone+"'");
		}else if( transCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求
			//requestNo:’201104062201’
			String requestNo = (String)args[1];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",requestNo:'"+requestNo+"'");
		}else if( transCode.equals(SystemPara.P_GETCONFIRM) ){//获取出租车响应
			String requestNo = (String)args[1];
			RequestResult resulst = (RequestResult)args[2];
			String cab = resulst.getTaxi().getCab();
			float credit = resulst.getTaxi().getCredit();
			UploadGPSData taxiGPSData = (UploadGPSData)args[3];
			String cabGPS = "{lat:"+taxiGPSData.getGpsdata().getLat()+",lng:"+taxiGPSData.getGpsdata().getLng()+"}";
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",requestNo:'"+requestNo+"'")
			.append(",cab:'"+cab+"'").append(",credit:'"+credit+"'").append(",cabGPS:'"+cabGPS+"'");
        }else if (transCode.equals(SystemPara.P_CANCELREQUEST)) {// 取消用车请求
        	jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'");
		}else if( transCode.equals(SystemPara.P_CREDITRATING) ){//信用评价
			
		}else if( transCode.equals(SystemPara.P_QUERYCREDIT) ){//查询Taxi信誉度
			List<CreditRecord> list = (List<CreditRecord>)args[1];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'");
			if(list!=null){
				jsonString.append(",credits:[");
				for (int i = 0; i < list.size(); i++) {
					CreditRecord creditRecord = list.get(i);
					float credit = creditRecord.getCredit();
					String comments = creditRecord.getComments();
					String cab = creditRecord.getCreditUserid();
					Date creditTime = creditRecord.getCreditTime();
					if(i!=list.size()-1){
						jsonString.append("{credit:'"+credit+"',comments:'"+comments+"',cab:'"+cab+"',creditTime:'"+creditTime+"'},");
					}else{
						jsonString.append("{credit:'"+credit+"',comments:'"+comments+"',cab:'"+cab+"',creditTime:'"+creditTime+"'}");
					}
				}
			}
		}else if( transCode.equals(SystemPara.P_QUERYTAXIGPS) ){//查询Taxi GPS
			
		}else if( transCode.equals(SystemPara.P_UPLOADGPS_TRACK) ){//上传GPS数据或经过路线
			
		}else if( transCode.equals(SystemPara.P_QUERYTAXIDETAILINFO) ){//查询出租车详细信息
			
		}else if( transCode.equals(SystemPara.T_REGISTER) ){//出租车注册
			String userId = (String)args[1];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userid:'"+userId+"'");
		}else if( transCode.equals(SystemPara.T_LOGIN) ){//出租车登录
			String userId = (String)args[1];
			String phone = (String)args[2];
			String [] phoneArray = phone.split(",");
			if(phoneArray.length>1){
				phone = "['"+phoneArray[0]+"','"+phoneArray[1]+"']";
			}
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userid:'"+userId+"'").append(",phone:'"+phone+"'");
		}else if(transCode.equals(SystemPara.T_CONFIRM_CALL)){//Confirm Call
			String requestNo = (String)args[1];
			String userId = (String)args[2];
			RequestResult resulst = (RequestResult)args[3];
			String nickname = resulst.getPassenger().getNickname();
			String phone = resulst.getPassenger().getPhone();
			float credit = resulst.getPassenger().getCredit();
			double lat = resulst.getPassenger().getGpsdata().getLat();
			double lng = resulst.getPassenger().getGpsdata().getLng();
			String cabGPS = "{lat:'"+lat+"',lng:'"+lng+"'}";
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",requestNo:'"+requestNo+"'")
			.append(",userId:'"+userId+"'").append(",nickname:'"+nickname+"'").append(",phone:'"+phone+"'")
			.append("credit:'"+credit+"'").append(",cabGPS:'"+cabGPS+"'");
		}else if(transCode.equals(SystemPara.T_CANCEL_CALL)){//Cancel Call T005
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'");
		}else if(transCode.equals(SystemPara.T_CREDIT_RATING)){//Credit Rating T006
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'");
		}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_CREDIT)){//Query Passenger’s Credit  T007
			List<CreditRecord> list = (List<CreditRecord>)args[1];
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'");
			if(list!=null){
				jsonString.append(",credits:[");
				for (int i = 0; i < list.size(); i++) {
					CreditRecord creditRecord = list.get(i);
					float credit = creditRecord.getCredit();
					String comments = creditRecord.getComments();
					String cab = creditRecord.getCreditUserid();
					Date creditTime = creditRecord.getCreditTime();
					if(i!=list.size()-1){
						jsonString.append("{credit:'"+credit+"',comments:'"+comments+"',cab:'"+cab+"',creditTime:'"+creditTime+"'},");
					}else{
						jsonString.append("{credit:'"+credit+"',comments:'"+comments+"',cab:'"+cab+"',creditTime:'"+creditTime+"'}");
					}
				}
				jsonString.append("]");
			}
		}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_LOCATION)){//T008---Query Passenger Real-time location
			GPSData data = (GPSData)args[1];
			String userGPS = "{lat:'"+data.getLat()+"',lng:'"+data.getLng()+"'}";
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userGPS:'"+userGPS+"'");
		}else if(transCode.equals(SystemPara.T_QUERY_CALL_INFO)){//Query Detail Taxi Call Info
			RequestInfo info = (RequestInfo)args[1];
			String requestNo = info.getRequestNo();
			String userid = info.getUserid();
			String phone = info.getPhone();
			String userGPS = "{lat:'"+info.getStartLat()+"',lng:'"+info.getStartLong()+"'}";
			int number = info.getNumber();
			int luggage = info.getLuggage();
			String comments = info.getComments();
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",requestNo:'"+requestNo+"'")
			.append(",userid:'"+userid+"'").append(",phone:'"+phone+"'").append(",userGPS:'"+userGPS+"'")
			.append(",number:'"+number+"'").append(",luggage:'"+luggage+"'").append(",comments:'"+comments+"'");
		}else if(transCode.equals(SystemPara.T_QUERY_PASSENGER_INFO)){//Query Detail Passenger Info T010
			Passenger p = (Passenger)args[1];
			RequestInfo info = (RequestInfo)args[2];
			String userid = p.getUserid();
			String firstname = p.getFirstname();
			String lastname = p.getLastname();
			float credit = p.getCredit();
			String userGPS = "{lat:'"+info.getStartLat()+"',lng:'"+info.getStartLong()+"'}";
			String nickname = p.getNickname();
			String gender = p.getGender();
			String province = p.getProvince();
			String city = p.getCity();
			String descr = p.getDescr();
			jsonString.append("ErrorCode:'"+ErrorCode.SUCCESS+"'").append(",userid:'"+userid+"'")
			.append(",firstname:'"+firstname+"'").append(",lastname:'"+lastname+"'").append(",credit:'"+credit+"'")
			.append(",userGPS:'"+userGPS+"'").append(",nickname:'"+nickname+"'").append(",gender:'"+gender+"'")
			.append(",province:'"+province+"'").append(",city:'"+city+"'").append(",descr:'"+descr+"'");
		}
		
		
		
		jsonString.append("}");
		return jsonString.toString();
	}
	
	protected String convertNullToEmpty(String s){
		return s == null ? "" : s ;
	}
	
}
