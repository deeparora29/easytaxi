/**
 * for register.jsp
 */

$(function(){
	var TYPE = new Array("passenger", "taxi");
	var tabIndex = 0;
	var isSubmit = true;
	
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
			if($("#passengerForm #agreement").attr("checked") == false){
				showErrorInfo("#passengerForm", "您需要遵守同意耍巴适用户发布信息协议");
				return;
			}
		if(!isSubmit){
			return;
		}
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
			var email = $.trim($("#taxiForm #email").val());
			if (!isEmail(email)) {
				showErrorInfo("#taxiForm", "Email格式错误");
				$("#taxiForm #email").select();
				return;
			}
			
			var phone0 = $.trim($("#taxiForm #phone0").val());
			if(!ismobile(phone0)){
				showErrorInfo("#taxiForm", "手机号码格式错误");
				$("#taxiForm #phone0").select();
				return;
			}
		if(!isSubmit){
			return;
		}
			$("#taxiForm").submit();
	});
	
	$("#passengerForm #email").blur(function(){
		if(!isEmpty("#passengerForm", "#email", "邮箱")){
			var email = $.trim($("#passengerForm #email").val());
			if (!isEmail(email)) {
				showErrorInfo("#passengerForm", "Email格式错误");
				$("#passengerForm #email").select();
			} else {
				isSubmit = true;
				checkOnServer("#passengerForm", "#email", "邮箱");
			}
		}
		
	});
	
	$("#passengerForm #phone").blur(function(){
		if(!isEmpty("#passengerForm", "#phone", "电话号码")){
			var phone = $.trim($("#passengerForm #phone").val());
			if(!ismobile(phone)){
				showErrorInfo("#passengerForm", "手机号码格式错误");
				$("#passengerForm #phone").select();
			} else {
				isSubmit = true;
				checkOnServer("#passengerForm", "#phone", "电话号码");				
			}
		}
	});
	
	$("#taxiForm #cab").blur(function(){
		if(!isEmpty("#taxiForm", "#cab", "出租车牌号")){
			checkOnServer("#taxiForm", "#cab", "出租车牌号");
		}
	});
	
	$("#taxiForm #email").blur(function(){
		var email = $.trim($("#taxiForm #email").val());
			if (!isEmail(email)) {
				showErrorInfo("#taxiForm", "Email格式错误");
				$("#taxiForm #email").select();
			} else {
				isSubmit = true;
			}
	});
	
	$("#taxiForm #phone0").blur(function(){
		var phone0 = $.trim($("#taxiForm #phone0").val());
			if(!ismobile(phone0)){
				showErrorInfo("#taxiForm", "手机号码格式错误");
				$("#taxiForm #phone0").select();
			} else {
				isSubmit = true;
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
	isSubmit = (msg == "");
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
					isSubmit = true;
				} else {
					showErrorInfo(formId, descr + "已经存在！");
					$(objectid).select();
				}
			}
		});
		
}
