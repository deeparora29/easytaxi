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
	
});

function isEmpty(formId, id, zhDesc){
 	id = formId + " " + id;
	if($(id).val() == ""){
		showErrorInfo(formId, zhDesc + "不能为空!");
		$(id).focus();
		return true;
	}
	return false;
}

function showErrorInfo(formId, msg){
	var errorid = formId + " #errorInfo";
	$(errorid).html(msg);
	$(errorid).addClass("visible");
}
