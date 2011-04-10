package com.easytaxi.common.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.ErrorCode;
import com.easytaxi.common.SystemPara;
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
	* @Description: 获取流水号
	* @param serialType 流水号类型 t_user_id:出租车id , p_user_id:乘客id ,request_no : 请求编号 ,track_id:行迹编号  
	* @param len  流水号长度
	* @param showDate 流水号是否带日期
	* @return SerialNum ， 流水号生成失败返回 -1
	* <p><blockquote>
	*  e.g.  getSerialNum( "t_user_id" , 13 , "true");
	*  		 其中：t_user_id与et_sys_var表中field_name对应，若无此记录则不能获取正确的流水号
	*       13 为流水号长度
	*       true表示需要添加日期
	*       return 2011040401056     
	* </blockquote>
	* <p>	
	*/
	public  String getSerialNum(final String serialType , final int len , final String showDate ) {
		String serialNum = (String) jdbcTemplate.execute(
			new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					String storedProc = "{call get_serial_num(?,?,?,?)}";
					CallableStatement cs = con.prepareCall(storedProc);
					cs.setString(1, serialType );
					cs.setInt(2, len);
					cs.setString(3, showDate );
					//cs.setInt(4, 15);
					cs.registerOutParameter(4, Types.VARCHAR);
					return cs;
				}
			}, new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)throws SQLException, DataAccessException {
					cs.execute();
					return cs.getString("s_value");
				}
		});

		return serialNum;
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
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",userid:"+userId+"");
		}else if( transCode.equals(SystemPara.P_LOGIN) ){//乘客登录
			String userId = (String)args[1];
			String phone = (String)args[2];
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",userid:"+userId+"").append(",phone:"+phone+"");
		}else if( transCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求
			//requestNo:’201104062201’
			String requestNo = (String)args[1];
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",requestNo:"+requestNo+"");
		}else if( transCode.equals(SystemPara.P_GETCONFIRM) ){//获取出租车响应
			String requestNo = (String)args[1];
			RequestResult resulst = (RequestResult)args[2];
			String cab = resulst.getTaxi().getCab();
			float credit = resulst.getTaxi().getCredit();
			UploadGPSData taxiGPSData = (UploadGPSData)args[3];
			String cabGPS = "{lat:"+taxiGPSData.getGpsdata().getLat()+",lng:"+taxiGPSData.getGpsdata().getLng()+"}";
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",requestNo:"+requestNo+"")
			.append(",cab:"+cab+"").append(",credit:"+credit+"").append(",cabGPS:"+cabGPS+"");
        }else if (transCode.equals(SystemPara.P_CANCELREQUEST)) {// 取消用车请求
			
		}else if( transCode.equals(SystemPara.P_CREDITRATING) ){//信用评价
			
		}else if( transCode.equals(SystemPara.P_QUERYCREDIT) ){//查询Taxi信誉度
			
		}else if( transCode.equals(SystemPara.P_QUERYTAXIGPS) ){//查询Taxi GPS
			
		}else if( transCode.equals(SystemPara.P_UPLOADGPS_TRACK) ){//上传GPS数据或经过路线
			
		}else if( transCode.equals(SystemPara.P_QUERYTAXIDETAILINFO) ){//查询出租车详细信息
			
		}else if( transCode.equals(SystemPara.T_REGISTER) ){//出租车注册
			String userId = (String)args[1];
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",userid:"+userId+"");
		}else if( transCode.equals(SystemPara.T_LOGIN) ){//出租车登录
			String userId = (String)args[1];
			String phone = (String)args[2];
			String [] phoneArray = phone.split(",");
			if(phoneArray.length>1){
				phone = "['"+phoneArray[0]+"','"+phoneArray[1]+"']";
			}
			jsonString.append("ErrorCode:"+ErrorCode.SUCCESS+"").append(",userid:"+userId+"").append(",phone:"+phone+"");
		}
		
		jsonString.append("}");
		return jsonString.toString();
	}
	
	protected String convertNullToEmpty(String s){
		return s == null ? "" : s ;
	}
	
}
