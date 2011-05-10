/**
 * for login.jsp
 */

$(function(){
		var TYPE = new Array("passenger", "taxi");
		var ShowMsg = new Array("您的邮箱/电话号码", "出租车牌号");
		var tabIndex = 0;
		
$(".jd_gonglve h2 span > a:first").addClass("currentBg10");
	$(".jd_gonglve .glpx:first").addClass("visible");
	$(".jd_gonglve h2 span > a").click(function(){
		$(".jd_gonglve h2 span > a").removeClass("currentBg10");
		$(".jd_gonglve .glpx").removeClass("visible");
		$(this).addClass("currentBg10");
		q="px00"+$(this).attr("rel");
		$("."+q).addClass("visible");
		tabIndex = $(this).attr("rel") - 1;
		$("#type").val(TYPE[tabIndex]);
		$("#account").val(ShowMsg[tabIndex]);	
	});
	
$("#account").focus(function(){
	$("#account").val("");					  
});

$("#account").blur(function(){
		if($("#account").attr("value")==''){
			$("#account").val(ShowMsg[tabIndex]);					  
		} else {
			checkOnServer($("#type").val(), "#account", ShowMsg[tabIndex]);
		}
});

$("#submitBtn").click(function(){
	if($("#account").val() == ShowMsg[tabIndex]){
		$("#errorInfo").html("请输入您的登录账号");
		$("#errorInfo").addClass("visible");
		$("#account").focusin();
		return false;
	}
	if($("#password").val() == ''){
		$("#errorInfo").html("请输入您的登录密码");
		$("#errorInfo").addClass("visible");
		$("#password").focus();
		return false;
	}
	$("#errorInfo").html("");
	$("#loginForm").attr("action", "LoginServlet?type=" + $("#type").val());
	$("#loginForm").submit();
});

function showErrorInfo(formId, msg){
	//alert(msg);
	var errorid = "#errorInfo";
	$(errorid).html(msg);
//	$(errorid).addClass("visible");
}

function checkOnServer(formId, id, descr){
	var objectid = id;
	var value = $.trim($(objectid).val());
	var url = "LoginServlet?type=check&formId=" + formId + "&objectValue=" + value;
	//alert(url);
	$.ajax({
		type: "get",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		url: url,
		cache: false,
		success: function(data){
			var dataObj = eval("(" + data + ")");
			if (dataObj.result == "ok") {
				showErrorInfo(formId, "");
			}
			else {
				showErrorInfo(formId, descr + "不存在！请先免费注册。");
				$(objectid).select();
			}
		}
	});
}

});