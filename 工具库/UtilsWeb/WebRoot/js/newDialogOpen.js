$(function() {
	//添加数据
	$("#button").click(function(){
		$("#paramDialog").dialog('open');
		//$(".ui-widget-overlay").html("<iframe src=\"\" style=\"position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';\"></iframe>");
		
});
	var button4 = {};
	button4['取消'] = function() { $("#paramDialog").dialog('close'); }
	button4['确认'] = function() { 
		var objId = document.getElementById("textId");
		var objName = document.getElementById("textContext");
		var radioObjArr = document.getElementsByName("test");
		for(i = 0; i < radioObjArr.length; i++){
			if(radioObjArr[i].checked){	
				var s = radioObjArr[i].value;                     		
			if(s != ""){
				var arr = s.split("~");
				objId.value = arr[0];
				objName.value = arr[1];
				}
				break;
			}
		}
		$("#paramDialog").dialog('close'); }

	$("#paramDialog").dialog({
		bgiframe: true,
		autoOpen: false,
		resizable: false,
		height:250,
		width:500,
		modal: true,
		buttons: button4
	}).bind( "", false, function( e, ui ) {
        $(".ui-widget-overlay").decorateIframe();//遮盖层，可以遮盖住select等
    });

});