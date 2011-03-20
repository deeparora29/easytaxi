package com.easytaxi.common.security;

import java.net.URLEncoder;
/**
 * 页面定义
 * @author renmian
 *
 */
public class LoginPagesCfg {

	  final public static String NXHomePage           = "/cscqm/index.jsp";
	  final public static String LoginPage          = "/cscqm/login.jsp";
	  final public static String LoginFailedPage    = "/cscqm/login.jsp";
	  final public static String AccountExpiredPage = "/cscqm/common/lgnerrinfo.jsp?err=1";
	  final public static String LoginExceptionPage = "/cscqm/common/lgnerrinfo.jsp?err=2";
	  final public static String RetryLoginPage     = "/cscqm/common/lgnerrinfo.jsp?err=3";
	  final public static String AccountLockedPage     = "/cscqm/common/lgnerrinfo.jsp?err=4";
	  final public static String SessionExpiredPage = "/cscqm/SessionOverTime.jsp";
	  final public static String UnauthorizedPage  = "/cscqm/common/unauthorized.jsp?err=unrole";
	  final public static String SecondLoginPage = "/cscqm/LoginAction.action";
	 
	  private LoginPagesCfg(){
	  }

	  static public String getHomePage(){
	    return getInstance().NXHomePage;
	  }

	  static public String getEntryPage(String AlterMsg){
	    if( (AlterMsg != null) && (AlterMsg.length() > 0 ) ){
	      try{
	        return getInstance().LoginPage + "?err=" + URLEncoder.encode(AlterMsg, "UTF-8");
	      }catch(Exception e){
	        return getInstance().LoginPage;
	      }
	    }else{
	      return getInstance().LoginPage;
	    }
	  }

	  static public String getEntryPage(){
	    return getEntryPage(null);
	  }

	  static public String getAccountExpiredPage(){
	    return getInstance().AccountExpiredPage;
	  }

	  static public String getLoginBlockedPage(){
	    try{
	      return getInstance().LoginFailedPage + "?err=" +
	          URLEncoder.encode("登录失败", "UTF-8");
	    }catch(Exception e){
	      return getInstance().LoginFailedPage 
	      + "?err=Please-Check-UserName-Or-Password.";
	    }
	  }

	  static public String getLoginErrorPage(){
	    return getInstance().LoginExceptionPage;
	  }

	  static public String getSessionExpiredPage(){
	    return getInstance().SessionExpiredPage;
	  }

	  static public String getRetryLoginPage(String Msg){
	    try{
	      return getInstance().RetryLoginPage + "&m=" + URLEncoder.encode(Msg, "UTF-8");
	    }catch(Exception e){
	      return getInstance().RetryLoginPage + "&m=Please-try-again";
	    }
	  }

	  static public String getRetryLoginPage(){
	    return getRetryLoginPage(null);
	  }

	  static public String getUnauthorizedPage(){
	    return getInstance().UnauthorizedPage;
	  }

	  public static LoginPagesCfg getInstance(){
	    if ( null == singleton ) {
	      singleton = new LoginPagesCfg();
	    }
	    return singleton;
	  }
	  
	  public static String getAccountLockedPage(){
	    return getInstance().AccountLockedPage;
	  }
	  
	  private static LoginPagesCfg singleton = null;

	public static String getSecondLoginPage() {
		return getInstance().SecondLoginPage;
	}

	}