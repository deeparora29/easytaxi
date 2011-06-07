package com.mobilesoft.smarttaxi.common.utils;

import org.apache.commons.lang.StringUtils;

public class SqlUtil {
	
	/**
	 * 模糊匹配前几位'?%'
	 * @param column
	 * @param value
	 * @return
	 */
	public static String appendLike(String column, String value) {
		StringBuffer strBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(value)) {
			strBuffer.append(" AND ");
			strBuffer.append(column).append(" LIKE '");
			strBuffer.append(value.trim());
			strBuffer.append("%'");
		}
		return strBuffer.toString();
	}
	/**
	 * 全模糊匹配 like '%?%'
	 * @param column
	 * @param value
	 * @return
	 */
	public static String appeandLikeAnywhere(String column, String value) {
		StringBuffer strBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(value)) {
			strBuffer.append(" AND ");
			strBuffer.append(column).append(" LIKE '%");
			strBuffer.append(value.trim());
			strBuffer.append("%'");
		}
		return strBuffer.toString();
	}
	/**
	 * 相等
	 * @param column
	 * @param value
	 */
	public static String appendEqual(String column,String value) {
		StringBuffer strBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(value)) {
			strBuffer.append(" AND ");
			strBuffer.append(column).append("='");
			strBuffer.append(value.trim());
			strBuffer.append("'");
		}
		return strBuffer.toString();
	}
	/**
	 * 时间区间
	 * @param column
	 * @param startTime
	 * @param endTime
	 */
	public static String appendDateBetween(String column, String startTime, String endTime){
		StringBuffer strBuffer = new StringBuffer();
		if(StringUtils.isNotBlank(startTime)){
			strBuffer.append(" AND ");
			strBuffer.append(column).append(" between to_date('");
			strBuffer.append(startTime.trim());
			strBuffer.append("','yyyy-MM-dd hh24:mi:ss')");
		}else
			return "";
		if(StringUtils.isNotBlank(endTime)){
			strBuffer.append(" and to_date('");
			strBuffer.append(endTime.trim());
			strBuffer.append("','yyyy-MM-dd hh24:mi:ss')");
		}else{
			strBuffer.append(" and sysdate");
		}
		return strBuffer.toString();
	}
	
	public static String appendSqlString(String sqlString){
		StringBuffer strBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(sqlString)) {
			strBuffer.append(" AND ");
			strBuffer.append(sqlString);
		}
		return strBuffer.toString();
	}
}

