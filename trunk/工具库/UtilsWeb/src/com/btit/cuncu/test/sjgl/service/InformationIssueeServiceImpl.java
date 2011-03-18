/**
 * <p>Copyright ? 2008 BTIT International Group Co. Ltd. </p>
 * <p>Project            :JZDW</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :实现系统功能定义接口</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2008.10.10</p>
 * <p>1.2008..10.10 yuxd new</p>
 **/
package com.btit.cuncu.test.sjgl.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.base.support.dao.Cond;
import com.base.support.dao.Condition;
import com.base.support.dao.ConditionImpl;
import com.base.support.dao.Order;
import com.base.support.dao.Orderation;
import com.base.support.dao.OrderationImpl;
import com.base.support.dao.PageInfo;
import com.base.support.dao.PageResult;
import com.base.support.dao.SimplePageResult;

import com.btit.cuncu.test.dao.Dao;
import com.btit.cuncu.test.sjgl.InformationIssuee;
import com.btit.cuncu.test.sjgl.service.DataAppService;
import com.btit.cuncu.test.BeanUtil;
import com.btit.cuncu.test.UserValueProxy;
import com.btit.cuncu.test.UserValue;;
/**
 * <p>
 * Title: 实现信息定义接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright ? 2008
 * </p>
 * <p>
 * Company: BTIT
 * </p>
 * 
 * @author :yuxd
 * @version:2.0
 */
public class InformationIssueeServiceImpl implements InformationIssueeService {
	/**
	 * <p>
	 *Dao对象
	 * </p>
	 */
	private Dao dao;
	
	


	/**
	 * <p>
	 * Description: 取得所有信息对象
	 * </p>
	 * 
	 * @return List 信息对象集合
	 */
	public List listInformationIssuee(){
//		 TODO 自动生成方法存根
		
		
//		1.得到列表数据条件
		DataAppService dateAppService = (DataAppService)BeanUtil.getBean(DataAppService.class);
		Condition condition = dateAppService.appListData(InformationIssuee.class);
		
	
		

		//2.构造主键倒序条件
		Orderation orderation = new OrderationImpl();
		Order order = new Order();
		order.setColName("informationIssuee.issueId");
		order.setOrderType(Order.ORDERDOWN);
		orderation.addOrder(order);
		
//		3.取得InformationIssuee对象列表ȡ��InformationIssuee�����б�
		List list = null;
		list = dao.queryObjects(InformationIssuee.class, condition, orderation);
		
//		释放对象
		dateAppService = null;
		condition = null;
		orderation = null;
		order = null;
		
		return list;
	}
	

	/**
	 * <p>
	 * Description: 根据条件和排序对象取得信息对象
	 * </p>
	 * 
	 * @param condition 条件对象
	 * @param orderation 排序对象
	 * @return List 信息对象集合
	 */
	public List queryInformationIssuee(Condition condition, Orderation orderation) {
//		 TODO 自动生成方法存根
		
//		1.根据已有的condition条件得到查询数据条件
		DataAppService dateAppService = (DataAppService)BeanUtil.getBean(DataAppService.class);
		condition = dateAppService.appQueryData(InformationIssuee.class, condition);

//		2. 如果没有具体的orderation 则构造主键倒序条件
		if (null == orderation || 0 == orderation.size()){
			orderation = new OrderationImpl();
			Order order = new Order();
			order.setColName("informationIssuee.issueId");
			order.setOrderType(Order.ORDERDOWN);
			orderation.addOrder(order);
			order = null;
		}
		
//		3.根据条件和排序取得InformationIssuee对象列表
		List list = null;
		list = dao.queryObjects(InformationIssuee.class, condition, orderation);
		
//		释放对象
		dateAppService = null;
		condition = null;
		orderation = null;
		
		return list;
	}
	
//	/**
//	 * <p>Description: 得到Dao对象;</p>
//	 * @return: Dao Dao对象
//	 */
//	public Dao getDao() {
//		return dao;
//	}
	/**
	 * <p>Description: 设置Dao对象;</p>
	 * @param: dao Dao对象
	*/
	public void setDao(Dao dao) {
		this.dao = dao;
	}


}
