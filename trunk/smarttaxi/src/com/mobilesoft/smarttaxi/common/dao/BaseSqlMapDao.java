package com.mobilesoft.smarttaxi.common.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class BaseSqlMapDao extends SqlMapClientDaoSupport {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 生成异常描述信息
	 * 
	 * @param msg
	 * @param e
	 * @return
	 */
	protected String buildError(String msg, Exception e) {
		StringBuffer error = new StringBuffer();
		error.append(msg).append(":");
		if (e != null) {
			error.append(e.getMessage());
		}
		return error.toString();
	}

}
