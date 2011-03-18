package com.btit.cuncu.test.servive;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.base.support.dao.CallParameter;
import com.btit.cuncu.test.BtitException;
import com.btit.cuncu.test.UserValueProxy;
import com.btit.cuncu.test.dao.Dao;
import com.btit.cuncu.test.UserValue;

/**
 * <p>Title: 单据号存储过程调用接口实现类;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2008</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class OrderNumberServiceImpl implements OrderNumberService{
	/**
	 * <p>数据库Dao接口对象</p>
	 */
	private Dao dao;

	@SuppressWarnings("unchecked")
	public String genOrderNumber(Class cls) {
	    UserValue userValue = UserValueProxy.getUserValue();
//	  参数列表
		List params = new ArrayList();
		
		CallParameter callParameter = new CallParameter();
		callParameter.setInOutTypes(CallParameter.TYPE_IN);
		callParameter.setTypes(Types.NUMERIC);
		callParameter.setValue(Long.valueOf(userValue.getEntityId()));
		params.add(callParameter);
		
		callParameter = new CallParameter();
		callParameter.setInOutTypes(CallParameter.TYPE_IN);
		callParameter.setTypes(Types.VARCHAR);
		callParameter.setValue(cls.getSimpleName());
		params.add(callParameter);
		
		callParameter = new CallParameter();
		callParameter.setInOutTypes(CallParameter.TYPE_OUT);
		callParameter.setTypes(Types.VARCHAR);
		params.add(callParameter);
		
		dao.callProcedure("{call PKG_PUB.P_PUB_NEW_ORDER_NUMBER(?,?,?)}", params);
		
		if(params == null || params.size() < 3){
			throw new BtitException("执行P_PUB_NEW_ORDER_NUMBER异常");
		}

		callParameter = (CallParameter)params.get(2);
		
		return (String)callParameter.getValue();
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
