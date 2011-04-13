$(function(){
//$(".jj_tt img").reflect({height:0.5,opacity:0.4});
$(".jj_tt p a").fadeTo(0,0.6)
$(".jj_tt p a:first").addClass("currentBg7");
$(".jj_tt p a:first").fadeTo(0,1)
$(".jj_con p").addClass("hidden");
$(".jj_con p").fadeIn(500);

var s=0;
var windowsjj=$(".jj_con").height();
var imagesHeightjj=$(".jj_con p a img").size()*windowsjj;
$(".jj_con p").css("height",imagesHeightjj)
rotateSwitch();
function rotateSwitch(){
	play=setInterval(function(){
		$current=$(".jj_tt p a.currentBg7").next();
		if($current.length==0){
		$current=$(".jj_tt p a:first");
		}
		rotate();
		},4000);
}


function rotate(){
	var triggerID=$current.attr("rel")-1;
	var imageAllPosition=triggerID*windowsjj;
	$(".jj_tt p a").removeClass("currentBg7");
	$(".jj_tt p a").fadeTo(0,0.6)
	$current.addClass("currentBg7");
	$current.fadeTo(0,1)
	$(".jj_con p").animate({"margin-top":-imageAllPosition},500)
}
	
$(".jj_tt p a").click(function(){
	$current=$(this);
	clearInterval(play);
	$(".jj_tt p a").removeClass("currentBg7");
	$(this).addClass("currentBg7");
	$(".jj_tt p a").fadeTo(0,0.6)
	$(this).fadeTo(0,1)
	var s=$(this).attr("rel")-1;
	$(".jj_con p").animate({"margin-top":-windowsjj*s},500)
},500);


$(".jj_tt p a").hover(function(){
	clearInterval(play);
},function(){
	rotate();
	rotateSwitch();
})

$(".jj_con p").hover(function(){
	clearInterval(play);
},function(){
	rotate();
	rotateSwitch();
})

/*景点排行*/
$(".top_jd p a:first").addClass("currentTopBg");
$(".top_jd ul:first").addClass("visible");
$(".top_jd p a").hover(function(){
	$(".top_jd p a").removeClass("currentTopBg");
	$(".top_jd ul").removeClass("visible");
	$(this).addClass("currentTopBg");
	m="jd00"+$(".top_jd p a.currentTopBg").attr("rel");
	$("."+m).addClass("visible");
})

/**********************************************************旅游二级页面*/
$(".jd_gonglve h2 span > a:first").addClass("currentBg10");
$(".jd_gonglve .glpx:first").addClass("visible");
$(".jd_gonglve h2 span > a").hover(function(){
$(".jd_gonglve h2 span > a").removeClass("currentBg10");
$(".jd_gonglve .glpx").removeClass("visible");
$(this).addClass("currentBg10");
q="px00"+$(this).attr("rel");
$("."+q).addClass("visible");
})

/*景点首页*/
$(".dq_wenzhang h2 span:first").addClass("currentBg10");
$(".fbglwz:first").addClass("visible");
$(".dq_wenzhang h2 span").hover(function(){
$(".dq_wenzhang h2 span").removeClass("currentBg10");
$(".fbglwz").removeClass("visible");
$(this).addClass("currentBg10");
r="fbgl00"+$(this).attr("rel");
$("."+r).addClass("visible");
})

/*线路文章*/
$(".jd_xianlu h2 span > a:first").addClass("currentBg10");
$(".jd_xianlu .xlwz:first").addClass("visible");
$(".jd_xianlu h2 span > a").hover(function(){
$(".jd_xianlu h2 span > a").removeClass("currentBg10");
$(".jd_xianlu .xlwz").removeClass("visible");
$(this).addClass("currentBg10");
s="xlwz00"+$(this).attr("rel");
$("."+s).addClass("visible");
})

/*游记文章*/
$(".jd_jouji h2 span > a:first").addClass("currentBg10");
$(".jd_jouji .yjwz:first").addClass("visible");
$(".jd_jouji h2 span > a").hover(function(){
$(".jd_jouji h2 span > a").removeClass("currentBg10");
$(".jd_jouji .yjwz").removeClass("visible");
$(this).addClass("currentBg10");
t="yjwz00"+$(this).attr("rel");
$("."+t).addClass("visible");
})





















})