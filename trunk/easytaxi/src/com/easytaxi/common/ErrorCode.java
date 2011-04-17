package com.easytaxi.common;

/**
 * 
 * 定义系统操作结果代码
 *
 */
public class ErrorCode {
	
	//操作成功
	public static final String SUCCESS ="0000";
	
	//客户端参数请求错误或解析请求数据失败
	public static final String PARA_ERROR_CODE ="1000";
	
	//交易编码无效
	public static final String TRANS_CODE_ERROR ="1001";
	
	//解析地址失败或地址无效
	public static final String LOCATION_PARSER_ERROR ="1002";
	
	//登录失败
	public static final String LOGIN_ERROR ="2000";
	
	//注册失败，用户已存在
	public static final String REGISTER_ERROR ="2001";
	
	//登录失败，用户不存在
	public static final String USER_NOT_FOUND = "2002";
	
	//登录失败，用户密码不正确
	public static final String PASSWORD_NOT_ACCURATE = "2003";

	//用户没有登录
	public static final String ACCOUNT_NOT_LOGIN = "2004";
	
	//用户已登录
	public static final String ACCOUNT_HAS_LOGIN = "2005";
	
	//操作错误（如乘客发布用车消息后司机确定后取消用车等）
    // 服务器端异常
    public final static String OPERATE_SERVERERROR = "3000";

    // 用车请求尚未响应
    public final static String REQUEST_ISVALID = "4000";
    // 用车请求已经被响应
    public final static String REQUEST_CONFIRMED = "4001";
    // 用车请求已经被取消
    public final static String REQUEST_CONCELED = "4002";
    // 用车请求过期
    public final static String REQEST_OUTOFDATE = "4003";
    // 用车请求不存在
    public final static String REQUEST_NOTEXIST = "4004";
	
	//查询时没有找到相关数据
    public final static String NOT_FOUND_DATA = "5000";
}
