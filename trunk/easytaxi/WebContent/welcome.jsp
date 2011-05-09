<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.easytaxi.common.SystemPara"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/welcome.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.reflect.js"></script>
<script language="javascript" type="text/javascript" src="js/travel.js"></script>
<script language="javascript" type="text/javascript" src="js/myjs.js"></script>
<script>
function setFrameSrc(src, index){
	document.getElementById("mainFrame").src = src;
	for(var i = 0; i < 4; i++){
		var aId = "tab" + i;
		document.getElementById(aId).setAttribute("class", "");
	}
	var aId = "tab" + index;
	document.getElementById(aId).setAttribute("class", "currentBg");
	
}
</script>
</head>
<%
	String userId = (String)session.getAttribute(SystemPara.SESSION_USERID);
	if(userId == null || userId.equals(""))
	    throw new Exception("用户未登录！");
%>
<body>
<input type="hidden" id="userId" value="<%=userId %>"/>
<div id="center">  
           

             <!--导航开始-->
    <h1 class="logo"><a href="http://www.shua84.com"><img src="images/logo.gif" alt="耍巴适网logo" width="140" height="90" title="耍巴适网logo" /></a></h1>
    <div class="menue">
    	<div class="navlist">
            <a id="tab0" class="currentBg" href="#" onclick="setFrameSrc('demo_v3.html', 0);">实时位置</a>
            <a id="tab1" href="#" onclick="setFrameSrc('track.jsp', 1);">线路</a>
            <a id="tab2" href="#" onclick="setFrameSrc('usermgr.jsp', 2);">设置</a>
            <a id="tab3" href="#">商家</a>
            <a href="LogoutServlet" class="search">退出</a>
        </div>
        
        <form>
            <input type="text" value="请输入关键词" class="input_ss"/>
            <div class="selectcon">
                <ul class="hidden">
                    <li><a href="#">文章</a></li>
                    <li><a href="#">景点</a></li>
                    <li><a href="#">活动</a></li>
                    <li><a href="#">耍友</a></li>
                </ul>
                <a href="#" class="down">文章</a>
            </div>
            
        </form>
        
        
    </div>
<!--导航结束-->  
<!--top-->
<div class="content">
<div class="lineGreen">
<iframe src="demo_v3.html" class="contentframe" id="mainFrame" scrolling="no" frameborder="0"></iframe>
</div>
</div>
</div>
</body>
</html>