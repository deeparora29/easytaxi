$(function(){
/*个人设置弹出层*/
$(".user_set").hover(function(){
   $(this).find('.dangan').slideToggle(500)
   //slideDown(),slideUp() 此方法实现向下、向上动画实现下拉菜单的感觉。
},function(){
  $(this).find('.dangan').slideUp();
});
/*搜索找人初始值*/
$(".input0").focusin(function(){
$(".input0").val("");					  
});
$(".input0").focusout(function(){
	if($(".input0").attr("value")==''){
	$(".input0").val("请输入你要搜索的人…");					  
	}
});
/*心情记录*/
$(".jlxq").focusin(function(){
$(".jlxq").val("");					  
});
$(".jlxq").focusout(function(){
	if($(".jlxq").attr("value")==''){
	$(".jlxq").val("今天有什么要说的吗？不防记录下来与好友分享吧。");					  
	}
});
/*动态选项卡*/
//初始化
$(".tab h2 a:first").addClass("currentBg_sw");
$(".tab .tabContent:first").addClass("visible");
$(".tab .tabContent div:first").addClass("visible");

$(".tab h2 a").click(function(){
							  
var dd="dnr00"+$(this).attr("rel");
var xx="xnr001";
var subtt=dd+" "+"h3"+" "+"a:first";

$(".tab h2 a").removeClass("currentBg_sw");
$(".tab .tabContent").removeClass("visible");
$(".tab .tabContent div").removeClass("visible");
$(".tabContent"+" "+"h3"+" "+"a").removeClass("currentBg_sw2");

$(this).addClass("currentBg_sw");
$("."+dd).addClass("visible");
$("."+xx).addClass("visible");
$("."+subtt).addClass("currentBg_sw2");

$("."+dd+" "+"h3"+" "+"a").click(function(){
	$(".tabContent"+" "+"h3"+" "+"a").removeClass("currentBg_sw2");
	$(this).addClass("currentBg_sw2");
	$(".tabContent"+" "+"div").removeClass("visible");
	
	var x0="xnr00"+$(this).attr("rel");
	//alert(dd+" "+x0);
	$("."+dd+" "+"."+x0).addClass("visible");
	})
})































})