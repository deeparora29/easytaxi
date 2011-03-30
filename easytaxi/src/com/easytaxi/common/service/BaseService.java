package com.easytaxi.common.service;


import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import com.easytaxi.common.SystemPara;

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

	public String getReturnMessage(String transCode) {
		StringBuffer jsonString = new StringBuffer("{");
		
		if( transCode.equals(SystemPara.P_REGISTER) ){//乘车注册
			
		}else if( transCode.equals(SystemPara.P_LOGIN) ){//乘客登录
			
		}else if( transCode.equals(SystemPara.P_REQUESTTAXI) ){//发布用车请求
			
		}else if( transCode.equals(SystemPara.P_GETCONFIRM) ){//获取出租车响应
			
		}else if( transCode.equals(SystemPara.P_ANCELREQUEST) ){//取消用车请求
			
		}else if( transCode.equals(SystemPara.P_CREDITRATING) ){//信用评价
			
		}else if( transCode.equals(SystemPara.P_QUERYCREDIT) ){//查询Taxi信誉度
			
		}else if( transCode.equals(SystemPara.P_QUERYTAXIGPS) ){//查询Taxi GPS
			
		}else if( transCode.equals(SystemPara.P_UPLOADGPS_TRACK) ){//上传GPS数据或经过路线
			
		}else if( transCode.equals(SystemPara.P_QUERYTAXIDETAILINFO) ){//查询出租车详细信息
			
		}
		
		jsonString.append("");
		return jsonString.toString();
	}
}
