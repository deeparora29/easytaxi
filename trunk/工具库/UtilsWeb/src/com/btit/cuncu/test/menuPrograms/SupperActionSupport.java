package com.btit.cuncu.test.menuPrograms;


import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>Title: Action超类;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2008</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class SupperActionSupport extends ActionSupport {
	/**
	 * <p>串行标识</p>
	 */
	private static final long serialVersionUID = -436701460101521221L;
	/**
	 * <p>字段控制映射表,其中key为表名.字段名，value为控制属性,"R"为只读，“W”为读写</p>
	 * <p>　如{"TransferHead.transferOrderNumber","R"}</p>
	 */
	private Map columnControlMap = new HashMap();
	/**
	 * <p>功能映射,其中key为功能头标识,value为功能列表</p>
	 */
	private Map functionMap = new HashMap();
	private String queryTag;

	/**
	 * @return queryTag
	 */
	public String getQueryTag() {
		return queryTag;
	}
	/**
	 * @param queryTag 要设置的 queryTag
	 */
	public void setQueryTag(String queryTag) {
		this.queryTag = queryTag;
	}
	/**
	 * @return columnControlMap
	 */
	public Map getColumnControlMap() {
		return columnControlMap;
	}
	/**
	 * @param columnControlMap 要设置的 columnControlMap
	 */
	public void setColumnControlMap(Map columnControlMap) {
		this.columnControlMap = columnControlMap;
	}
	public Map getFunctionMap() {
		return functionMap;
	}
	public void setFunctionMap(Map functionMap) {
		this.functionMap = functionMap;
	}
}
