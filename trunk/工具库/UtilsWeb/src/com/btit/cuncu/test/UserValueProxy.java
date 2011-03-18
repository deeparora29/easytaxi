package com.btit.cuncu.test;

import com.btit.cuncu.test.UserValue;



/**
 * 用户信息类代理
 * @author yuxd
 * @since 2008-7-12
 * @version 2.0
 *
 */
public class UserValueProxy {
	/**
	 * 用于保存UserValue对象
	 */
	private static final ThreadLocal<UserValue> userValueLocal = new ThreadLocal<UserValue>();
	/**
	 * 获得UserValue对象
	 * @return
	 */
	public static UserValue getUserValue(){
		return (UserValue)userValueLocal.get();
	}
	public static void setUserValue(UserValue userValue){
		userValueLocal.set(userValue);
	}
}