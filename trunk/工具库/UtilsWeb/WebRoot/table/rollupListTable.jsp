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
		<td><ec:table items="rollupList" var="rollupEnterpriseCommbarcode" 
		 	title="RollupEnterpriseCommbarcode" 
			action="${pageContext.request.contextPath}/PageResultServlet"
			width="100%" 
			sortable="true"
			filterable="true"
			paginationLocation="top"
			showRowsDisplayed="false"
			showGotoPage="true"
			showPagination="true" 
			showStatusBar="true" 
			rowsDisplayed="10"
			>
			<ec:exportXls fileName="query.xls" tooltip="导出 Excel"/>
			<ec:row>
				<ec:column title="商品名称" width="25%" property="name">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="COMM_BARCODE" width="25%" property="commBarcode">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
				<ec:column title="AREA_CODE" width="25%" property="areaCode">
					<ec:attribute>align="center"</ec:attribute>
				</ec:column>
			</ec:row>
		</ec:table></td>
	</tr>
</table>
</body>
</html>