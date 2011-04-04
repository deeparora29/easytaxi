<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Sqlite</title>
<style>
  span { color:red; }
  </style>
<%@ include file="/include.jsp" %>

</head>
<body>
<form action="<%=rootCtx %>/TestSqliteServlet" method="post">
<table align="center">
<tr>
		<td colspan="2" align="center">Input to Sqlite</td> </tr>
	<tr>
	<td align="right">Name:</td>
	<td><input type="text" maxlength="20" id="name" name="name"></input>
	</td>
	</tr>
	<tr>
	<td align="right">Occupation:</td>
	<td><input type="text" maxlength="30" id="occupation" name="occupation"></input></td>
	</tr>
	<tr><td colspan="2" align="center">
	<input type="submit" value="Submit"></input> &nbsp;&nbsp;
	<input type="reset" value="Reset"></input> 
	</td></tr>
	<tr><td colspan="2" align="center">
	<span></span>
		</td></tr>
</table>
</form>
<script type="text/javascript">
	$("form").submit(function() {
		if(jQuery.trim($("#name").val()).length == 0){
			$("span").text("Empty Name!").show().fadeOut(2000);
			$("#name").val("");$("#name").focus();
			return false;
		}

		if(jQuery.trim($("#occupation").val()).length == 0){
			$("span").text("Empty Occupation!").show().fadeOut(2000);
			$("#occupation").val("");$("#occupation").focus();
			return false;
		}
		return true;
	});
</script>
</body>
</html>