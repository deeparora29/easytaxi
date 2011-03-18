<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>可编辑EC Table 测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/tab_main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/td_style_ec.css" />

<script language="JavaScript" src="${pageContext.request.contextPath}/js/eccn.js" charset="UTF-8"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/editTable.js" charset="UTF-8"></script>
</head>

<body>
<table width="100%" id="editableTable">
	<tr>
		<td align=""><ec:table items="staffList" var="staffInfo"
			title="可编辑EC Table显示列表"
			action="${pageContext.request.contextPath}/QueryStaffInfoServlet?query=false"
			width="100%" showPagination="true" showStatusBar="true"
			showGotoPage="false" rowsDisplayed="5">
			<ec:row>
				<ec:column cell="radiobox" value="${staffInfo.staffId}" width="10%"
					alias="preStaffId" title="选择">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="员工号" width="25%" property="staffId">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="姓名" width="25%" property="staffName">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="部门名称" width="25%" property="departName">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
			</ec:row>
		</ec:table></td>
	</tr>
	<tr><td align="center"><input type="button" value="增加" id="addBtn" class="button">&nbsp;&nbsp;
	<input type="button" value="删除" id="delBtn" class="button"></td></tr>
</table>
<br>
<div id="adddiv"><from action="#">
<table class="table_input" align="center">
<tr><td class="lable">员工号:</td>
<td><input type="text" id="num"/></td></tr>
<tr>
<td class="lable">姓名:</td>
<td><input type="text" id="name"/></td></tr>
<tr>
<td class="lable">员工部门:</td>
<td><input type="text" id="depart"/></td>
</tr>
<tr><td align="center" colspan="2"><input type="button" id="add" value="保存"/>&nbsp;&nbsp;<input type="button" id="not" value="取消"/></td></tr>
</table></form></div>
</body>
</html>