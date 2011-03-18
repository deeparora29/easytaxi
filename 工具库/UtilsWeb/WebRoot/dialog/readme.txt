此目录页面用于做工具库-弹出回传数据处理框

用到了jquery的dialog弹出框
改功能的核心js为dialogOpen.js：定义了弹出框的大小，以及弹出框的iframe中的页面，点击确定按钮触发的js函数方法。
修改说明：
1.弹出页面链接的修改：$("#paramDialog .dialogDiv").append('<iframe id="selectParam"  name="selectParam" height="372"  width="750" src="paramOpen.html" BORDER=0 FRAMEBORDER=NO ARGINHEIGHT=0 MARGINWIDTH=0 NORESIZE=NORESIZE SCROLLING=auto></iframe>');
将其中的iframe的src修改一下即可；
2.点击确定按钮触发事件的修改：
//点击确定
    $("#btnSubmit").click( function() {
    	var obj = document.getElementById("selectParam");					
			var result = obj.contentWindow.getParam();//调用iframe页面中的js方法		
			paramDialog.dialog( "close" );
    });

更多情况请登录http://jqueryui.net/dialog/查阅