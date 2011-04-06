<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
<%@ include file="/include.jsp" %>
<link rel="stylesheet" href="<%=rootCtx %>/css/index.css" type="text/css" media="screen" />
<script type="text/javascript" src="<%=rootCtx %>/js/ui/ui.core.js"></script>
<script type="text/javascript" src="<%=rootCtx %>/js/ui/ui.tabs.js"></script>
<script type="text/javascript" src="<%=rootCtx %>/js/show.js"></script>
</head>
<body>
        <div id="wrapper">  
            <div id="header" class="page5" >
                <h1 id="logo"><a title="Easy Taxi" href="#">Easy Taxi</a></h1>
            </div>

            <div id="content" >
                <div id="show" class="box">
                    <ul id="ccwTabList">
                    	<li><a href="demo_v3.html"><span>Location</span></a></li>
                    	<li><a href="test.jsp"><span>Track History</span></a></li>
                    	<li><a href="index.jsp"><span>Account</span></a></li>
                    </ul>
                </div>
                 
            </div>
            
            <div id="footer">
              Account：12345&nbsp;&nbsp;Nick Name：XXXX&nbsp;&nbsp;Type:Taxi
            </div>
            
        </div>
</body>
</html>