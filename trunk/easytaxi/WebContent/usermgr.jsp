<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/usermgr.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.1.4.2.min.js"></script>
</head>

<body>
<div class="travel_index_con">
  <div class="travel_menue_mid">
                <div class="title">
                	<h2><span class=" left">用户信息</span><span class="right"><a href="#">>></a></span></h2>
                </div>
        <form>
        	<label>用户类型：</label><span>乘客</span>
			<div id="passengerDiv">
        	<label>您的常用邮箱：</label><span><input type="text" class="input_bg"/>*</span>
        	<label>手机号码：</label><span><input type="text" class="input_bg"/>*</span>
        	<label>设定密码：</label><span><input type="text" class="input_bg"/>*</span>
        	<div class="mimagf">密码长度6-16位，可使用英文字母、数字、特殊字符组成。</div>
        	<label>昵称：</label><span><input type="text" class="input_bg"/></span>
        	<label>性别：</label><span><input type="radio" class="danxuan"/><b>男</b><input type="radio" class="danxuan"/><b>女</b></span>
        	
        		
        		<label>司机：</label><span><input type="text" class="input_bg"/></span>
        		<label>手机号码：</label><span><input type="text" class="input_bg"/></span>
        	</div>
        	<p><input type="button" class="zc_tijiao" id="okBtn"/></p>
        </form>
   </div>
        <div class="pictures">
            	<h2><span class="left">照片显示</span></h2>
                <p class="tqyb">
                <span><img src="images/travel/04.jpg" width="200" height="130"/></span><br/>
                <span><a href="#" class="sc_phto_btn"></a></span>
                </p>
               
        </div>
   
</div>
</body>
</html>