<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme/tab_main.css" type="text/css" media="screen">
<link href="${pageContext.request.contextPath}/css/theme/search_suggest.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search_suggest.js"></script>
</head>
<body>

<h1 align="center">模糊匹配输入框方式</h1>
<!--第一部分：文本输入框的hint  -->
  <table class="table_input" align="center">
    <tr>
    <td class="lable">获取RollupEnterpriseCommbarcode中的name字段输入框形式：</td>
    <td><div id="inputTextDiv"></div></td>
    </tr>

  </table>

<script>
var option = {
inputName : "inputText",
inputText : "",
url : "search.jsp",
zIndex:11,
suggestWidth:200,
fns:function(){$("#inputTextDiv").append("<div>我是被添加的</div>")}
}
$("#inputTextDiv").suggestShow(option); 
</script>
<!--第二部分：下拉列表框的ajax提示 -->
 <table class="table_input" align="center">
    <tr>
    <td class="lable">获取RollupEnterpriseCommbarcode中的name字段下拉框形式：</td>
    <td><div id="selectDiv"></div></td>
    </tr>

  </table>
	
<script>
var option1 = {
inputName : "inputText",
inputValue : "请选择...",
url : "search.jsp",
arrow :1,
isScroll:1,
zIndex:10
}
$("#selectDiv").suggestShow(option1);
</script>

</body>
</html>