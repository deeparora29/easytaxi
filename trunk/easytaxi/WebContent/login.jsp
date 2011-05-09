<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.easytaxi.common.SystemPara"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/usertab.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/login.js"></script>
</head>
<%
	String errorInfo = (String)session.getAttribute(SystemPara.SESSION_ERRORINFO);
	if(errorInfo == null)
	    errorInfo = "";
%>
<body>

<div class="login_in">
	<div class="login_l">
    	<div class="login_title"></div>
    	<div class="jd_gonglve">
                    <h2><span class="left">登录：</span><span class="right"><a href="#" rel="1">乘客</a><a href="#" rel="2">出租车</a></span></h2>
                    <div class="glpx px001 hidden"></div>
                    <div class="glpx px002 hidden"></div>
        <form action="LoginServlet" method="post" id="loginForm">
        	<input type="hidden" id="type" value="passenger" name="type"/>
            <div class="login_content">
                <label>登录账号：</label><div class="inputWidth"><input type="text" class="input_bg df0" value="您的邮箱/电话号码" id="account" name="account"/></div>
                <label>登录密码：</label><div class="inputWidth"><input type="password" class="input_bg" id="password" name="password"/> <a href="#">忘记密码？</a></div>
            </div>
            <br />
            <div class="login_con">
                <div class="autolog"><input type="checkbox" class="mR5"/>下次自动登录</div>
                <div id="errorInfo" style="color: red"><%=errorInfo %></div>
                <input type="button" class="login_btn hand" id="submitBtn" />
            </div>
        </form>
        <br />
        <div class="login_zhuce">
        	<p>还没使用过EasyTaxi</p>
            <a class="zhuce hand" href="register.jsp"></a>
        </div>
         </div>
    </div>
	<div class="login_r">
    	<div class="login_gn"></div>
        <div class="login_nljs">
        	<ul></ul>
            <P>[4月24日] 商家频道<span class="fColorGreen">“引进商家”</span><br />
            </P>
        </div>
    </div>
</div>
</body>
</html>
