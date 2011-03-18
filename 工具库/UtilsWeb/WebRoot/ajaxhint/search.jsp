<%@page import="com.btit.common.utils.ajax.test.AjaxHintService"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.btit.common.utils.ajax.XMLConstructor"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/xml");
response.setCharacterEncoding("UTF-8");
String inputText = request.getParameter("inputText");
String result = "";
//---Begin 数据库查询方式
   AjaxHintService service = new AjaxHintService();
   result = service.queryRollupResultByName(inputText);
//---End
//这里是匹配数据的工程
/*****
//这里是原来采用session读取数据的方式
HashMap<String, String> dataMap = (HashMap<String, String>)application.getAttribute("Data");

if(inputText == null || inputText.equals("")){
	result = XMLConstructor.construtcXml(dataMap);
}else{
	result = XMLConstructor.construtcXmlBy(dataMap, inputText);
}
******/
out.println(result);


%>