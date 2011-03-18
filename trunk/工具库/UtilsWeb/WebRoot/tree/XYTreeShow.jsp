<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/btit-taglib.tld" prefix="btit"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XYTree Demo Show</title>

<link href="${pageContext.request.contextPath}/js/xytree/xtree.css" rel="stylesheet" type="text/css">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/xytree/DivTree.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/xytree/Node.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/xytree/Tree.js"></SCRIPT>

</head>
<body>
<h1>普通树</h1>
<btit:createXYTree divId="menuTreeDiv" treeId="menuTree" treeDesc="treeDesc" treeNodes="treeNodes"/>
<hr>
<h1>带复选框的树</h1>
<btit:createXYTree divId="menuTreeDiv1" treeId="menuTree1" treeDesc="checkboxTreeDesc" treeNodes="checkboxTreeNodes"/>

</body>
</html>