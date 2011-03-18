<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>   
<head>   
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme/tab_main.css" type="text/css" media="screen">
<script src="${pageContext.request.contextPath}/js/jquery/jquery.js" type="text/javascript"></script>
<script type="text/javascript">    
  
function validatorloginName(){   

   
  var loginName = $('#loginName').val();   
  $.ajax({   
        type: "POST",       
         url: "../VaildateName",       
          data: "loginName="+loginName,    
          success: function(result){     
                   if(result=="false"){   
                   alert("用户名可用");  
   
                    }else{   
                   alert("用户名已存在");   
 
                    }   
                 }    
           
               });           
     
}      


  
   
</script>
</head>
  



<body>  
 
<h1 align="center">后台重复性验证</h1>
<table class="table_input" align="center">    
<tr>
<td class="lable">检验StaffInfo中的staffName字段:</td>
<td>
<input class=text id="loginName" title="通行证用户名" size=18  name="loginName" >   
</td>
<td>    
<input id="confirm" type="button" value="检查是否可用" onclick="validatorloginName();" class="button"/>      
</td>
</tr>
</table>



   
  
</body>   
  
</html>  
