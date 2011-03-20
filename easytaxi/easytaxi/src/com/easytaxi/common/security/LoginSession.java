package com.easytaxi.common.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.cscqm.login.bo.StaffInfo;

/**
 * session会话过期检查
 * @author renmian
 *
 */
public class LoginSession {
	private final static Log log = LogFactory.getLog(LoginSession.class);

	public static boolean doValidate(HttpSession sn) {
		log.debug("HttpSession doValidate");
		if (sn == null)
			return false;
		StaffInfo staffInfo = (StaffInfo) sn.getAttribute("UserInfo");
		
		if (staffInfo == null)
			return false;
		return true;
	}

	public static boolean doValidate(Map sn) {
		log.debug("Map doValidate");
		if (sn == null)
			return false;
		StaffInfo staffInfo = (StaffInfo) sn.get("UserInfo");
		if (staffInfo == null)
			return false;
		return true;
	}

	public static boolean doValidate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		log.debug("HttpServletRequest doValidate");

		HttpSession sn = request.getSession();
		if (sn != null) {
			if (doValidate(sn))
				return true;
		}
		
		StringBuffer url = new StringBuffer(LoginPagesCfg
				.getSessionExpiredPage());
		StringBuffer from = request.getRequestURL();
		String queryStr = request.getQueryString();
		if (StringUtils.isNotBlank(queryStr)) {
			from.append("?" + queryStr);
		}
		String org = URLEncoder.encode(from.toString(), "UTF-8");
		if (org.indexOf("?") >= 0) {
			url.append("&from=");
		} else {
			url.append("?from=");
		}
		url.append(org.toString());
		log.info("Fail to pass validating User Session, redirect to " + url);
		response.sendRedirect(url.toString());
		return false;
	}
}
