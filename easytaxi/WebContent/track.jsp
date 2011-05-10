<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.easytaxi.location.service.TrackService"%>
<%@page import="com.easytaxi.common.utils.BeanFactoryUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.easytaxi.bo.TrackHistory"%>

<%@ include file="include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Track History</title>
<link href="css/share.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/track.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/travel.js"></script>
<script language="javascript" type="text/javascript" src="js/myjs.js"></script>
</head>
<%

TrackService service = (TrackService)BeanFactoryUtil.getBean("trackService");
List<TrackHistory> list = service.getTrackHistories(userid, -1);//all
	
%>
<body>
<div class="travel_index_con">
            <div class="travel_menue_mid">
                <div class="title">
                	<h2><span class=" left">您的足迹</span><span class="right"><a href="#">>>分享</a></span></h2>
                    <ul>
                    	<li class="d6">记录时间</li>
                    	<li class="d2">线路记录</li>
                    	<li class="d4">用车请求</li>
                    	<li class="d5">线路回放</li>
                    </ul>
                    <%
                    if(list != null && list.size() > 0){
                        for(int i = 0; i < list.size(); i++){
                            TrackHistory track = list.get(i);
                     %>
                     <ul>
                    	<li class="e6"><%=track.getBegintime() %></li>
                    	<li class="e2"><a href="#"><%=track.getTrackfile() %></a></li>
                    	<li class="e4"><%=track.getUserid() %></li>
                    	<li class="e5"><a href="#"><%=track.getTrackid() %></a></li>
                    </ul>
                     <%
                        }
                    }else{
                        out.println("当前没有线路记录！");
                    }
                    %>
                    
                    <div class="fenye"><a href="#"><<</a><a href="#" class="currentPage">1</a><a href="#">2</a>…<a href="#">9</a><a href="#">10</a><a href="#">>></a></div>
                </div>
            </div>
            <div class="travel_menue_rr">
            	<h2><span class="left">四川天气预报</span><a href="#" class="right">>>更多</a></h2>
                <p class="tqyb"><span>成都</span><img src="images/travel/tq_icon.gif" width="18" height="16"/><span>可能有雨</span><span>23℃∽17℃</span><br />
                <span>攀枝花</span><img src="images/travel/tq_icon.gif" width="18" height="16"/><span>可能有雨</span><span>23℃∽17℃</span><br />
                <span>资阳</span><img src="images/travel/tq_icon.gif" width="18" height="16"/><span>小雨</span><span>23℃∽17℃</span><br />
              </p>
            	<h2>景点热门标签</h2>
                <p class="jd_biaoqian"><a href="#">成都</a><a href="#">四姑娘山</a><a href="#">九寨沟</a><a href="#">成都</a><a href="#">四姑娘山</a><a href="#">九寨沟</a><a href="#">成都</a><a href="#">四姑娘山</a><a href="#">九寨沟</a><a href="#">成都</a><a href="#">四姑娘山</a><a href="#">九寨沟</a></p>
          </div>
</div>

</body>
</html>