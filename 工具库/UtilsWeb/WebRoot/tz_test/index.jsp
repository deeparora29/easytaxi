<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>   
<head>
<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/simpletool.js"></script> 
	  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme/tab_main.css" type="text/css" media="screen">
<script src="${pageContext.request.contextPath}/js/jquery/jquery.js" type="text/javascript"></script>
<script type="text/javascript">    
  


function add(){
		 
			 var oldtarget = document.all.simplepage.target;
		    var oldaction = document.all.simplepage.action;
		    var owin = window.open("about:blank","ADD","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
			document.all.simplepage.target = "ADD";
			document.all.simplepage.action = "${pageContext.request.contextPath}/tz_test/MyJsp.jsp";
			document.all.simplepage.submit();
			document.all.simplepage.target = oldtarget;
			document.all.simplepage.action = oldaction;
			owin.focus();
			
		}	
  function mod(){
		 
			 var oldtarget = document.all.simplepage.target;
		    var oldaction = document.all.simplepage.action;
		    var owin = window.open("about:blank","MOD","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
			document.all.simplepage.target = "MOD";
			document.all.simplepage.action = "${pageContext.request.contextPath}/tz_test/MyJsp.jsp";
			document.all.simplepage.submit();
			document.all.simplepage.target = oldtarget;
			document.all.simplepage.action = oldaction;
			owin.focus();
			
		}
		
function del(){
		 
			 var oldtarget = document.all.simplepage.target;
		    var oldaction = document.all.simplepage.action;
		    var owin = window.open("about:blank","MOD","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
			document.all.simplepage.target = "MOD";
			document.all.simplepage.action = "${pageContext.request.contextPath}/tz_test/MyJsp.jsp";
			document.all.simplepage.submit();
			document.all.simplepage.target = oldtarget;
			document.all.simplepage.action = oldaction;
			owin.focus();
			
		}				
   
</script>
</head>
  


<body>  
 <div id="toolbar">
	        
		<div class="bar">
			<a href="#" onclick="add();"><img src="/UtilsWeb/images/xinzheng.gif" width="14" height="15" hspace="2" vspace="6" border="0" align="absmiddle" />新增</a>
			<a href="#" onclick="mod();"><img src="/UtilsWeb/images/xiugai.gif" width="14" height="15" hspace="2" vspace="6" border="0" align="absmiddle" />修改</a>
		</div>
		
		<div class="bar">
			<a href="#" onclick="del();"><img src="/UtilsWeb/images/shanchu.gif" width="14" height="15" hspace="2" vspace="6" border="0" align="absmiddle" />删除</a>
		</div>
		

	    </div>
 
 <form id="myform" method="post"action="#" name="simplepage"> 

</body>   
  
</html>  
