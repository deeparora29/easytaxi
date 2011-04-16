/**
 * for login.jsp
 */

$("#account").focusin(function(){
	$("#account").val("");					  
});

$("#account").focusout(function(){
		if($("").attr("value")==''){
		$("#account").val("您的账号");					  
		}
});