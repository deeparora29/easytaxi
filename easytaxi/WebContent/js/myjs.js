$(function(){
$(".input_ss").focusin(function(){
$(".input_ss").val("");					  
});
$(".input_ss").focusout(function(){
	if($("").attr("value")==''){
	$(".input_ss").val("请输入关键字");					  
	}
});


/*导航搜索下拉菜单*/		   
$(".down").click(function(){
$(".selectcon ul").slideToggle(100)
},function(){
$(".selectcon ul").slideUp("fast");
});

$(".selectcon ul li a").click(function(){
$(".down").html($(this).html());
$(".selectcon ul").slideUp("fast");
});

$(".down").focusout(function(){
$(".selectcon ul").slideUp("fast");
});

/*登录默认*/		   
$(".df0").focusin(function(){
$(".df0").val("");					  
});
$(".df0").focusout(function(){
	if($(".df0").attr("value")==''){
	$(".df0").val("您的邮箱/用户名");					  
	}
});

/*景点评论默认值*/
$(".fbpinglun textarea").val("文明上网，杜绝谩骂！")
$(".fbpinglun textarea").focusin(function(){
$(".fbpinglun textarea").val('');	   
})

$(".fbpinglun textarea").focusout(function(){
	if($(".fbpinglun textarea").val()==''){
	$(".fbpinglun textarea").val("文明上网，杜绝谩骂！");		   
	}										   
})


/*我的耍窝top下拉菜单*/
$(".xialacd li").hover(function(){
   $(this).find(".layer").slideToggle(500)
},function(){
  $(this).find(".layer").slideUp();
});

































})







