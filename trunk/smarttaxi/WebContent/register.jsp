<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SmartTaxi</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="SmartTaxi,LBS" />
<meta name="keywords" content="SmartTaxi,LBS" />
<meta content="Copyright (c) 2010 - 2011 SmartTaxi,LBS" name="Copyright"/>

<link href="images/favicon.ico" type="image/x-icon" rel="shortcut icon"/>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="js/jquery.1.6.1.js"></script>
<script language="javascript" type="text/javascript" src="js/main.js"></script>

<style type="text/css">
</style>
</head>

<body>
<!--header-->
<div id="header">
    <div class="head_content">
    	<h3><a href="#">SmartTaxi 首页</a> | <a href="#">官方微博</a></h3>
        <!--<p><a href="#">登录</a> 或者 <a href="#">免费注册</a> | <a href="#">FAQ</a></p>-->
        <p>欢迎您： <a href="#">joney51</a> 乘客  | <a href="setting.html">设置</a> | <a href="#">退出</a> | <a href="#">FAQ</a></p>
    </div>
</div>
<!--navigation-->
<div id="navigation">
    <div class="navigation_content">
        <h1 class="logo"><a href="#"><img src="images/logo.gif" width="255" height="100" /></a></h1>
        <div class="menu">
            <a href="index.html" class="home_selected"><span>首页</span></a>
            <a href="line.html" class="line"><span>我的线路</span></a>
            <a href="operation.html" class="operation"><span>操作记录</span></a>
            <a href="about.html" class="about"><span>关于</span></a>
            <a href="help.html" class="help"><span>帮助</span></a>
        </div>
    </div>
</div>
<!--container-->
<div id="container">
<div class="register">
        <div class="register_left">
            <h3><img src="images/register_title2.gif" width="195" height="24"/></h3>
            <form>
                <label>我　　是：</label><span class="fRed">*</span><div class="register_r"><input name="" type="radio" value=""  class="mR5"/>乘客<input name="" type="radio" value="" class="mR5"/>司机</div><br />
                
                
                <label>车牌号：</label><span class="fRed">*</span><div class="register_r"><input type="text" class="input_text"/></div><br />
                <label>登录密码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                <label>确认密码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                <label>服务证号：</label><span class="fRed">*</span><div class="register_r"><input type="text" class="input_text"/></div><br />
                <label>用户名：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                <label>手机号码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                
<!--                <label>邮箱地址：</label><span class="fRed">*</span><div class="register_r"><input type="text" class="input_text"/></div><br />
                <label>登录密码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                <label>确认密码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
                <label>性　　别：</label><span class="fRed">*</span><div class="register_r"><input name="" type="radio" value=""  class="mR5"/>男<input name="" type="radio" value="" class="mR5"/>女</div><br />
                <label>用户名：</label><span class="fRed">*</span><div class="register_r"><input type="text" class="input_text"/></div><br />
                <label>手机号码：</label><span class="fRed">*</span><div class="register_r"><input type="password" class="input_text"/></div><br />
-->                
                <label></label><span></span><div class="register_r"><input name="" type="checkbox" value="" class="mR5"/>同意使用协议条款</div><br />
                <label></label><span></span><input name="" type="button" value=""  class="register_submit"/>
            </form>
            <p>还没有账号？<a href="register.html">>>点击这里登录</a></p>
        </div>
        <div class="register_right">
            <h3><img src="images/register_title.gif" width="284" height="65" /></h3>
            <p>
                1) 再不用在雨中、风中等车了。<br />
                2) 拿出手机即可快速查找周边的空车<br />
                3) 投诉和消除怨气以及被宰的经历<br />
                4) 如遇东西忘记在车上，还可以通过电话联系司机找回来<br />
                    远不止这些……            
            </p>
<!--            <p>
                1) 不用满大街跑，才能找到乘客<br />
                2) 空驶只是在极少数情况下<br />
                3) 提高每天的工作效率<br />
                4) 通过每次的服务质量和口碑来获取更多的生意<br />
                    远不止这些……
            </p>
-->        </div>
    </div>
</div>
<!--footer-->
<div id="footer">SmartTaxi 官方网站 中国领先LBS解决方案及供应商<br />
Copyright © SmartTaxi. All rights reserved. 蜀ICP备00000000号</div>
</body>
</html>
