/**
 * for editTable.jsp
 * 用于可编辑表格
 * @author renmian
 **/
//获取ContextPath
function getDomain(){
	str=window.location.pathname;
	arr=str.split("/");
        if (arr[0] == ""){
          return "/" + arr[1];
        }
        else{
          return "/" + arr[0];
        }
}
$(function(){
	//增加表单项载入时隐藏，待点击增加时显示，再点击保存时又进入隐藏并用Ajax提交内容与更新页面
	$("#adddiv").hide();//隐藏增加内容的输入区域
	$("#addBtn").click(function(){//点击增加后清空原有的内容，显示增加的输入界面
	$("#num").val("");
	$("#name").val("");
	$("#depart").val("");
	$("#adddiv").show();
	});
	$("#not").click(function(){//取消增加后把所填内容清空，隐藏区域
	$("#adddiv").hide();
	});
	$("#add").click(function(){//输入内容点保存后取各项值增加到上面表格中再用ajax传到后台，如没填完全则提示填写完全,最后隐藏
	if($("#num").val()!=""&&$("#name").val()!=""&&$("#depart").val()!=""){
	 staffId=$("#num").val();
	 
	 staffName=$("#name").val();
	 departName=$("#depart").val();
	 //增加时后台增加成功后页面相应更新
	 var tr=$("<tr align='center'><td><input type='radio' name='preStaffId'/></td><td>"+staffId+"</td><td>"+staffName+"</td><td>"+departName+"</td></tr>");
	  tr.appendTo($("#editableTable").find("#ec_table tbody")); 
	  $("#adddiv").hide();
	 doAjaxAdd(staffId,staffName,departName);
		alert("新增成功");
	}
	else{alert("请输入要增加的记录明细");}
	});
	
	// 双击单元格时，使得该单元格可修改，修改过的数据用不用颜色以示区别 
	$("#editableTable").find("#ec_table tbody tr td").each(function(i){	
		if(i % 4 != 0 && i % 4 != 1){ //第一列不允许修改，假设其为此条记录的主键
	        $(this).dblclick(function(){

	        	var staffId,staffName,departName;
	        	
	        	var nullFlag = true; //用于细项序号以及信息是否填写完整
	        	var tdObj = $(this);
	        	var souText = tdObj.text();   // 原来的内容
	    
	        tdObj.css("text-valign","middle");
	        var input = $("<input type='text' value='" + souText + "' style='height:17px;margin-top:0px;vertical-align: middle;' />");
	        // 设置文本框的宽
	        input.width(tdObj.width()-10);
	        // 设置文本框的点击事件
	        input.click(function() {
	        return false;
	        });
	        // 设置文本框的样式
	        input.css({ "border-width": "1px", "border-color" : "red"});
	        input.css("margin-bottom","3px");
	        input.css("margin-left","3px");
	        
	        tdObj.html(input);
	        // 触发文本框的focus事件后再触发select事件
	        input.trigger("focus").trigger("select");
	        
	       
	        
	        // 文件框的焦点失去事件, 把文本框中填写的内容变成
	        input.blur(function() {
	          
	          if(input.val()!=""){
	           tdObj.html(input.val());
	            nullFlag = true;
	          } 
	          
	          switch(i%4){
	        
//	          case 0:
//	          
//	            staffId = tdObj.text();
//	            staffName = tdObj.next().text();
//	            departName = tdObj.next().next().text();
//	            alert(staffId);
//	            if (isNaN(staffId)) {
//	               alert("员工工号必须为整数！");
//	               nullFlag = false;
//	             }
//	     
//	            break;
	          case 2:
	            
	        	  staffId = tdObj.prev().text();
	        	  staffName = tdObj.text();
	        	  departName = tdObj.next().text();
	        	  break;
	            
	          case 3:
	            
	        	  staffId = tdObj.prev().prev().text();
	        	  staffName = tdObj.prev().text();
	        	  departName = tdObj.text();
	        	  break;
	         }
	        
	        //判断修改后是否为空。若为空，不提交，并提示用户重新输入
	        if(tdObj.text() == ""){
	        
	           alert("修改项不能为空,请重新填写!");
	           nullFlag = false;
	           input.focus();
	        }
	        //对修改过的td加背景色，以示区别　add　by  linxy@2009.1.16
	          if(tdObj.text()!=souText && nullFlag == true){
	        
	           tdObj.css("background","#fff88c");
	           //在这里采用ajax技术进行数据的提交
	           doAjaxUpdate(staffId, staffName, departName);
			   tdObj.css("background","#ffffff");
	          }
	        });
	        
	        
	      
	        // 处理文本框中键盘按键事件, Enter 13确认,Esc 27取消
	        input.keydown(function(event) {
	        var keyCode = event.keyCode;
	        if (keyCode == 13) {
	            // 按下回车
	            if(input.val()!=""){
	               tdObj.html(input.val());
	               nullFlag = true;
	            }
	            
	            switch(i%4){
		        
//		          case 0:
//		          
//		            staffId = tdObj.text();
//		            staffName = tdObj.next().text();
//		            departName = tdObj.next().next().text();
//		            if (isNaN(staffId)) {
//			               alert("员工工号必须为整数！");
//			               nullFlag = false;
//			        }
//		            break;
		          case 2:
		            
		        	  staffId = tdObj.prev().text();
		        	  staffName = tdObj.text();
		        	  departName = tdObj.next().text();
		        	  break;
		            
		          case 3:
		            
		        	  staffId = tdObj.prev().prev().text();
		        	  staffName = tdObj.prev().text();
		        	  departName = tdObj.text();
		        	  break;
		         }
	            
	            //对修改过的td加背景色，以示区别
	            if(tdObj.text()!=souText && nullFlag == true){
	              tdObj.css("background","#fff88c");
	              //ajax异步提交
	              doAjaxUpdate(staffId, staffName, departName);
	              tdObj.css("background","#ffffff");
	            }
	        }
	        if (keyCode == 27) {
	            // 按下Esc
	            tdObj.html(souText);
	        }
	 
	   });
	   
	  });
	      
		}  
	});
	
	$("#delBtn").click(function(){
		if(confirm("确定要删除此条记录吗?")){
		     

		var staffId=$("input[name=preStaffId][checked]").val();

			if(!staffId || staffId == ""){
				alert("请确定您要删除的文件");
				return false;
			}else{
				doAjaxDelete(staffId);
					//此处为后台删除成功时同步删除页面相应内容。以免造成重复删除。
		$("input[name=preStaffId][checked]").parent().parent().remove();
			}
		
		}
	});

});

//ajax提交时最好设定编码，否则会出现乱码问题
function doAjaxUpdate(staffId, staffName, departName){
	alert("update New Data!");
	$.ajax({
	 	type: "POST",
	 	url: getDomain() + "/UpdateTableDataServlet",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		data: "staffId=" + staffId +"&staffName=" + staffName + "&departName=" + departName,
		async: false
	});
}

function doAjaxDelete(staffId){
	alert("delete The Data!");
	
	 $.ajax({
	 				type: "POST",
	 				url: getDomain() + "/DeleteTableDataServlet",
	 				data: "staffId=" + staffId,
	 				async: false,
	 				success:function(msg){alert(msg);}
				 });
	//alert(result);
	//window.location.href.refresh();
}
//ajax提交时最好设定编码，否则会出现乱码问题
function doAjaxAdd(staffId,staffName,departName){
 	alert("add New Date");
 	$.ajax({
	 	type: "POST",
	 	url: getDomain() + "/AddTableDataServlet",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		data: "staffId=" + staffId +"&staffName=" + staffName + "&departName=" + departName,
	    async: false
	});
}
