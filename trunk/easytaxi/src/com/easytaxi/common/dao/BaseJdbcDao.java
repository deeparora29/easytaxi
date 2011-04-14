package com.easytaxi.common.dao;

import java.beans.PropertyDescriptor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.easytaxi.common.exception.DaoException;

/**
 * baseJdbcDao
 * 
 * @author rennnmia
 */
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

    protected Object getObjectFromList(List list) {
        return (list == null || list.size() == 0) ? null : list.get(0);
    }
    
    
    /**
	* @Description: 获取流水号
	* @param serialType 流水号类型 t_user_id:出租车id , p_user_id:乘客id ,request_no : 请求编号 ,track_id:行迹编号  
	* @param len  流水号长度
	* @param showDate 流水号是否带日期
	* @return SerialNum ， 流水号生成失败返回 -1
	* <p><blockquote>
	*  e.g.  getSerialNum( "t_user_id" , 13 , "true");
	*  		 其中：t_user_id与et_sys_var表中field_name对应，若无此记录则不能获取正确的流水号
	*       13 为流水号长度
	*       true表示需要添加日期
	*       return 2011040401056     
	* </blockquote>
	* <p>	
	*/
	public  String getSerialNum(final String serialType , final int len , final String showDate ) {
		String serialNum = (String) getJdbcTemplate().execute(
			new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					String storedProc = "{call get_serial_num(?,?,?,?)}";
					CallableStatement cs = con.prepareCall(storedProc);
					cs.setString(1, serialType );
					cs.setInt(2, len);
					cs.setString(3, showDate );
					//cs.setInt(4, 15);
					cs.registerOutParameter(4, Types.VARCHAR);
					return cs;
				}
			}, new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)throws SQLException, DataAccessException {
					cs.execute();
					return cs.getString("s_value");
				}
		});

		return serialNum;
	}
    
}
