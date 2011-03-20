package com.easytaxi.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.asiainfo.cscqm.login.bo.StaffInfo;
import com.easytaxi.common.exception.OutOfSessionException;
import com.easytaxi.common.exception.ServiceException;
import com.easytaxi.common.util.BeanFactoryUtil;
import com.easytaxi.commonlog.bo.QualityOrderLog;
import com.easytaxi.commonlog.service.IQualityOrderLogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * global-exception:catch ServiceExction
 * 
 * @author renmian
 * 
 */
public class BaseAction extends ActionSupport {

	protected Log logger = LogFactory.getLog(getClass());

	protected Object getBean(String beanName) throws ServiceException {
		return BeanFactoryUtil.getBean(beanName);
	}

	/**
	 * 生成异常描述信息
	 * 
	 * @param msg
	 * @param e
	 * @return
	 */
	protected String buildError(String msg, Exception e) {
		StringBuffer error = new StringBuffer(500);
		error.append(msg).append(":");
		if (e != null) {
			error.append(e.getMessage());
		}
		return error.toString();
	}

	/**
	 * 从session获取数据
	 * 
	 * @param key
	 * @return
	 */
	protected Object getSesstion(String key) {
		Map<String, Object> session = (Map) ActionContext.getContext().get(
				"session");
		return session.get(key);
	}

	/**
	 * 从session中删除数据
	 * 
	 * @param key
	 */
	protected void removeSession(String key) {
		Map<String, Object> session = (Map) ActionContext.getContext().get(
				"session");
		session.remove(key);
	}

	/**
	 * 保存数据到session
	 * 
	 * @param key
	 * @param object
	 */
	protected void saveSession(String key, Object object) {
		Map<String, Object> session = (Map) ActionContext.getContext().get(
				"session");
		session.put(key, object);
	}

	protected HttpServletRequest getRequest() {
		// return
		// (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		// return
		// (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 记录系统日志
	 * @param operModule
	 * @param operType
	 * @param remark
	 */
	protected void recordSysLog(String operModule, String operType,
			String remark,StaffInfo staffInfo) {
		//StaffInfo staffInfo = (StaffInfo)getSesstion("UserInfo");
		if (staffInfo == null)
			throw new OutOfSessionException("您的登录时间超长，会话已经过期，请重新登录！");
		try {
			QualityOrderLog qualityOrderLog = new QualityOrderLog(staffInfo
					.getStaffId(), staffInfo.getStaffName(), operModule,
					operType, remark);
			IQualityOrderLogService qualityOrderLogService = (IQualityOrderLogService) getBean("qualityOrderLogService");
			qualityOrderLogService.insertQualityOrderLog(qualityOrderLog);
		} catch (ServiceException e) {
			// TODO: handle exception
			logger.error("recordSysLog error:", e);
		}

	}
	
	public void outJsonString(String str) {
		getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str);
	}

	public void outJson(Object obj) {
		outJsonString(JSONObject.fromObject(obj).toString());
	}

	public void outJsonArray(Object array) {
		outJsonString(JSONArray.fromObject(array).toString());
	}
	
	public void outJsonArrayPage(Object array,int num) {
		outJsonString("{totalProperty:"+num+",root:"+JSONArray.fromObject(array).toString()+"}");
	}

	public void outString(String str) {
		try {
			PrintWriter out = getResponse().getWriter();
			out.write(str);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public void outXMLString(String xmlStr) {
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}
}
