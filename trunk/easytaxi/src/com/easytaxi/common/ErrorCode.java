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
	
	//操作错误（如乘客发布用车消息后司机确定后取消用车等）
	public static final String OPERATE_ERROR ="3000";
	
	
}
