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
	
$("#account").focusin(function(){
	$("#account").val("");					  
});

$("#account").focusout(function(){
		if($("#account").attr("value")==''){
			$("#account").val(ShowMsg[tabIndex]);					  
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

});