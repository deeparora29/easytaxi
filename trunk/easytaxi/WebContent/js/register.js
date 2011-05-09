/**
 * for register.jsp
 */

$(function(){
	var TYPE = new Array("passenger", "taxi");
	var tabIndex = 0;
	
	$(".jd_gonglve h2 span > a:first").addClass("currentBg10");
	$(".jd_gonglve .glpx:first").addClass("visible");
	$(".jd_gonglve h2 span > a").click(function(){
		$(".jd_gonglve h2 span > a").removeClass("currentBg10");
		$(".jd_gonglve .glpx").removeClass("visible");
		$(this).addClass("currentBg10");
		q="px00"+$(this).attr("rel");
		$("."+q).addClass("visible");
		tabIndex = $(this).attr("rel");
		$("#type").val(TYPE[tabIndex]);
	})
	
	$("#passengerForm #okBtn").click(function(){
			if(isEmpty("#passengerForm", "#email", "邮箱"))
				return;
			if(isEmpty("#passengerForm", "#phone", "电话号码"))
				return;
			if(isEmpty("#passengerForm", "#password", "密码"))
				return;
			$("#passengerForm").submit();
	});
	
	$("#taxiForm #okBtn").click(function(){
			if(isEmpty("#taxiForm", "#cab", "出租车牌号"))
				return;
			else if($("#taxiForm #cab").val().length > 10){
				showErrorInfo("#taxiForm", "出租车牌号太长，超过10个字符");
				return;
			}
			if(isEmpty("#taxiForm", "#password", "密码"))
				return;
			$("#taxiForm").submit();
	});
	
	$("#passengerForm #email").blur(function(){
		if(!isEmpty("#passengerForm", "#email", "邮箱")){
			checkOnServer("#passengerForm", "#email", "邮箱");
		}
		
	});
	
	$("#passengerForm #phone").blur(function(){
		if(!isEmpty("#passengerForm", "#phone", "电话号码")){
			checkOnServer("#passengerForm", "#phone", "电话号码");
		}
	});
	
	$("#taxiForm #cab").blur(function(){
		if(!isEmpty("#taxiForm", "#cab", "出租车牌号")){
			checkOnServer("#taxiForm", "#cab", "出租车牌号");
		}
	});
	
	
	
});

function isEmpty(formId, id, zhDesc){
 	id = formId + " " + id;
	if($.trim($(id).val()) == ""){
		showErrorInfo(formId, zhDesc + "不能为空!");
		$(id).focus();
		return true;
	} else {
		showErrorInfo(formId, "");
	}
	return false;
}

function showErrorInfo(formId, msg){
	var errorid = formId + " #errorInfo";
	$(errorid).html(msg);
	$(errorid).addClass("visible");
}

function checkOnServer(formId, id, descr){
	var objectid = formId + " " + id;
	var value = $.trim($(objectid).val());
	var url = "RegisterServlet?type=check&formId=" 
				+ formId.substring(1) + "&objectId=" + id.substring(1) + "&objectValue=" + value;
//	var url = "RegisterServlet";
//	alert(url);
	$.ajax( {
			type :"get",
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			url : url,
			cache : false,
//			data: "type=check&formId=" 
//				+ formId.substring(1) + "&objectId=" + id.substring(1) + "&objectValue=" + value + "&descr=" + descr,
//			dataType: "json",
			//error: function(){ alert("错误！请联系管理员。"); },
			success : function( data ) {
				var dataObj=eval("("+data+")");
				if(dataObj.result == "ok"){
					showErrorInfo(formId, "");
				} else {
					showErrorInfo(formId, descr + "已经存在！");
					$(objectid).select();
				}
			}
		});
		
}
