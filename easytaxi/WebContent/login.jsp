<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Easytaxi App.</title>
<%@ include file="/include.jsp" %>
<script type="text/javascript">   
function login() {
		if (document.getElementById("account").value == "") {
			alert("Please input your account!");
			document.getElementById("account").focus();
			return false;
		}
		if (document.getElementById("password").value == "") {
			alert("Please input your password!");
			document.getElementById("password").focus();
			return false;
		}
		document.getElementById("logonForm").action='LoginServlet';
	
    	document.logonForm.submit();
    
    }
</script>
<script language="javascript" for="document" event="onkeydown">
    if (event.keyCode == 13)
    {
        login();
    }
</script>
</head>
<body>
<form method="post" id='logonForm' name="logonForm" action="LoginServlet">
        <div class="login">
            <div class="container">
                <div class="top">
                </div>
                <div class="center">
                    <div><label for="account">Account:</label><input type="text" name="account" id="account" /></div>
                    <div><label for="password">Password:</label><input type="password" name="password" id="password" /></div>
                    <div><button class="btnNormal2 btnLogin">Log&nbsp;in</button></div>
                    
                </div>
                <div class="bottom">
              Mobile Soft Consulting 
                </div>
            </div>
        </div>
</form>        
</body>
</html>