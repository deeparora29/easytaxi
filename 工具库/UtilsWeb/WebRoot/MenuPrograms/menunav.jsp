<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
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
  <div class="tubiao">
			
			<s:iterator id="menuNode" value="selMenuList" status="status">
				<div class="divouter sign<s:property value="%{#status.count}"/>css">
					<a href="#" onclick="menunav(${menuNode.menuId})"><img
							src="${pageContext.request.contextPath}/upload/sym/menu/<s:if test="#menuNode.iconName != null">${menuNode.iconName}</s:if><s:else>defaultsecond.gif</s:else>"
							width="86" height="86" border="0" />
						<div class="divinner">
							${menuNode.menuName}
						</div> 
					</a>
				</div>
			</s:iterator>
			<form action="unknowaction.jsp" name="fm" method="post"
				target="_self">
				<input type="hidden" name="menu.menuId" id="menuId" />
			</form>
			<script type="text/javascript">
		function menunav(menuId){
			document.fm.menuId.value = menuId;
	
		    var owin = window.open("about:blank","MENUNAV","fullscreen=yes,scrollbars=yes");
			document.fm.target = "MENUNAV";
			document.fm.action = "menunav.action";
			document.fm.submit();
			owin.focus();
		}
	</script>
		</div>
  </body>
</html>
