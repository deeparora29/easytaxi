/*
飞飞Ajax模仿google提示输入框 使用说明 v1.8

程序介绍：

本程序官网演示地址：http://www.ffasp.com/content.asp?newsid=1376
转载请注明出处

此小程序是模仿Google(sgguest)输入提示框所编写
亦可用于用户注册邮箱时的提示
此程序使用方便简单、易用灵活
且不限Asp、php、net

更新说明：
1.支持xhtml
2.兼容IE6,IE7,FF
3.支持input显示与value分离【以隐藏域方式提交数据】
4.支持下拉框按钮
5.支持显示结果数量
6.可以一个页面多次使用
7.模拟下拉框不会被select遮挡
8.调用更方便（内核已改为jq插件）
9.皮肤修改方便 ，外置的css样式文件，带有注释说明，任意修改
10.支持键盘方向键按住不放选择
11.解决了延时卡死的现象
12.解决了在有滚动条时，ie6下被表单遮挡的问题
13.支持回调函数。当该插件执行完毕时，执行另外一个指定函数
######V1.7解决问题############
14.可以自己设置提示框的长度
15.解决了无法获取用户填写默认值的问题
16.当输入框失去焦点时，提示框会自动隐藏
######V1.8解决问题############
17.解决了鼠标无法选择的问题
******************************************************************
文件使用说明：
在需要使用的页面首先要引用2个js文件
<script src="jquery1.2.6.min.js"></script>
<script src="search_suggest.js"></script>
此二文件必须放于调用函数之前
*****************************以下是调用示例*************************：
var option = {
inputName : "test2",
inputText : "测试字符串4",
inputValue: "我是值",
inputlength:1,
url : "search.asp",
arrow :1,
arrowUrl :"search.asp?aa=nowitest",
zIndex:7,
fns:function(){alert(3)}
}
$("#test").suggestShow(option)
+++++++解释说明+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
option:为参数设置对象。option内的所有参数可以省略
inputName        表单input的name.即 提交时获取input的name。也为search.asp页面获取input的name
inputText        inputName 所显示的数据
inputValue       inputName 所提交的数据，当inputValue省略时，inputValue则与inputText相等
url              远程执行获取数据的url
arrow            模仿select，使用下拉按钮。固定值。为1则显示下拉箭头 为0则不显示
arrowUrl         当arrow为1时 ，默认情况下点击下拉按钮所执行的远程搜索页面
zIndex           z-index值
inputlength      inputName中输入数据的最小长度
fns              当执行完该插件时，执行的回调函数

************************************************************************
search.asp页面介绍：
该页面返回数据必须是xml格式,输出数据数据必须符合以下格式
<response value="1" result ="共2个结果">显示信息</response>
属性介绍：
1.value      aa的value值，可以不填。不填value值则取"显示信息"
2.result     显示远程获取的统计结果，可以不填。
3.显示信息    即aa的显示值text值 
************************************************************************
*/

