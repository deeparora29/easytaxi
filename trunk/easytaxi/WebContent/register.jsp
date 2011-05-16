<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/registe.css" rel="stylesheet" type="text/css" />
<link href="css/usertab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script language="javascript" type="text/javascript" src="js/register.js"></script>
</head>

<body>
<input type="hidden" id="type" value="passenger"/>
<div class="zhuce">
	<div class="zc_ll">
    	<div class="zc_top"></div>
        <div class="zc_hyc">加入耍巴适，找到喜欢的活动、玩转很多有趣的游戏、遇见趣味相投的好友。</div>
        <div class="zc_dl">如果你已经是注册会员,请点击 <a href="login.jsp">登录</a></div>
        <div class="jd_gonglve">
                    <h2><span class="left">注册：</span><span class="right"><a href="#" rel="1">乘客</a><a href="#" rel="2">出租车</a></span></h2>
                    <div class="glpx px001 hidden">
        
        <form action="RegisterServlet?type=passenger" method="post" id="passengerForm">
        	<label>您的常用邮箱：</label><span><input type="text" class="input_bg" id="email" name="email"/>*</span>
        	<label>手机号码：</label><span><input type="text" class="input_bg" id="phone" name="phone"/>*</span>
        	<label>设定密码：</label><span><input type="password" class="input_bg" id="password" name="password"/>*</span>
        	<div class="mimagf">密码长度6-16位，可使用英文字母、数字、特殊字符组成。</div>
        	<label>昵称：</label><span><input type="text" class="input_bg" d="nickName" name="nickName"/></span>
        	<label>性别：</label><span><input type="radio" class="danxuan" name="gender" id="gender0" value="male" checked="checked"/><b>男</b><input type="radio" class="danxuan" name="gender" id="gender1" value="female"/><b>女</b></span>
        	<label></label><span><input type="checkbox" checked="checked" class="danxuan" id="agreement" name="agreement"/>同意耍巴适用户发布 <a href="#">信息协议</a></span>
        	<p><div class="hidden" id="errorInfo" style="color: red"></div><br/>
        	<input type="button" class="zc_tijiao" id="okBtn"/></p>
        	</p>
        	</form>
        	</div>
        	<div class="glpx px002 hidden">
        	<form action="RegisterServlet?type=taxi" method="post" id="taxiForm">
        	<input type="hidden" id="type" value="taxi"/>
        		<label>出租车牌号：</label><span><input type="text" class="input_bg" id="cab" name="cab"/>*</span>
        		<label>设定密码：</label><span><input type="password" class="input_bg" id="password" name="password"/>*</span>
        		<div class="mimagf">密码长度6-16位，可使用英文字母、数字、特殊字符组成。</div>
        		<label>所属公司：</label><span><input type="text" class="input_bg" id="company" name="company"/></span>
        		<label>联系邮箱：</label><span><input type="text" class="input_bg" id="email" name="email"/></span>
        		<label>司机：</label><span><input type="text" class="input_bg" id="driver0" name="driver0"/></span>
        		<label>手机号码：</label><span><input type="text" class="input_bg" id="phone0" name="phone0"/></span>
        		<p>
        		<div class="hidden" id="errorInfo" style="color: red"></div><br/>
        		<input type="button" class="zc_tijiao" id="okBtn"/></p>
        		</form>
        	</div>
        	</div>
    </div>
    <div class="zc_rr">
    	<h3>注册会员专享特权</h3>
        <p>
            <span class="fColorRed mR10">√</span><span class="fColorRed">免费</span>参与其他会员组织的活动<br />
            <span class="fColorRed mR10">√</span><span class="fColorRed">管理</span>每个景区的游记<br />
            <span class="fColorRed mR10">√</span><span class="fColorRed">管理</span>每个景区的照片<br />
            <span class="fColorRed mR10">√</span><span class="fColorRed">管理</span>自己曾经玩过的地方, 记下自己的每个旅程:<br />
            <span class="fColorRed mR10">√</span><span class="fColorRed">点评</span>景区的吃,住,玩,受骗经历<br />
        </p>
    	<h3>体验趣味游戏乐趣</h3>
        <h4><span class="game1">私密花园</span><span class="game2">自己当导演</span><span class="game3">阳光沙滩</span></h4>
    </div>
</div>
</body>
</html>
