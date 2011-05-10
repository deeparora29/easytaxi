<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.easytaxi.common.SystemPara"%>
<%
	String rootCtx = request.getContextPath();
	String userid = (String)session.getAttribute(SystemPara.SESSION_USERID);
	if(userid == null || userid.equals(""))
	    throw new Exception("用户尚未登录！");
%>
