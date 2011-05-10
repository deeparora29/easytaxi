<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="com.easytaxi.request.service.CallTaxiServie"%>
<%@page import="com.easytaxi.common.utils.BeanFactoryUtil"%>
<%@page import="com.easytaxi.request.bo.RequestInfo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务</title>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript"><!--
	function confirmRequest(requestNo){
		var data = '{"TransCode":"T004","userid"="<%=userid%>",cabGPS:{lat:32.5767, lng:54.3432},requestNo:"' + requestNo + '"}';
		$.ajax( {
			type :"GET",
			contentType :"application/json;utf-8",
			url :"taxi",
			cache : false,
			//dataType :"json",
			data : { data : data },
			success : function( msg) {
				//alert(msg.ErrorCode);
				$("#responsedata").val(msg);
			}
		});

	}
</script>
</head>
<%
CallTaxiServie service = (CallTaxiServie)BeanFactoryUtil.getBean("callTaxiServie");
List<RequestInfo> list = service.getValidRequest();
if(list == null || list.size() == 0){
    out.println("当前没有乘客发布用车请求");
    return;
}
%>
<div>response data:<br/>
<textarea rows="1" cols="100" name="responsedata" id="responsedata"></textarea>
</div>
<table width="98%" align="center">
<tr>
<td width="20%">RequestNo</td>
<td width="15%">RequestTime</td>
<td width="50%">Detail Info</td>
<td width="15%">Operation</td>
</tr>
<%
for(int i = 0; i < list.size(); i++){
    RequestInfo requestInfo = list.get(i);
%>
<tr>
<td><%=requestInfo.getRequestNo() %></td>
<td><%=requestInfo.getRequestTime() %></td>
<td><%=requestInfo.getRequestInfo() %></td>
<td><input type="button" name="t_button" value="Confirm Request" onclick="confirmRequest('<%=requestInfo.getRequestNo() %>')"/></td>
<%
}
%>
</tr>
</table>
<body>

</body>
</html>