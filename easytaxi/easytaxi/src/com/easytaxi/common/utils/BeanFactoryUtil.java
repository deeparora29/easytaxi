package com.easytaxi.common.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.easytaxi.common.exceptions.utils.BeanFactoryException;



/**
 * During HttpServlet init, load spring context 
 * @author renmian
 *
 */
 public class BeanFactoryUtil extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	 private static final Log logger = LogFactory.getLog(BeanFactoryUtil.class);

		private static ApplicationContext ctx = null;

	/**
	 * 获取Spring中的Bean
	 * 
	 * @param beanName
	 *            Bean的名称
	 * @return 如果成功则反回Bean对象，如果失败则抛出异常.
	 */
		public static Object getBean(String beanName) throws BeanFactoryException{
			if (ctx == null) {
			logger.warn("ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
			throw new BeanFactoryException(
					"ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
			}
			try {
				return ctx.getBean(beanName);
			} catch (BeansException exp) {
			logger.error("读取[" + beanName + "]失败！", exp);
			throw new BeanFactoryException("读取[" + beanName + "]失败！", exp);
			}
		}

		/**
		 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
		 */
		public void init(ServletConfig config) throws ServletException {
			if (logger.isInfoEnabled()) {
			logger.info("初始化BeanFactoryUtil....");
			}
			super.init(config);

			if (ctx == null) {
				ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			}

			if (logger.isInfoEnabled()) {
			logger.info("初始化BeanFactoryUtil....OK.");
			}
		}
}