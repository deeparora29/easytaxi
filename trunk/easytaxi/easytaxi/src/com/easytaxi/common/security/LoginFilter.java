package com.easytaxi.common.security;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.URLEncoder;

public class LoginFilter implements Filter {

	private final static Log log = LogFactory.getLog(LoginFilter.class);

	/*
	 * (non-Java-doc)
	 * 
	 * @see java.lang.Object#Object()
	 */
	public LoginFilter() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.Filter#init(FilterConfig arg0)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.Filter#doFilter(ServletRequest arg0, ServletResponse
	 *      arg1, FilterChain arg2)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.debug("To validate request.");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String filterUrl = request.getRequestURI();

		
		if (filterUrl.indexOf(".action") > 0) { //仅对action进行检查
			log.debug("filter url:::" + filterUrl);
			if (filterUrl.equals(LoginPagesCfg.getEntryPage())
					|| filterUrl.equals(LoginPagesCfg.getSecondLoginPage()))  {
				arg2.doFilter(arg0, arg1);
				return;
			}
			HttpSession session = request.getSession();

			if (!LoginSession.doValidate(request, response)) {
				log.debug("session已经过期");
				//response.sendRedirect(LoginPagesCfg.SessionExpiredPage);
				return;
			} 
		}

		arg2.doFilter(arg0, arg1);
		return;

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}