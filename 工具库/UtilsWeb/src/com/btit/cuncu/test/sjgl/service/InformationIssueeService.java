/**
 * <p>Copyright ? 2008 BTIT International Group Co. Ltd. </p>
 * <p>Project            :JZDW</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :信息</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2008.10.10</p>
 * <p>1.2008..10.10 yuxd new</p>
 **/
package com.btit.cuncu.test.sjgl.service;

import java.util.List;
import com.base.support.dao.Condition;
import com.base.support.dao.Orderation;



/**
 * <p>
 * Title: 定义信息的接口
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
public interface InformationIssueeService {
	

	/**
	 * <p>
	 * Description: 取得所有信息对象
	 * </p>
	 * 
	 * @return List 信息对象集合
	 */
	List listInformationIssuee() ;

	

	/**
	 * <p>
	 * Description: 根据条件和排序对象取得信息对象
	 * </p>
	 * 
	 * @param condition 条件对象
	 * @param orderation 排序对象
	 * @return List 信息对象集合
	 */
	List queryInformationIssuee(Condition condition, Orderation orderation);


	
}