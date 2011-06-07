package com.mobilesoft.smarttaxi.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mobilesoft.smarttaxi.common.exception.util.ConvertException;



/**
 * Utility class to convert one object to another.
 * @author renmian
 *
 */
public final class ConvertUtil {
	// ~ Static fields/initializers
	// =============================================

	private static Log log = LogFactory.getLog(ConvertUtil.class);

	// ~ Methods
	// ================================================================

	/**
	 * Method to convert a ResourceBundle to a Map object.
	 * 
	 * @param rb
	 *            a given resource bundle
	 * @return Map a populated map
	 */
	public static Map convertBundleToMap(ResourceBundle rb) {
		Map<String, String> map = new HashMap<String, String>();

		for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			map.put(key, rb.getString(key));
		}

		return map;
	}

	public static List<Object> convertSetToList(Set<Object> set) {
		List<Object> list = new ArrayList<Object>();
		if (set == null || set.isEmpty()) {
			return list;
		}
		list.addAll(set);
		return list;
	}

	public static Set<Object> convertListToSet(List<Object> list) {
		Set<Object> set = new HashSet<Object>();
		if (list == null || list.isEmpty()) {
			return set;
		}
		set.addAll(list);
		return set;
	}

	public static Map convertListToMap(List list) {
		Map map = new LinkedHashMap();
		return map;
	}

	/**
	 * Method to convert a ResourceBundle to a Properties object.
	 * 
	 * @param rb
	 *            a given resource bundle
	 * @return Properties a populated properties object
	 */
	public static Properties convertBundleToProperties(ResourceBundle rb) {
		Properties props = new Properties();

		for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			props.put(key, rb.getString(key));
		}

		return props;
	}

	/**
	 * Convenience method used by tests to populate an object from a
	 * ResourceBundle
	 * 
	 * @param obj
	 *            an initialized object
	 * @param rb
	 *            a resource bundle
	 * @return a populated object
	 */
	public static Object populateObject(Object obj, ResourceBundle rb) {
		try {
			Map map = convertBundleToMap(rb);

			BeanUtils.copyProperties(obj, map);
		} catch (Exception e) {
			log.error("Exception occured populating object: " + e.getMessage());
		}

		return obj;
	}

	public static Object convert(Object src, Object target)
			throws ConvertException {
		if (src == null) {
			return null;
		}
		try {
			BeanUtils.copyProperties(target, src);
			return target;
		} catch (IllegalAccessException e) {
			throw new ConvertException("Bean对象复制异常", e);
		} catch (InvocationTargetException e) {
			throw new ConvertException("Bean对象复制异常", e);
		}
	}

	public static Object convertOmit(Object src, Object dest, String[] omit)
			throws ConvertException {
		try {
			return BeanUtilsProxy.getInstance().convertOmit(dest, src, omit);
		} catch (IllegalAccessException e) {
			throw new ConvertException("Bean对象复制异常", e);
		} catch (InvocationTargetException e) {
			throw new ConvertException("Bean对象复制异常", e);
		}

	}

	/**
	 * Convenience method to convert Lists of objects to a list of objects in
	 * different class. Also checks for and formats dates.
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static List convert(List list, Class classObj)
			throws ConvertException {
		if (list == null) {
			return null;
		}
		try {
			List<Object> targetList = new ArrayList<Object>(list.size());
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				Object target = classObj.newInstance();
				convert(o, target);
				targetList.add(target);
			}
			return targetList;
		} catch (InstantiationException e) {
			throw new ConvertException("Bean对象复制异常", e);
		} catch (IllegalAccessException e) {
			throw new ConvertException("Bean对象复制异常", e);
		}
	}

	/**
	 * 查询指定bean的属性
	 * 
	 * @param obj
	 * @param property
	 * @return
	 */
	public static Object getProperty(Object obj, String property) {
        return com.mobilesoft.smarttaxi.common.utils.BeanUtils.getProperty(obj, property);
	}

	public static String processException(Exception e) throws IOException {
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));

		StringBuffer buffer = new StringBuffer();
		BufferedReader in = new BufferedReader(new StringReader(out.toString()));
		while (true) {
			String line = in.readLine();
			if (line == null)
				break;
			buffer.append(line);
			buffer.append("<br>");
			if (buffer.length() > 3900)
				break;
		}
		return buffer.toString();
	}

	/**
	 * 获取月表
	 * 参数dateTime为null或是空字符串的时候，缺省就认为选定当前时间
	 * @param dateTime
	 *            format "yyyy-MM-dd"
	 * @return
	 */
	public static String getQueryTableDate(String dateTime) {
		String tableDate = getQueryTableDate(dateTime, "yyyyMM");
		// return ("CCT_WF_MANAGE_" + tableDate);
		return tableDate;
	}
/**
 * 获取dateFormat表
 * 参数dateTime为null或是空字符串的时候，缺省就认为选定当前时间
 * @param dateTime
 * @param dateFormat
 * @return
 */
	public static String getQueryTableDate(String dateTime, String dateFormat) {
		Date date = new Date();
		if (StringUtils.isNotBlank(dateTime)) {
			try {
				date = DateUtil.convertStringToDate(dateTime);
			} catch (ParseException e) {
				// TODO: handle exception
				log.warn(e);
			}
		}
		String tableDate = DateUtil.convertDateToString(dateFormat, date);
		// return ("CCT_WF_MANAGE_" + tableDate);
		return tableDate;
	}

}
