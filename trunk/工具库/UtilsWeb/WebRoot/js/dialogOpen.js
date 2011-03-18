/************************
**用于弹出窗口，并进行返回结果。

依靠组件

UI Core

Draggable

Resizable

bgiframe

*************************/

( function() {
    var loadFrameOne=0;//设置一个标签，其作用是在用户调用一次该方法后，就不需要再生成一个重复的dialog
   //定义dialog
    var paramDialog = $( "#paramDialog" ).dialog({	//设置 id 为 paramDialog 的div标签的属性
        dialogClass: "paramDialog",					//添加额外的对话框 CSS Class
        autoOpen: false,							//实例化时是否自动显示对话框。设置为 false 时，使用 open 方法显示对话框
        width: 800,									//设置div的宽度
        height: 460,								//设置div的高度
        modal: true,								//是否为模态窗口，设置为 true 时，页面上其它元素将被覆盖且无法响应用户操作
		resizable:true,
		overlay: {		
            opacity: 0.5,							//设置透明度
            background: "black"						//设置透明颜色
        },
        position: "center",							//居中
        hide: "slow",								//设置隐藏运动样式
        show: "fast"								//设置弹出运动样式
    }).bind( "", false, function( e, ui ) {
        $( ".ui-dialog-overlay" ).decorateIframe();	//将具备.ui-dialog-overlay这个样式的DOM对象附上iframe的特有样式
    });
	//dialog定义结束

    //点击选择
    $( "#btnSelect" ).click( function() {
    if(loadFrameOne==0){//如果用户是第一次调用该方法,就会生成一个iframe,而再次调用,就只需要将此div show出来就可以了
    	$("#paramDialog .dialogDiv").append('<iframe id="selectParam"  name="selectParam" height="372"  width="750" src="paramOpen.html" BORDER=0 FRAMEBORDER=NO ARGINHEIGHT=0 MARGINWIDTH=0 NORESIZE=NORESIZE SCROLLING=auto></iframe>');
    	$('.btnComplaint').appendTo('#paramDialog .dialogDiv');		//将id为paramDialog的 dialogDiv元素 放到class为btnComplaint里面
    	document.getElementById("dialogBtnGroup").style.display="";	//将id为dialogBtnGroup的DIV显示出来
    	loadFrameOne=1;												//将读取标签设置为1
    }															
        paramDialog.dialog( "open" );								//如果不是第一次读取直接show出来
    });
    //点击确定
    $("#btnSubmit").click( function() {
    	var obj = document.getElementById("selectParam");					
			var result = obj.contentWindow.getParam();				//调用iframe页面中的js方法		
			paramDialog.dialog( "close" );							//hidden DIV
    });
    //点击关闭
    $( "#btnCancel" ).click( function() {
        paramDialog.dialog( "close" );								//直接hidden
    });
})( jQuery );