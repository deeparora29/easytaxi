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
	
	//更新手机号码
	public static final String P_UPDATE_PHONE_NUMBER = "P011";
	
	
	//Taxi Register
	public static final String T_REGISTER = "T001";
	
	//Taxi Login
	public static final String T_LOGIN = "T002";
	
	//上传出租车GPS数据
	public static final String T_UPLOADGPS = "T003";
	
	//Confirm Call
	public static final String T_CONFIRM_CALL = "T004";
	
	//Cancel Call
	public static final String T_CANCEL_CALL = "T005";
	
	//Credit Rating
	public static final String T_CREDIT_RATING = "T006";
	
	//Query Passenger’s Credit 
	public static final String T_QUERY_PASSENGER_CREDIT = "T007";
	
	//Query Passenger Real-time location
	public static final String T_QUERY_PASSENGER_LOCATION = "T008";
	
	//Query Detail Taxi Call Info
	public static final String T_QUERY_CALL_INFO = "T009";
	
	//Query Detail Passenger Info
	public static final String T_QUERY_PASSENGER_INFO = "T010";
	
	//Update Taxi phone
	public static final String T_UPDATE_TAXI_PHONE = "T011";
	
	// Get valid passenger’s call
	public static final String T_VALID_PASSENGER_CALL = "T012";
	
    // taxi status
    public final static int TAXI_STATUS_EMPTY = 0;
    
    public final static int TAXI_STATUS_HIRED = 1;
    
    public final static int TAXI_STATUS_SHARING = 2;

    private final static String[] TAXI_STATUS = { "Empty", "Hired", "Sharing" };

    public static String getTaxiStatus(int status) {
        return (status >= 0 && status < 3) ? TAXI_STATUS[status] : "Unknown";
    }

    // User type
    private final static String[] USER_TYPE = { "taxi", "passenger" };

    public static String getUserType(int type) {
        return (type >= 0 && type < 2) ? USER_TYPE[type] : "Unknown";
    }

    public static int getUserTypeByUserid(String userid) {
        int type = userid.indexOf("T");
        if (type < 0)
            type = 1;
        return type;
    }
	
    public static final String GOOLGE_MAP_GECODE_JSON = "http://maps.google.com/maps/api/geocode/json";
    // requestinfo status
    public final static int REQUESTINFO_STATUS_ISVALID = 0;
    public final static int REQUESTINFO_STATUS_CONFIRMED = 1;
    public final static int REQUESTINFO_STATUS_CANCELED = 2;

}
