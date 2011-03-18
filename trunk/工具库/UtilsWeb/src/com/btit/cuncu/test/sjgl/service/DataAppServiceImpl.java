/**
 * 
 */
package com.btit.cuncu.test.sjgl.service;


import com.btit.cuncu.test.dao.Dao;
import com.base.support.dao.Cond;
import com.base.support.dao.Condition;
import com.base.support.dao.ConditionImpl;


/**
 * 数据存取接口实现类
 * @author yuxd
 *
 */
public class DataAppServiceImpl implements DataAppService{
	/**
	 * <p>数据库Dao接口对象</p>
	 */
	private Dao dao;

	public Condition appListData(Class cls) {		
		Condition condition = new ConditionImpl();		
		
		String className = null;
		if(cls != null){
			className = cls.getName();
		}
	
		
		return condition;
	}


	public Condition appListData(Class cls, Condition condition) {
		if(condition == null){
			condition = new ConditionImpl();
		}	

		Condition appCondition = appListData(cls);
		if(appCondition != null && appCondition.size() > 0){
			for(int i=0; i<appCondition.size(); i++){
				condition.addCondition(appCondition.getCond(i));
			}
		}

		return condition;
	}

	public Condition appQueryData(Class cls) {
		Condition condition = new ConditionImpl();		
		
		String className = null;
		if(cls != null){
			className = cls.getName();
		}
	
		return condition;
	}

	public Condition appQueryData(Class cls, Condition condition) {
		if(condition == null){
			condition = new ConditionImpl();
		}	

		Condition appCondition = appQueryData(cls);
		if(appCondition != null && appCondition.size() > 0){
			for(int i=0; i<appCondition.size(); i++){
				condition.addCondition(appCondition.getCond(i));
			}
		}

		return condition;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	

}