$.fn.suggestShow = function(G){
	var D ={
			inputName : "search",                //提交的表单input名称
			inputText : "",                      //表单input[inputName]的显示值
			inputValue : "",                     //表单input[inputName]的提交值
			inputlength : 1,                     //当表单input[inputName]值的长度大于inputlength时开始执行搜索
			url : "search.jsp",                  //远程执行搜索的动态页面
			zIndex : 1,                          //当前的提示框的z-index值
			isScroll : 0,                       //是否支持提示框出现滚动条 0 不支持，1 支持
			arrow : 0 ,                          //下拉框是否出现提示箭头按钮
			arrowUrl: "search.jsp"  ,             //当使用提示箭头按钮时，默认的下拉框的所显示内容获取的页面
			suggestWidth:"",                      //数据选择框宽度
			fns:function(){}                     //返回值执行的函数
		};
	$.extend(D,G);	
	if (D.inputValue == "") D.inputValue = D.inputText; 
	var temp = "";//定义变量
	var objdiv,objInput,objSuggest,objtemp,objInputValue,objInputDiv,objIframe,InputValue,InputValueZZ,date,dzz;//定义对象
	var objform;
	var obj,inputWidth,sugesstDivWidth;       
	var zz=-1; //此为指针 
	var itemLength = 0;//设置默认的itemLength值
	var t;
	InputValueZZ = "";
	if(typeof(D.inputlength)=="undefined"){D.inputlength = 1}//inputlength为1时开始执行搜索
	
	
	/*初始化对象*/
	var _d = new Date();
	var _tem = _d.getTime();	
	objdiv = $("<div></div>");//最外层的定位div
	objdiv.attr("id","ffsuggest"+_tem);
	objInputDiv = $("<div></div>");//表单input的div
	objInput = $("<input maxlength=\"50\" id=\""+D.inputName+"\" type=\"text\" />");//显示搜索内容input
	objInputValue = $("<input name=\""+D.inputName+"\" type = \"hidden\" />");//隐藏域 提交表单项目
	objButton = $("<input type = \"button\" />");//下拉箭头按钮
	objSuggest = $("<div></div>");//模拟下拉框div
	objIframe = $("<iframe frameborder=0></iframe>");//防止select遮挡的iframe
	/*初始化对象结束*/
	
	/*对象添加整合*/		 
	objInputDiv.append(objInput);
	objInputDiv.append(objInputValue);
	objdiv.append(objInputDiv);
	objdiv.append(objSuggest);
	objdiv.appendTo(this);
	/*对象添加整合完毕*/
	objform = objInput.parents("form");
	/*选择框样式*/
	inputWidth = objInput.css("width");
	if (inputWidth == "auto") inputWidth = "200px";
	if (objInput.css("margin-left") == "auto") objInput.css("margin-left","0");
	if (D.suggestWidth == "" )
		sugesstDivWidth = parseInt(inputWidth)+3+"px";
	else
		sugesstDivWidth = parseInt(D.suggestWidth)+3+"px";

	if(D.isScroll){
		objSuggest.css("overflow-y","scroll");
		objSuggest.css("overflow-x","hidden");
		objSuggest.css("height","100px");
		objSuggest.css("padding-right","16px");
		sugesstDivWidth = (parseInt(sugesstDivWidth)-16)+"px";
	};
		objSuggest.css("margin-left",objInput.css("margin-left"));
	
	//下拉按钮位置
	objInputDiv.css("position","relative")
               .css("width",inputWidth); 
	objInputValue.val(D.inputValue);
	// 表单input[inputName]的显示值
	objInput.val(D.inputText)         
		    .attr("class","key_normal")
		    .keyup(function(e){beKeyUp(e);})
			.keydown(function(e){beKeyDown(e);})
			.blur(function(e){
					if($.trim(objInput.val())==""||$.trim(objInput.val()).length==0)
					objInputValue.val("");
					objform.unbind("keydown",unbindSubmit(e));
//					objInput.attr("class","key_normal");
//					objSuggest.attr("class","suggest_hidden");
					zz=-1; 

				})
  //表单input样式
		    .css("width",inputWidth);
	objSuggest.attr("class","suggest_hidden")
		      .css("width",sugesstDivWidth);
	/*下拉箭头按钮处理*/
	if(D.arrow)
	{//eval(-42-parseInt(objInput.css("margin-left")))
		objInputDiv.append(objButton);
		objButton.attr("class","suggest_button")
				 .css("right",eval(-42-parseInt(objInput.css("margin-left")))+"px")
				 .mouseover(function(){$(this).css("clip","rect(0px,36px,22px,18px)");$(this).css("right",eval(-24-parseInt(objInput.css("margin-left")))+"px")})
				 .mouseout(function(){$(this).css("clip","rect(0px,18px,22px,0px)");$(this).css("right",eval(-42-parseInt(objInput.css("margin-left")))+"px")})
				 .click(function(e){
					if(objSuggest.attr("class") == "suggest_hidden")
					{
						if((D.arrowUrl=="") || (typeof(D.arrowUrl)=="undefined")) getdata(D.inputName,D.url);else getdata(D.inputName,D.arrowUrl);
					}
					else
					{
						objSuggest.attr("class","suggest_hidden");
						objInput.attr("class","key_normal");
					};
				 });
	}
	/*---------------------------------------------------------------------*/		 
	if (!isNaN(D.zIndex)) objdiv.css("z-index",D.zIndex);
	objdiv.css("position","relative");
	objIframe.css("position","absolute")
			 .css("width","100%")
			 .css("z-index","-1")
			 .css("border","0");
//beKeyUp事件。与服务器通信 
function beKeyUp(e)
{ 
	if(e.keyCode!=13&e.keyCode!=9&e.keyCode!=38&e.keyCode!=40)
	{ 
		objInputValue.val(objInput.val());

		zz = -1;
		if ($.trim(objInput.val()).length<D.inputlength) objSuggest.attr("class","suggest_hidden"); 
		if ($.trim(objInput.val()).length>=D.inputlength)
		{ 
			InputValue = $.trim(objInput.val());
			if (InputValueZZ == ""){
				InputValueZZ = InputValue;
				t=setTimeout(function(){sdd()},400);
			}
			else
			{
				clearTimeout(t);
				if(InputValueZZ == InputValue) return; else t=setTimeout(function(){sdd()},400);
			}
		} 
	} 
} 

function sdd()
{
	if(InputValueZZ == InputValue)
	{
		if ($.trim(objInput.val()).length>=D.inputlength) getdata(D.inputName,D.url);
	}
	else
	{
		InputValueZZ = InputValue;
		sdd();
	}
}

//远程获取数据
function getdata(inputName,url)
{
	var temp = "";
	var result = "";
	var objleft,objright;
	var objload;
	objload = $("<div>正在获取数据。。。</div>")
	objInput.focus();
	objSuggest.html(objIframe);
	objSuggest.append(objload);
	objload.attr("class","loaddata");
	objIframe.css("height",objSuggest.height());
	objInput.attr("class","key_normal");
	objSuggest.attr("class","search_suggest");
	$.ajax({
		type:"post",
		url:D.url,
		dataType:"xml",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		data:D.inputName+"="+($.trim(objInput.val())),
		success:function(Back){
			itemLength = $("response",Back).size();
			if(itemLength == 0)
			{
				objIframe.css("height",objSuggest.height());
				objInput.attr("class","key_normal");
				objSuggest.attr("class","suggest_hidden");
				return;
			}
			objSuggest.html(objIframe);
			$("response",Back).each(function(i){
				result = $(this).attr("result");
				if(typeof(result)=="undefined") result="";
				objleft = $("<span></span>");
				objright = $("<span></span>")
				objtemp = $("<div></div>");
				objtemp.append(objleft);
				objtemp.append(objright);
				objtemp.appendTo(objSuggest);
				
				objleft.text($(this).text());
				objright.text(result);
				
				objleft.attr("class","suggest_left");
				objright.attr("class","suggest_right");
				
				objtemp.attr("value",$(this,Back).attr("value"))
					   .attr("valuetext",$(this,Back).text())
					   .attr("class","item_normal")
					   .mouseover(function(){beMouseOver(i)})
					   .css("width",sugesstDivWidth)
					   .css("overflow","hidden")
					   .click(function(){beClick(i)});
			})
				$("<div>取消</div>").click(function(){beClick2()})
									.attr("class","cancel")
									.appendTo(objSuggest);
				//objIframe.css("height",objSuggest.get(0).scrollHeight);
				objInput.attr("class","key_abnormal");
				objSuggest.attr("class","search_suggest");
				objIframe.css("height",objSuggest.get(0).scrollHeight);
				objIframe.css("height",objSuggest.get(0).scrollHeight);
				return;					
			},
		error:function(){
			alert("远程获取数据失败！！");
			objInput.attr("class","key_normal");
			objSuggest.attr("class","suggest_hidden");
			return false;
			}					
	})
}

//函数鼠标经过效果 
function beMouseOverEFF(i){$("div",objSuggest).eq(i).attr("class","item_high");} 
//函数鼠标移开效果 
function beMouseOutEFF(i){if (i>=0) $("div",objSuggest).eq(i).attr("class","item_normal");} 
function beMouseOver(i)
{ 
	objInput.focus(); 
	beMouseOutEFF(zz); 
	zz=i; 
	beMouseOverEFF(zz); 
} 

//函数单击 
function beClick(i)
{ 
	objform.keydown(function(e){return unbindSubmit(e)});
	objInput.val($("div",objSuggest).eq(i).attr("valuetext"));
	objInputValue.val($("div",objSuggest).eq(i).attr("value"));
	if($("div",objSuggest).eq(i).attr("value") == "undefined"){objInputValue.val($("div",objSuggest).eq(i).text())};
	objInput.attr("class","key_normal");
	objSuggest.attr("class","suggest_hidden");
	objInput.focus();
	zz=-1; 
	D.fns();
	//objform.unbind();
} 

function unbindSubmit(e)
{
	if(e.keyCode == 13){return false;}	
}
//函数单击 
function beClick2()
{ 
	if($.trim(objInput.val()).length<D.inputlength){objInputValue.val("");}
	objInput.attr("class","key_normal");
	objSuggest.attr("class","suggest_hidden");
	zz=-1; 
} 

//方向键接收函数 
function beKeyDown(e){ 
	//往下按 
	if (e.keyCode==40)
	{ 	if(zz == itemLength -1 ){beMouseOutEFF(zz);zz = -1;};
		if(zz<itemLength-1){beMouseOutEFF(zz++);} ;
		if(zz<itemLength){beMouseOverEFF(zz);} ;
	} 
	//往上按 
	if (e.keyCode==38)
	{ 
		if (zz>0){beMouseOutEFF(zz--);} 
		if (zz>=0){beMouseOverEFF(zz);} 
	} 
	//按回车或者TAB 
	if (e.keyCode==13){if (zz!=-1)beClick(zz);} 
} 

//当鼠标在该插件其他区域点击时，隐藏该插件提示框
var _sobj = objdiv.get(0);
$(document).click(function(e){
	e = e ? e : window.event;
	var tag = e.srcElement || e.target;
	if(_sobj.id==tag.id){return;}
	var _temObj = tag;
	while(_temObj)
	{
		if(_temObj.id=="ffsuggest"+_tem)return;
		_temObj = _temObj.parentNode;
	}
	objInput.attr("class","key_normal");
	objSuggest.attr("class","suggest_hidden");
});

}