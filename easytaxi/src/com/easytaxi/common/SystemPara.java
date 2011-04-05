package com.easytaxi.common;

/**
 * 
 * @Package com.easytaxi.common
 *
 * @Title: SystemPara.java 
 *	
 * @Description: 系统参数
 *
 * @Copyright:   Copyright (c)2010
 * 
 * @author:      付奎
 *
 * @date 2011-3-29 下午10:54:47
 *
 * @version V1.0   
 *
 */
public class SystemPara {
	
	//乘车注册
	public static final String P_REGISTER = "P001";
	
	//乘客登录
	public static final String P_LOGIN = "P002";
	
	//发布用车请求
	public static final String P_REQUESTTAXI = "P003";
	
	//获取出租车响应
	public static final String P_GETCONFIRM = "P004";
	
	//取消用车请求
    public static final String P_CANCELREQUEST = "P005";
	
	//信用评价
	public static final String P_CREDITRATING = "P006";
	
	//查询Taxi信誉度
	public static final String P_QUERYCREDIT = "P007";
	
	//查询Taxi GPS
	public static final String P_QUERYTAXIGPS = "P008";
	
	//上传GPS数据或经过路线
	public static final String P_UPLOADGPS_TRACK = "P009";
	
	//查询出租车详细信息
	public static final String P_QUERYTAXIDETAILINFO = "P010";
	
    // taxi status
    private final static String[] TAXI_STATUS = { "Empty", "Hired", "Sharing" };

    public static String getTaxiStatus(int status) {
        return (status >= 0 && status < 3) ? TAXI_STATUS[status] : "Unknown";
    }

    // User type
    private final static String[] USER_TYPE = { "taxi", "passenger" };

    public static String getUserType(int type) {
        return (type >= 0 && type < 2) ? USER_TYPE[type] : "Unknown";
    }
	
}
