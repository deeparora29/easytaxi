$(function(){
$(".topleft li:even").addClass("libg");
$(".topleft li:odd").addClass("libg2");
$(".topleft ul li span").addClass("fColorGreen");
/*全站动态*/
$(".topleft ul:first").addClass("visible");
$(".topleft p a:first").addClass("currentBg2");

$(".topleft p a").hover(function(){
if($(this).attr("rel")!=0){
$(".topleft p a").removeClass("currentBg2");
$(".topleft ul").removeClass("visible");
$(this).addClass("currentBg2");
i="dt00"+$(this).attr("rel");
$("."+i).addClass("visible");
}else{
//alert("还未完善！")	
}
})

/*首页焦点图*/

$(".btn li:first").addClass("currentBg3");
var imageWidth=$(".window").width();
var imageItem=$(".focusTitle .content").size();
var imageAllWidth=imageWidth*imageItem;
$(".focusTitle").css("width",imageAllWidth);

rotate=function(){
	var triggerID=$current.attr("rel")-1;
	var imageAllPosition=triggerID*imageWidth;
	$(".btn li").removeClass("currentBg3");
	$current.addClass("currentBg3");
	$(".focusTitle").animate({left:-imageAllPosition},1000);
	}
rotateSwitch=function(){
	play=setInterval(function(){
		$current=$(".btn li.currentBg3").next();
		if($current.length==0){
			$current=$(".btn li:first");
			}
			rotate();
		},3000);
	};
rotateSwitch();
$(".focusTitle a").hover(function(){
	clearInterval(play);
	},function(){
		rotateSwitch();
		});
$(".btn li").hover(function(){
	clearInterval(play);
	$(".btn li").removeClass("currentBg3");
	$(this).addClass("currentBg3");
	var triggerID=$(this).attr("rel")-1;
	var imageAllPosition=triggerID*imageWidth;
	$(".focusTitle").animate({left:-imageAllPosition},1000);
},function(){
	rotateSwitch();
});

/*首页旅游热图*/
$(".trave_focus ul:first").addClass("visible");
$(".trave_focus p a:first").addClass("curt");
playCon=function(){
$(".trave_focus p a").removeClass("curt");
$current.addClass("curt");
}
playBtn=function(){
	play2=setInterval(function(){
		$current=$(".trave_focus p a.curt").next();
		m="tu0"+$(".trave_focus p a.curt").attr("rel");
		$(".trave_focus ul").removeClass("visible");
		$("."+m).next().addClass("visible");
		if($current.length==0){
			$current=$(".trave_focus p a:first");
			$(".trave_focus ul:first").addClass("visible");
		}
		playCon();
	},3000);
}
playBtn();
$(".trave_focus ul").hover(function(){
	clearInterval(play2);
},function(){
	playBtn();	
})
$(".trave_focus p a").hover(function(){
	$(".trave_focus p a").removeClass("curt");
	$(this).addClass("curt");
	clearInterval(play2);
	m="tu0"+$(".trave_focus p a.curt").attr("rel");
	$(".trave_focus ul").removeClass("visible");
	$("."+m).addClass("visible");
},function(){
	playBtn();	
});


/*首页旅游文章*/
$(".tab_aticle h2 a:first").addClass("currentBg4");
$(".tab_aticle ul:first").addClass("visible");
$(".tab_aticle h2 a").hover(function(){
	$(".tab_aticle h2 a").removeClass("currentBg4");
	$(".tab_aticle ul").removeClass("visible");
	$(this).addClass("currentBg4");
	n="wz00"+$(".tab_aticle h2 a.currentBg4").attr("rel");
	$("."+n).addClass("visible");
})
/*首页旅游景点排行*/
$(".top_jd p a:first").addClass("currentBg5");
$(".top_jd ul:first").addClass("visible");
$(".top_jd p a").hover(function(){
	$(".top_jd p a").removeClass("currentBg5");
	$(".top_jd ul").removeClass("visible");
	$(this).addClass("currentBg5");
	m="jd00"+$(".top_jd p a.currentBg5").attr("rel");
	$("."+m).addClass("visible");
})
/*首页活动*/
$(".tab_menue a:first").addClass("currentBg6");
$(".tab_con:first").addClass("visible");
$(".tab_menue a").hover(function(){
	$(".tab_menue a").removeClass("currentBg6");
	$(".tab_con").removeClass("visible");
	$(this).addClass("currentBg6");
	s="hd00"+$(".tab_menue a.currentBg6").attr("rel");
	$("."+s).addClass("visible");
	var spotPosition=($(this).attr("rel")-1)*45+10;
	$(".tab_con dl a span.spot3").css("margin-top",spotPosition)
})

/*底部滚动图*/
var i=0;
var windowWidth=$(".ly_meitu dd").width();
var imgItem=$(".ly_meitu dd p a img").size();
var pageItem=Math.ceil(imgItem/6);
var imgALLwidth=windowWidth*pageItem;
$(".ly_meitu dd p").css("width",imgALLwidth);

nextPlay=function(){
	if(i==pageItem) i=pageItem-1;
	$(".ly_meitu dd p").animate({marginLeft:-windowWidth*i+10},1000);
	}
prevPlay=function(){
	if(i<0) i=0;
	$(".ly_meitu dd p").animate({marginLeft:-windowWidth*i+5},1000);
	}

$(".next_btn").click(function(){
	i++;
	nextPlay();
})
$(".prev_btn").click(function(){
	i--;
	prevPlay();
})















})