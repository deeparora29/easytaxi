/**
 * 
 */
package com.btit.cuncu.test;

import com.btit.cuncu.test.AppContext;

/**
 * Bean工具类
 * @author yuxd
 *
 */
public class BeanUtil {
	/**
	 * 得到Bean实例
	 * @param beanClass
	 * @return
	 */
	public static Object getBean(Class beanClass){
		return AppContext.getBean(beanClass);
	}
	
	
}
