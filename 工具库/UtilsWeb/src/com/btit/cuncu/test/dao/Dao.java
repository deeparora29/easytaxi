package com.btit.cuncu.test.dao;

import java.util.List;

import com.base.support.dao.Condition;
import com.base.support.dao.Orderation;
import com.btit.cuncu.test.sjgl.InformationIssuee;


public interface Dao {
	/**
	 * 调用存储过程
	 * @param procedureName
	 * @param params
	 */
	public void callProcedure(String procedureName, List params);
	
	/**
	 * 调用函数
	 * @param functionName
	 * @param params
	 * @return Object
	 */
	public Object callFunction(String functionName, List params);

	public List queryObjects(Class<InformationIssuee> name, Condition condition, Orderation orderation);
}