<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>文件上传测试</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme/tab_main.css" type="text/css" media="screen">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>

    </head>
    <body>
    <h1 align="center">文件和图片上传演示</h1><p></p><p></p><p></p><p></p>
    	<s:fielderror />
        <s:form action="fileup.action" method="post" id="uploadform" name="uploadform" enctype="multipart/form-data" 
        
         theme="simple"     namespace="/file">
               <table class="table_input" align="center">
               <tr>
		<td class="lable">
           文件上传：</td>
           <td> <s:file name="file"></s:file></td></tr>
             <!-- tr><td class="lable">图片预览：</td>
            <td><img id="yu" src="" height="60" width="80" border="0" style="display: none" ></td></tr -->
           <tr>
           <td class="lable">图片上传：</td><td > <s:file name="file" id="tu" ></s:file></td></tr>

           <tr ><td align="center" colspan="2"><!-- input type="button" onclick="a();" class="button_HightLight"  value="预览图片" -->&nbsp;&nbsp;
           <input type="submit" value=" 提交 " class="button">
           
           </td></tr></table>

        </s:form>
    </body>
    <div align=center> 
    
   <table boder="0" align="center">   <s:iterator value="fileFileName" status="stuts"> 
       
      <tr> 
      <s:iterator value="fileFileName[#stuts.index]" > 
       <td height="30px"> 
       <s:property/> 
       </td> 
      </s:iterator> 
      </tr> 
     </s:iterator>
    
    <tr><td>
  <s:property value="message" />
  </td></tr>
  <tr><td>
  <input type="button" class="button" value="下载" onclick="location.href='download.jsp'">
  </td></tr>
  </table></div>
    
    <script type="text/javascript">
    function a(){
    if(document.getElementById("tu").value!=""){
    document.getElementById("yu").style.display = "block";
    document.getElementById("yu").src=document.getElementById("tu").value;}
    }
    </script>
    
</html>