$(function() {
		//定义按钮名称及功能
		var alertButton = {};
		alertButton['确认'] = function() { $("#alertDialog").dialog('close'); }
		
		$("#alertDialog").dialog({
			autoOpen: false,
			bgiframe: true,
			height: 200,
			modal: true,
			buttons:alertButton
		});
		
		$("#alertButton").click(function(){
			$("#alertDialog").dialog('open');
		});
		
		//定义按钮名称及功能
		var confirmButton = {};
		confirmButton['取消'] = function() { alert("您点击了取消"); $("#confirmDialog").dialog('close'); }
		confirmButton['确认'] = function() { alert("您点击了确定"); $("#confirmDialog").dialog('close'); }
		
		$("#confirmDialog").dialog({
			autoOpen: false,
			bgiframe: true,
			height: 200,
			width:400,
			modal: true,
			buttons:confirmButton
		});
		
		$("#confirmButton").click(function(){
			$("#confirmDialog").dialog('open');
		});
	});