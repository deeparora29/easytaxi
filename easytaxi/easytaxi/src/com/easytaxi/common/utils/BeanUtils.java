package com.easytaxi.common.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.common.exception.util.UtilException;

/**
 * Bean util
 * @author renmian
 *
 */
final class BeanUtils {
	private static Log logger = LogFactory.getLog(BeanUtils.class);

	public static Object getProperty(Object obj, String property) {
		try {
			return PropertyUtils.getProperty(obj, property);
		} catch (IllegalAccessException exp) {
			logger.error("BeanUtils.getProperty() Error: ", exp);
			return null;
		} catch (InvocationTargetException exp) {
			logger.error("BeanUtils.getProperty() Error: ", exp);
			return null;
		} catch (NoSuchMethodException exp) {
			logger.error("BeanUtils.getProperty() Error: ", exp);
			return null;
		}
	}

	public static String getPropertyString(Object obj, String property) {
		return String.valueOf(getProperty(obj, property));
	}

	public static void copyProperties(Object source, Object desc)
			throws UtilException {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(desc, source);
		} catch (IllegalAccessException exp) {
			logger.error("对象复制异常", exp);
			throw new UtilException("对象复制异常", exp);
		} catch (InvocationTargetException exp) {
			logger.error("对象复制异常", exp);
			throw new UtilException("对象复制异常", exp);
		}
	}
}
