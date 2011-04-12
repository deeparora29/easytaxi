<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript"><!--
	function callService(){
		var data = $("#data").val();
		$.ajax( {
			type :"POST",
			//contentType :"application/json;utf-8",
			url :"passenger",
			cache : false,
			dataType :"json",
			data : { data : data },
			success : function( msg) {
				alert(msg.ErrorCode);
			}
		});

		/*$.ajax( {
			type :"POST",
			url :"/realtimeMonitor/KpiReportServlet",
			data :data,
			cache :false,
			success : function(msg) {
				//alert(msg);
				getLastKpiReport();
			}
		});*/
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>接口参数内容</td>
			<td><textarea rows="8" cols="100" name="data" id="data"></textarea></td>
		</tr>
		<tr>
			<td><input type="button" name="t_button" value="CALL_SERVER" onclick="callService()"/></td>
		</tr>
	</table>
		<ul>
			<li>
				P001    {TransCode:'P001',phone:'13012345678',email:'anne.mian.ren@gmail.com',password:'123',
						nickname:'anne',firstname:'Ren',lastname:'Mian',gender:'female',province:'四川',	city:'成都',
						agreement:'yes',descr:'for test passenger'}
			</li>
		</ul>
</body>
</html>