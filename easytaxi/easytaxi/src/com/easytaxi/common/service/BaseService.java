package com.easytaxi.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
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

}
