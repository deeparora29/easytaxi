package com.btit.cuncu.test.dao;

import java.util.List;

import com.base.support.dao.Condition;
import com.base.support.dao.Orderation;
import com.base.support.dao.PageSupport;
import com.btit.cuncu.test.dao.Dao;
import com.btit.cuncu.test.sjgl.InformationIssuee;

public class DaoImpl extends PageSupport implements Dao {
	
	public Object callFunction(String functionName, List params) {
		return this.getPageTemplate().callFunction(functionName, params);
	}

	public void callProcedure(String procedureName, List params) {
		this.getPageTemplate().callProcedure(procedureName, params);
	}

	public List queryObjects(Class<InformationIssuee> name, Condition condition, Orderation orderation) {
		//	 TODO 自动生成方法存根
		return null;
	}
	
	
}
