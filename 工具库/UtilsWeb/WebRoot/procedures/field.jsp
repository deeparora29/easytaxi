<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'field.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <tr>
  <td width="25%" class="cellinputline">程序字段读写调用
    <s:if test='%{columnControlMap.get("ElectricityOrder.emosOrderNumber") == "R"}'>
		<s:set name="emosOrderNumber" value="true" />
		</s:if>
		<s:else>
			<s:set name="emosOrderNumber" value="false" />
		</s:else>
	<s:if test="#emosOrderNumber == true">
		<s:property value="electricityOrder.emosOrderNumber" />
	</s:if>
	<s:else>
	<s:textfield theme="simple" name="electricityOrder.emosOrderNumber" cssClass="input" 
				 id="electricityOrder.emosOrderNumber" size="30" maxlength="30"></s:textfield>
</s:else>
</td>
</tr>


  </body>
</html>
