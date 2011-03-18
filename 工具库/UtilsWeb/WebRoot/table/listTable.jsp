<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>EC Table 测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/td_style_ec.css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/eccn.js"></script>
</head>

<body>
<table width="100%">
	<tr>
		<td><ec:table items="staffList" var="staffInfo" title="普通列表测试" 
			action="${pageContext.request.contextPath}/QueryStaffInfoServlet"
			width="100%" 
			sortable="true"
			filterable="true"
			paginationLocation="top"
			showRowsDisplayed="false"
			showGotoPage="true"
			showPagination="true" 
			showStatusBar="true" 
			rowsDisplayed="5">
			<ec:exportXls fileName="query.xls" tooltip="导出 Excel"/>
			<ec:row>
				<ec:column cell="radiobox" value="${staffInfo.staffId}" width="10%" 
					alias="preStaffId" title="选择" onclick="alert('您选中的员工工号为${staffInfo.staffId}');"
					filterable="false" sortable="false">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="员工号" width="25%" property="staffId" style="readonly">
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
</table>
</body>
</html>