<!--//
  //校验数字
function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}
//-->
 //校验邮件
function isPostCode(mobile) {
	if(mobile.length == 0)
	return true;
	if(!isInteger(mobile)){
		return false;
	}
	if (mobile.length != 6) {
		return false;
	}
	return true;
}
function isWebAddress(str)
{
    if(str.length == 0)
	return true;
    var pattern = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
    if(!pattern.exec(str)){
 		return false;
	}
	return true;
}

//检验浮点型数字
  function isFloat(str){    
  var   j=0   ;   
  var  n=0;
      if   (str==""){   
      n=1;  
      return  n; 
  }   
 if(str.charAt(str.length-1)=="."){   
 //     alert("最后一位不能是小数点！");   
      n=2;
      return   n;   
  }    
  for(var   i   =   0;i<str.length;i++){   
      if(str.charAt(i)=="."){   
  if(i==0){   
     // alert("第一位不能是小数点！");
      n=3;
      return   n;   
  }   
          j=j+1;   
      }   
      if(j>1){   
       n=4;
       //   alert("小数点个数有误！");   
  return   n;   
      }   
  }   
  for(var   i   =   0;i<str.length;i++){   
      if(str.charAt(i)<"0"||str.charAt(i)>"9"){   
                  if(str.charAt(i)!="."){   
    //  alert("时限长度只能是整数或小数！");   
      n=5;
      return   n;   
  }   
      }   
  }   
  return   n; 
 }   



//中文值检测

//中文值检测
function isChinese(name) 
{ 
if(name.length == 0)
return true;
for(i = 0; i < name.length; i++) { 
if(name.charCodeAt(i) > 128)
return true;
}
return false;
}

function isEnglish(name) //英文值检测
{ 
if(name.length == 0)
return true;
for(i = 0; i < name.length; i++) { 
if(name.charCodeAt(i) > 128)
return false;
}
return true;
}


function isPhone(tel){
    var telephone=/^(0[0-9]{2,3}(\-)?)?([2-9]{1}[0-9]{6,7}){1}(\-[0-9]{1,4})?$/;
    if(!telephone.test(tel))
    {
     return false;
    }
    return true;
   }

/**
* 比较2个日期大小。如果beginDate大于endDate,返回false；否则返回true
*/
function compareDate(beginDate, endDate, msg, id) {
	var bDate = Date.parse(beginDate.replace(/-/g, "/"));
	var eDate = Date.parse(endDate.replace(/-/g, "/"));
	if (bDate > eDate) {
		document.getElementById(id).innerHTML = msg;
		return false;
	}
	return true;
}
/**
* 字符串左右去空格
*/
function trim(str) {
	return str.replace(/^\s*/, "").replace(/\s*$/, "");
}
/*************************************************************************
		 *
		 * 名  称: ValidData
		 *
		 * 类  型: public
		 *
		 * 功  能: 日期格式的验证
		 *
		 * 参  数: ControlId:需要验证的控件ID
		 *		ErrMsg:错误信息(提示名称)
		 * 返回值: true 格式正确；false 格式不正确
		 *
		 *************************************************************************/
function ValidData(strVal, msg, id) {
	var str = strVal;
	if (/^\s+$/.test(str)) {
		document.getElementById(id).innerHTML = msg;
		return false;
	}
	if (str.search(/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/) != -1) {
		return true;
	} else {
		document.getElementById(id).innerHTML = msg;
	}
	return false;
}
/**
* 取得字符串的字节长度
*/
function strlen(str) {
	var i;
	var len;
	len = 0;
	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255) {
			len += 2;
		} else {
			len++;
		}
	}
	return len;
}
/*提示信息并且跳转
confirmInfo：提示信息
gotoUrl：跳转路径
*/
function areYouSure(confirmInfo, gotoUrl) {
	if (confirm(confirmInfo)) {
		window.location = gotoUrl;
	}
}
/*判断是否是数字*/
function isNumber(str) {
	if ("" == str) {
		return true;
	}
	var reg = /\D/;
	var flag = (str.match(reg) == null);
	if (str.length > 1 && str.substring(0, 1) == "0") {
		flag = false;
	}
	return flag;
}
/**
 * 弹出提示对话框
 */
function popwnd() {
	var path = window.location.pathname;
	var actionName = path.substring(path.lastIndexOf("/") + 1);
	var url = path.substring(0, path.lastIndexOf("/")) + "/alert.jsp";
	if (actionName.indexOf("go", 0) == -1 && document.all.message.value != "") {
		window.showModalDialog(url, window, "dialogWidth:430px;dialogHeight:200px;center:yes;help:yes;resizable:no;status:no;scroll:no");
	} else {
		if (actionName.indexOf("del", 0) != -1) {
			window.showModalDialog(url, window, "dialogWidth:430px;dialogHeight:200px;center:yes;help:yes;resizable:no;status:no;scroll:no");
		}
	}
	return false;
}
/** 
 * 初始化提示框的提示信息
 */
