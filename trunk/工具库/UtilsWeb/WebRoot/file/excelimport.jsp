<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
        <title>Excel文件导入</title>
        
       <link rel="stylesheet" href="../css/theme/tab_main.css" type="text/css" media="screen">
<script type="text/javascript" src="../js/jquery/jquery.js"></script>
<script type="text/javascript" src="../js/comm.js"></script>
    </head>
    <body>
       <h1 align="center">Excel文件导入演示</h1><p></p><p></p><p></p><p></p>
        <s:form action="import.action" method="post" id="importform" name="importform" enctype="multipart/form-data" 
        
         theme="simple"     namespace="/file">
         <table class="table_input" align="center">
	<tr>
		<td class="lable"> Excel文件选择:</td>
		<td> <s:file name="file"  ></s:file></td>
	</tr>
           
          
          <tr><td colspan="2"><font color="red">格式说明：此表格分三列，第一行为表头，第一列须为数字类型，第二列为字符型，第三列为yyyy-mm-dd格式的日期类型！</font></td></tr>
           <tr><td colspan="2"><font color="red">注意：Excel文件仅支持Excel 97-2003的版本</font></td></tr>
             
          <tr><td colspan="2" align="center">  <input type="submit" value=" 上传验证 "  class="button_HightLight"></td></tr></table>
        </s:form>
        <div align="center">
        
        <table boder="0" align="center">
        <tr><td><s:property value="fileFileName"></s:property></td></tr>
        <s:iterator value="message" status="stuts"> 
       
      <tr> 
      <s:iterator value="message[#stuts.index]" > 
       <td height="30px"> 
       <s:property/> 
       </td> 
      </s:iterator> 
      </tr> 
     </s:iterator>
        </table>
        </div>
    </body>
    
    
    
</html>