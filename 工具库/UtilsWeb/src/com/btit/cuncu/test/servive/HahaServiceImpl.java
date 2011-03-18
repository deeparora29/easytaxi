package com.btit.cuncu.test.servive;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.btit.cuncu.test.UserValue;
import com.btit.cuncu.test.UserValueProxy;
import com.btit.cuncu.test.dao.Dao;

import com.btit.cuncu.test.servive.HahaService;

import com.base.support.dao.CallParameter;
import com.base.support.dao.Cond;
import com.base.support.dao.Condition;
import com.base.support.dao.ConditionImpl;
import com.base.support.util.ConvertUtil;



public class HahaServiceImpl
implements HahaService{
	

	/**
	 * <p>数据库Dao接口对象</p>
	 */
	private Dao dao;
	
	public int distributedRecords() {
		UserValue userValue = UserValueProxy.getUserValue();
		
		if(userValue != null){
			 //参数列表
			List params = new ArrayList();
			int paramlength = 3;
			
			CallParameter callParameter = new CallParameter();
			callParameter.setInOutTypes(CallParameter.TYPE_IN);
			callParameter.setTypes(Types.NUMERIC);
			callParameter.setValue(Long.valueOf(userValue.getEntityId()));
			params.add(callParameter);
			
			callParameter = new CallParameter();
			callParameter.setInOutTypes(CallParameter.TYPE_IN);
			callParameter.setTypes(Types.NUMERIC);
			callParameter.setValue(Long.valueOf(userValue.getEntityId()));
			params.add(callParameter);
								
			callParameter = new CallParameter();
			callParameter.setInOutTypes(CallParameter.TYPE_OUT);
			callParameter.setTypes(Types.VARCHAR);
			params.add(callParameter);
			
			
			dao.callProcedure("{call PKG_EBM.P_EBM_DISTRIBUTED_RECORDS(?,?,?)}", params);

			

			int result = ConvertUtil.stringToInt((String)callParameter.getValue());
			return result;
			
		}
		return 0;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public static void main(String args[]) throws Exception {
		
	}
}