function setMessage() {
	var path = window.location.pathname;
	var actionName = path.substring(path.lastIndexOf("/") + 1);
	var parentWindow;
	if (actionName.indexOf("dispatchbasestation", 0) != -1) {
		document.all.msg.innerHTML = document.all.message.value;
	} else {
		if(actionName.indexOf("import", 0) != -1 || actionName.indexOf("clear", 0) != -1){
			document.all.msg.innerHTML = document.all.message.value;
		}else if (actionName.indexOf("alert", 0) != -1 ) {
			parentWindow = window.dialogArguments;
			document.all.msg.innerHTML = parentWindow.document.all.message.value;
		}else {
			document.all.msg.innerHTML = document.all.message.value;
		}
	}
}
var isConfirm;
/**
 * 提示对话框的操作
 */
function doAction(val) {
	if (1 == isConfirm) {
		return;
	}
	var path = window.location.pathname;
	var actionName = path.substring(path.lastIndexOf("/") + 1);
	var parentWindow;
	
	if(actionName.indexOf("import", 0) != -1 || actionName.indexOf("clear", 0) != -1){
		parentWindow = window.opener;
		parentWindow.document.all.simplepage.simpleId.value = "";
		parentWindow.document.all.simplepage.simpleNumber.value = "";
		isConfirm = val;
		parentWindow.refresh();
		window.close();
	}else if (actionName.indexOf("alert", 0) != -1 && actionName.indexOf("dispatchbasestation", 0) == -1) {
		try{
		parentWindow = window.dialogArguments;
	
		if(parentWindow.opener==null){
			window.close();
			return;
		}
		var addhref;
		addhref = parentWindow.location.href;
	
		if (addhref.indexOf("mod", 0) == -1) {
			
			if(parentWindow.opener != null){
			
		
				if ( null != parentWindow.opener.document.all.simplepage) {
					
					parentWindow.opener.document.all.simplepage.simpleId.value = "";
					
				} else {
					if (addhref.indexOf("head", 0) != -1) {
						parentWindow.opener.document.all.linepage.headId.value = "";
					} else {
						if (addhref.indexOf("basestation", 0) != -1) {
						} else {
							if (addhref.indexOf("tree", 0) != -1) {
								parentWindow.opener.document.all.treeform.treeId.value = "";
							} else if(null != parentWindow.opener.document.all.linepage) {
								parentWindow.opener.document.all.linepage.lineId.value = "";
							}
						}
					}
				}
			}
		}
		}catch(e){
			window.close();
			parentWindow.close();
		}
		isConfirm = val;
		try{
			parentWindow.opener.refresh();
			window.close();
			parentWindow.close();
		}catch(e){
			window.close();
			parentWindow.close();
		}
	} else {		
		parentWindow = window.opener;
		if (null != parentWindow.document.all.simplepage) {
			parentWindow.document.all.simplepage.simpleId.value = "";
		} else {
			if (actionName.indexOf("head", 0) != -1 && null != parentWindow.document.all.linepage) {
				parentWindow.document.all.linepage.headId.value = "";
			} else {
				if (actionName.indexOf("tree", 0) != -1 && null != parentWindow.document.all.treeform) {
					parentWindow.document.all.treeform.treeId.value = "";
				} else {
					if (actionName.indexOf("basestation", 0) != -1 && null != parentWindow.document.all.treeform) {
						parentWindow.document.all.treeform.matchId.value = "";
					} else if(null != parentWindow.document.all.linepage){
						parentWindow.document.all.linepage.lineId.value = "";
					}
				}
			}
		}
		isConfirm = val;
		//parentWindow.refresh();
		//window.close();
		try{
			parentWindow.refresh();
			window.close();		
		}catch(e){			
			window.close();			
		}
	}
}
/**
 * 根节点关闭窗口方法
 */
function closeAlert() {
	window.opener.refresh();
	window.close();
}
/**
 * 解决Struts2日期控件的只读问题以及当表单重置后无默认值的问题，在onload事件时使用。
 * 本函数需要传入日期控件的ID。例如：sturstDate('id1','id2'...)个数不限
 */
