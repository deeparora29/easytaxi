$(function(){
var i=0;
var imagesPageWidth=$(".windows_btn").width();
var imagesItem=$(".windows_btn p img").size();
var pageItem=Math.ceil(imagesItem/5);
var imagesAllWidth=imagesPageWidth*pageItem;
$(".windows_btn p").css("width",imagesAllWidth);

moveNext=function(){
	$(".windows_btn p").animate({marginLeft:-imagesPageWidth*i+6},500);
}
movePrev=function(){
	$(".windows_btn p").animate({marginLeft:-imagesPageWidth*i+6},500);
}
$(".next").click(function(){
	i++;
	if(i==pageItem) i=pageItem-1;
	moveNext();
})
$(".prev").click(function(){
	i--;
	if(i<0) i=0;
	movePrev();
})
var o=1;
var imagesPageWidth2=$(".windows_con p img").width();
var imagesItem2=$(".windows_con p img").size()+1;
var imagesAllWidth2=imagesItem2*imagesPageWidth2;
$(".windows_con p").css("width",imagesAllWidth2);

$(".windows_btn p a").click(function(){
o=$(this).attr("rel")-1;
if(o==imagesItem2) o=imagesItem2-1;
$(".windows_con p").animate({marginLeft:-(imagesPageWidth2+2)*o},2000);
})

/*活动分类*/
$(".tab_hd a:first").addClass("currentTabHd");
$(".con_left8 div:eq(1)").addClass("visible");
$(".tab_hd a").hover(function(){
	$(".tab_hd a").removeClass("currentTabHd");
	$(".con_left8 div").removeClass("visible");
	$(this).addClass("currentTabHd");
	m="tablist00"+$(".tab_hd a.currentTabHd").attr("rel");
	$("."+m).addClass("visible");
})























})