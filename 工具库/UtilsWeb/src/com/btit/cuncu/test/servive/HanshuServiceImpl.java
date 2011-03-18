package com.btit.cuncu.test.servive;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.base.support.dao.CallParameter;
import com.base.support.util.ConvertUtil;
import com.btit.cuncu.test.UserValue;
import com.btit.cuncu.test.UserValueProxy;
import com.btit.cuncu.test.dao.Dao;
import com.btit.cuncu.test.BtitException;

public class HanshuServiceImpl
implements HanshuService{
	

	/**
	 * <p>数据库Dao接口对象</p>
	 */
	private Dao dao;
	
	public Object declareAuditing(Long declareId, Object Object) {
UserValue userValue = UserValueProxy.getUserValue();
		
		if(userValue != null){
//			参数列表
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

			
			
			dao.callFunction("{call PKG_EBM.P_EBM_DISTRIBUTED_RECORDS(?,?,?)}", params);

			if(params == null || params.size() < paramlength){
				throw new BtitException("执行统计异常");
			}

			callParameter = (CallParameter)params.get(paramlength - 1);
			
			if(callParameter == null || callParameter.getValue() == null){
				throw new BtitException("执行统计失败");
			}
			
			String sresult = (String)callParameter.getValue();
			if(sresult != null && sresult.equals("-1")){
				throw new BtitException("执行统计失败");
			}
			int result = ConvertUtil.stringToInt((String)callParameter.getValue());
			return Object ;
			
		}
		return null;
	}



}