function sturstDate() {
	var params = sturstDate.arguments;
	if (params.length == 0) {
		return;
	}
	var defaultValues = new Array();
	var textControls = new Array();
	var oldResetFunction = document.forms[0].reset;
	this.onKeydown = function (ev) {
		if (ev.keyCode != 8) {
			return false;
		}
	};
	this.onFocus = function (ev) {
		ev.srcElement.select();
	};
	this.onContextmenu = function (ev) {
		return false;
	};
	for (var i = 0; i < params.length; i++) {
		try {
		
			if(document.getElementById(params[i])==null){
				continue;
			}
			
			var dateText = new Array();
			dateText[0] = document.getElementById(params[i]).getElementsByTagName("INPUT")[0];
			dateText[1] = document.getElementById(params[i]).getElementsByTagName("INPUT")[1];
			dateText[1].attachEvent("onkeydown", onKeydown);
			dateText[1].attachEvent("onfocus", onFocus);
			dateText[1].attachEvent("oncontextmenu", onContextmenu);
			defaultValues.push(dateText[1].value);
			textControls.push(dateText);
		}
		catch (e) {
		}
	}
	this.resetDate = function () {
		oldResetFunction();
		try {
			for (var i = 0; i < textControls.length; i++) {
				textControls[i][0].value = defaultValues[i];
				textControls[i][1].value = defaultValues[i];
			}
		}
		catch (e) {
		}
	};
	document.forms[0].reset = resetDate;
}
/**
 *  检查EMail是否正确 
 *  
 */
function isEmail(email) {
	if(email.length==0){
return true;
}
	return (new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(email));
}
/**
 *  检查年月日是否是合法日期 
 *  
 */
function isdate(intYear, intMonth, intDay) {
	if (isNaN(intYear) || isNaN(intMonth) || isNaN(intDay)) {
		return false;
	}
	if (intMonth > 12 || intMonth < 1) {
		return false;
	}
	if (intDay < 1 || intDay > 31) {
		return false;
	}
	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intDay > 30)) {
		return false;
	}
	if (intMonth == 2) {
		if (intDay > 29) {
			return false;
		}
		if ((((intYear % 100 == 0) && (intYear % 400 != 0)) || (intYear % 4 != 0)) && (intDay > 28)) {
			return false;
		}
	}
	return true;
}
/**
 *  检查身份证是否是正确格式 
 *  如果要返回不同的提示信息，在JAVA类再得到些返回的消息串，并ALERT消息串 返回FALSE，串为“验证通过”不执行ALERT 返回TRUE
 */
function checkCard(cId) {
	var pattern;
	if (cId.length == 15) {
		pattern = /^\d{15}$/;//正则表达式,15位且全是数字
		if (pattern.exec(cId) == null) {
		 //   alert("15位身份证号码必须为数字！")
			return false;
		}
		if (!isdate("19" + cId.substring(6, 8), cId.substring(8, 10), cId.substring(10, 12))) {
		 //   alert("身份证号码中所含日期不正确") 
			return false;
		}
	} else {
		if (cId.length == 18) {
			pattern = /^\d{17}(\d|x|X)$/;//正则表达式,18位且前17位全是数字，最后一位只能数字,x,X
			if (pattern.exec(cId) == null) {
		//       alert("18位身份证号码必须为数字！")
				return false;
			}
			if (!isdate(cId.substring(6, 10), cId.substring(10, 12), cId.substring(12, 14))) {
                 //   alert("身份证号码中所含日期不正确") 
				return false;
			}
			var strJiaoYan = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"];
			var intQuan = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];
			var intTemp = 0;
			for (i = 0; i < cId.length - 1; i++) {
				intTemp += cId.substring(i, i + 1) * intQuan[i];
			}
			intTemp %= 11;
			if (cId.substring(cId.length - 1, cId.length).toUpperCase() != strJiaoYan[intTemp]) {
		//   alert("验证码失败！")
				return false;
			}
		} else {
	// alert("长度必须为15或18！")
			return false;
		}
	}
	return true;
}
/**
 * 取得系统当前时间
 */
function getNowDate() {
	var nowDate = "";
	var date = new Date(); //日期对象 				
	nowDate = date.getFullYear() + "-"; //读英文就行了
	nowDate = nowDate + (date.getMonth() + 1) + "-";//取月的时候取的是当前月-1如果想取当前月+1就可以了 
	nowDate = nowDate + date.getDate();
	//以下为如果要小时,分,秒,打开即可
	//nowDate = nowDate + date.getDate()+" ";
	//nowDate = nowDate + date.getHours()+":";
	//nowDate = nowDate + date.getMinutes()+":";
	//nowDate = nowDate + date.getSeconds()+"";
	return nowDate;
}
/**
 * 验证是否为手机号码
 */
function ismobile(str) {
var patrn = /^0?1[3-9]{1}[0-9]{1}[0-9]{8}$/;
return patrn.test(str);
}
/**
 * 针对Struts2标签的日期控件和时间控件结合使用的情况。将2个控件的值合并为一个JavaScript的Date对象并返回。
 * 如果2个控件任意一个控件值为空或控件值不能转化为日期，返回false。并且会在传入的错误提示控件中显示
 * 参数：dateId       传入的日期控件ID，其值的格式必须为"yyyy-MM-dd"
 *      timeId       传入的时间控件ID，其值的格式必须为"HH:mm:ss"
 *      errorInfoId  传入的错误提示控件ID
 *      isRequired   布尔值，表示该组控件是否必填。true为必填，false为可以为空。(默认为必填)
 */
