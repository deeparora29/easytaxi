

<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
<%@ page import="java.io.File" %>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<html> 
  <head> 
   <title>download</title> 
          <link rel="stylesheet" href="../css/theme/tab_main.css" type="text/css" media="screen">
<script type="text/javascript" src="../js/jquery/jquery.js"></script>
<script type="text/javascript" src="../js/comm.js"></script>
  </head> 
  <body> 
  <h1 align="center">文件下载演示</h1><p></p><p></p><p></p><p></p>
  <table boder="0" align="center">
    <h3>
   <% 
    //取得服务器"/download/file"目录的物理路径 
    String path = request.getRealPath("/file/upload"); 
    //取得"/download/file"目录的file对象 
    File file = new File(path); 
    //取得file目录下所有文件 
    File[] files = file.listFiles(); 
    if(files == null || files.length == 0){
    	out.println("/file/upload目录下没有文件可以下载！");
    	return;
    }
  
   for (int i = 0; i < files.length; i++) { 
  
    String fname = files[i].getName(); 
  
    //对文件名进行url编码(UTF-8指明fname原来的编码，UTF-8一般由本地编码GBK代替) 
     fname = java.net.URLEncoder.encode(fname, "UTF-8"); 
  
    out.println("<a href=download.action?fileName=" + fname + ">" 
     + files[i].getName() + "</a><br>"); 
    } 
    %></h3> </table>
   <a href="upload.jsp">文件上传</a>
  </body> 
 </html>