<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出错了！</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/footer.css" rel="stylesheet" type="text/css" />
<link href="css/registe.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="footer">

<h2/>出错了</h2>
<p>

<% 
try {
	// The Servlet spec guarantees this attribute will be available
	Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception"); 

	if (exception != null) {
		if (exception instanceof ServletException) {
			// It's a ServletException: we should extract the root cause
			ServletException sex = (ServletException) exception;
			Throwable rootCause = sex.getRootCause();
			if (rootCause == null)
				rootCause = sex;
			out.println("** Root cause is: "+ rootCause.getMessage());
			//rootCause.printStackTrace(new java.io.PrintWriter(out)); 
		}
		else {
			// It's not a ServletException, so we'll just show it
			exception.printStackTrace(new java.io.PrintWriter(out)); 
		}
	} 
	else  {
    	out.println("No error information available");
	} 

	// Display cookies
	out.println("\nCookies:\n");
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
    	for (int i = 0; i < cookies.length; i++) {
      		out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
		}
	}
	    
} catch (Exception ex) { 
	ex.printStackTrace(new java.io.PrintWriter(out));
}
%>

</p>
<br/>
<div class="zc_hyc">加入耍巴适，找到喜欢的活动、玩转很多有趣的游戏、遇见趣味相投的好友。<a href="register.jsp" style="font-weight: bold; color:#167b1b;">免费注册</a></div>
<div class="zc_dl">如果你已经是注册会员,请点击 <a href="login.jsp" style="font-weight: bold">登录</a></div>
</div>

</body></html>