function parseStrutsDate(dateId, timeId, errorInfoId, isRequired) {
	var result;
	var dateObj = dojo.widget.byId(dateId);
	var timeObj = dojo.widget.byId(timeId);
	var req = isRequired;
	if (null == req) {
		req = true;
	}
	Date.prototype.toStr = function () {
		var timeStr = this.getYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":00";
		return timeStr;
	};
	if (null == dateObj || null == timeObj) {
		return false;
	}
	if (req) {
		if ("" == dateObj.getValue() && "" == timeObj.inputNode.value) {
			document.getElementById(errorInfoId).innerHTML = "\u8be5\u9879\u4e0d\u80fd\u4e3a\u7a7a!";
			return false;
		} else {
			if ("" == dateObj.getValue()) {
				document.getElementById(errorInfoId).innerHTML = "\u65e5\u671f\u4e0d\u80fd\u4e3a\u7a7a!";
				return false;
			} else {
				if ("" == timeObj.inputNode.value) {
					document.getElementById(errorInfoId).innerHTML = "\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a!";
					return false;
				}
			}
		}
		var dateValue = dateObj.getValue().split("-");
		var timeValue = document.getElementById(timeId).getElementsByTagName("INPUT")[1].value.split(":");
		result = new Date(dateValue[0], (dateValue[1] - 1), dateValue[2], timeValue[0], timeValue[1]);
		if (isNaN(result)) {
			document.getElementById(errorInfoId).innerHTML = "\u65e5\u671f\u683c\u5f0f\u9519\u8bef!";
			return false;
		} else {
			document.getElementById(errorInfoId).innerHTML = "";
		}
	} else {
		if ("" != dateObj.getValue() && "" != timeObj.inputNode.value) {
			var dateValue2 = dateObj.getValue().split("-");
			var timeValue2 = document.getElementById(timeId).getElementsByTagName("INPUT")[1].value.split(":");
			result = new Date(dateValue2[0], (dateValue2[1] - 1), dateValue2[2], timeValue2[0], timeValue2[1]);
			if (isNaN(result)) {
				document.getElementById(errorInfoId).innerHTML = "\u65e5\u671f\u683c\u5f0f\u9519\u8bef!";
				return false;
			} else {
				document.getElementById(errorInfoId).innerHTML = "";
			}
		} else if("" == dateObj.getValue() && "" == timeObj.inputNode.value){
			document.getElementById(errorInfoId).innerHTML = "";
			return true;
		} else {
			if ("" == dateObj.getValue() && "" != timeObj.inputNode.value) {
				document.getElementById(errorInfoId).innerHTML = "\u65e5\u671f\u4e0d\u80fd\u4e3a\u7a7a!";
				return false;
			} else {
				if ("" != dateObj.getValue() && "" == timeObj.inputNode.value) {
					document.getElementById(errorInfoId).innerHTML = "\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a!";
					return false;
				} else {
					document.getElementById(errorInfoId).innerHTML = "";
				}
			}
		}
	}
	return result;
}
//获取下拉列表选中项的文本与传入值是否相同  
function getSelectedText(obj, value) {
	for (var i = 0; i < obj.length; i++) {
		if (trim(obj[i].innerText) == trim(value)) {
			return true;
		}
	}
	return false;
}
/**
 * 浮点数判断
 */
function ValidResult(id){
		//浮点数
	var result3 = /^\d{1,4}(\.\d{1,2})?$/;
	if(!result3.test(document.getElementById(id).value)){
		return true;
	}else{
		return false;
	}
}

function returnIndex(){
	window.top.window.close();
}
function validateLength(fieldValue,min,max){
      var  fieldSize=  trim(fieldValue).length;
      if(fieldSize>=min&&fieldSize<=max)
        return false;
     return true;
  
}
/*************************************************************************
 *
 * 名  称: isNotNull
 *
 * 类  型: public
 *
 * 功  能: 非空校验
 *
 * 参  数: ControlId:需要验证的控件ID
 *		ErrMsg:错误信息(提示名称)
 *		msgId:提示信息ID(span id)
 *		flag:验证标识符
 * 返回值: true 不为空；false 为空并且返回错误信息
 *
 *************************************************************************/
function isNotNull(controlId,errMsg,msgId,flag){
	var str = document.getElementById(controlId).value;
	if(str.length==0||str==""){
	document.getElementById(msgId).innerHTML = errMsg;
	flag = false;
	}
	return flag;
}
