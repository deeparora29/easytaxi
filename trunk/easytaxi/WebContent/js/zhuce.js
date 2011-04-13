$(function(){
/*注册成功全选反选*/
$(":checkbox").attr("checked",false);
$(".lianxiren h4 :checkbox").click(function(){
if(this.checked){  
$(".lianxiren input[name='checkbox']").each(function(){this.checked=true;});
$(this).attr("value","反选");  
}else{  
	$(".lianxiren input[name='checkbox']").each(function(){this.checked=false;});  
	$(this).attr("value","不全选");
}  
}); 



$(".lxr_ll h4 :checkbox").click(function(){  
if(this.checked){ 
$(".lxr_ll input[name='checkbox']").each(function(){this.checked=true;});
$(this).attr("value","反选");  
}else{  
	$(".lxr_ll input[name='checkbox']").each(function(){this.checked=false;});  
	$(this).attr("value","不全选");
}  
}); 

/*邀请好友默认值*/
$("textarea").val("想在耍巴适发出的邀请信中多加几句吗？")
$("textarea").focusin(function(){
$("textarea").val('');	   
})

$("textarea").focusout(function(){
	if($("textarea").val()==''){
	$("textarea").val("想在耍巴适发出的邀请信中多加几句吗？");		   
	}										   
})


/*超级搜索*/
$(".chushiss").addClass("visible")						 
$(".cjss").click(function(){
$(".chushiss").removeClass("visible");
$(".chaojiss").addClass("visible");
})
/*显示景点*/
$(".input8").focusin(function(){
$(".sslist").addClass("visible")
$(".wsd").addClass("visible")
})
/*消失景点*/
$(".input8").focusout(function(){
$(".sslist").removeClass("visible")
$(".wsd").removeClass("visible")
})
/*搜索景点*/
$(".ssjd_btn").click(function(){
$(".sslist").addClass("visible")
$(".wsd").removeClass("visible")
$(".ssd").addClass("visible")
})
/*选择景点*/
$(".ssd a").click(function(){
$(".chaojiss").removeClass("visible");
$(".xiugaiss").addClass("visible");
$("input[name='jingdian']").attr("value",$(this).html());
})
/*修改*/
$(".xgss").click(function(){
$(".xiugaiss").removeClass("visible");
$(".sslist").removeClass("visible")
$(".chaojiss").addClass("visible");
})









})