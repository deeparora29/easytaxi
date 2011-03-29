package com.easytaxi.common.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.easytaxi.common.SystemPara;
import com.easytaxi.common.utils.JsonUtil;

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
		//乘车注册
		if( transCode.equals(SystemPara.P_REGISTER) ){
			
		}
		
		jsonString.append("");
		return jsonString.toString();
	}
}
