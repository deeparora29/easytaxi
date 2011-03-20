package com.easytaxi.common.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.easytaxi.common.exception.DaoException;
import com.easytaxi.commonlog.bo.QualityOrderLog;

public class BaseJdbcDao extends JdbcDaoSupport {
	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * 为子类提供的第二类查询方法，若子类查询后po类的属性是简单类型（String、int、long等基本类型），则不需要
	 * 覆盖getPoList()方法，否则需要子类覆盖getPoList（）方法，为保险起见，子类最好覆盖getPoList（）方法
	 * 
	 * @param sql
	 * @param poClass
	 * @param paras
	 *            TODO
	 * 
	 * @return
	 */
	protected List getQueryObjectList(JdbcTemplate jdbcTemplate, String sql,
			Class poClass, Object[] paras) throws DaoException {

		List list = jdbcTemplate.queryForList(sql, paras);
		List poList = this.getBoList(list, poClass);
		return poList;
	}

	protected List getBoList(List list, Class boClass) throws DaoException {
		List ret = new ArrayList();
		try {
			if (list == null || list.size() == 0) {
				return ret;
			}
			PropertyDescriptor[] pds = PropertyUtils
					.getPropertyDescriptors(boClass);
			String[] propertyNames = this.getPropertyNames(pds);
			Map map = (Map) list.get(0);
			String[] keys = this.getMatchKey(map, propertyNames);
			for (int i = 0; i < list.size(); i++) {
				Object obj = null;
				obj = boClass.newInstance();
				Map temp = (Map) list.get(i);
				for (int k = 0; k < propertyNames.length; k++) {
					if (PropertyUtils.isWriteable(obj, propertyNames[k])) {
						if (keys[k] != null && temp.containsKey(keys[k])) {
							BeanUtils.setProperty(obj, propertyNames[k], temp
									.get(keys[k]));
						}
					}
				}
				ret.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new DaoException("JdbcTemplate query list查询出错：", e);
		}
		return ret;
	}

	private String[] getPropertyNames(PropertyDescriptor[] pds) {
		String[] ret = new String[pds.length];
		for (int i = 0; i < pds.length; i++) {
			ret[i] = pds[i].getName();
		}
		return ret;
	}

	/**
	 * 获得相应的key值，若没有则返回相应的为null
	 * 
	 * @param row
	 * @param property
	 * @return
	 */
	private String[] getMatchKey(Map row, String[] property) {
		String[] ret = new String[property.length];
		for (int i = 0; i < property.length; i++) {
			ret[i] = this.getMatchKey(row, property[i]);
		}
		return ret;
	}

	private String getMatchKey(Map row, String property) {
		for (Iterator iter = row.keySet().iterator(); iter.hasNext();) {
			String item = (String) iter.next();
			// System.out.println("KEY值：" + item);
			String newItem = StringUtils.replaceChars(item, "-_", null);
			// System.out.println("转化KEY值：" + newItem);
			if (property.equalsIgnoreCase(newItem)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * 生成异常描述信息
	 * 
	 * @param msg
	 * @param e
	 * @return
	 */
	protected String buildError(String msg, Exception e) {
		StringBuffer error = new StringBuffer();
		error.append(msg).append(":");
		if (e != null) {
			error.append(e.getMessage());
		}
		return error.toString();
	}

//	public void insertQualityOrderLog(QualityOrderLog qualityOrderLog)
//			throws DataAccessException {
//		String sql = "insert into CCT_QUALITY_ORDER_LOG(OPER_ID, OPER_NAME, OPER_TIME, OPER_MODULE, OPER_TYPE, REMARK) values( ?, ?, sysdate, ?, ?, ? )";
//		Object[] args = {qualityOrderLog.getOperId(), qualityOrderLog.getOperName(), qualityOrderLog.getOperModule(), qualityOrderLog.getOperType(), qualityOrderLog.getRemark()};
//		this.getJdbcTemplate().update(sql, args);
//	}

}
