/**
 * 
 */
package com.btit.cuncu.test.sjgl.service;

import com.base.support.dao.Condition;

/**
 * 数据存取接口类
 * @author yuxd
 *
 */
public interface DataAppService {
	/**
	 * 得到数据的列表条件
	 * @param cls
	 * @return
	 */
	public Condition appListData(Class cls);

	/**
	 * 得到数据的列表条件
	 * @param cls
	 * @param condition
	 * @return
	 */
	public Condition appListData(Class cls, Condition condition);

	/**
	 * 得到数据的筛选条件
	 * @param cls
	 * @return
	 */
	public Condition appQueryData(Class cls);

	/**
	 * 得到数据的筛选条件
	 * @param cls
	 * @param condition
	 * @return
	 */
	public Condition appQueryData(Class cls, Condition condition);
}
