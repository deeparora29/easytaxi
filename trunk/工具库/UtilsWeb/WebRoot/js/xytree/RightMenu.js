/**
 *   用于呈现右键菜单
 *	 
 **/

//定义全局变量
document.oncontextmenu = function(){ return false; };
var nodeMenuId = 0;
function showRightMenu(node)
{
    if($("div_RightMenu") == null){    
        CreateMenu();   
    }else{
		//alert("div_RightMenu 存在！");
		nodeMenuId = 0;  
    }   
	ShowMenu(node.id);
	document.body.onclick  = HideMenu; 
}
/**
 * 刷新按钮
 */ 
function evtRefresh(){
	HideMenu();
	window.location.reload();
}
/**
 * 转派
 */        
function evtDispatch(){    
    HideMenu();
	if(nodeMenuId > 0){
		alert(nodeMenuId);
	}
}
/**
 * 批量转派
 */
function evtBatchDispatch(){    
    HideMenu();
	if(nodeMenuId > 0){
		alert(nodeMenuId);
	}
}
        
function evtMenuOnmouseMove(){
	this.style.backgroundColor='#8AAD77';
    this.style.paddingLeft='10px';    
}
        
function evtOnMouseOut(){
	this.style.backgroundColor='#FAFFF8';
}
        
function CreateMenu(){    
   var div_Menu          = document.createElement("Div");
   div_Menu.id           = "div_RightMenu";
   div_Menu.className    = "div_RightMenu";
   
   var div_Menu1          = document.createElement("Div");
   div_Menu1.id 		 = "div_RightMenu_1";
   div_Menu1.className   = "divMenuItem";
   div_Menu1.onclick     = evtRefresh;
   div_Menu1.onmousemove = evtMenuOnmouseMove;
   div_Menu1.onmouseout  = evtOnMouseOut;
   div_Menu1.innerHTML   = "刷新";
   
   var Hr1        = document.createElement("Hr");
   
   var div_Menu2          = document.createElement("Div");
   div_Menu2.id 		 = "div_RightMenu_2";
   div_Menu2.className   = "divMenuItem";
   div_Menu2.onclick     = evtDispatch;
   div_Menu2.onmousemove = evtMenuOnmouseMove
   div_Menu2.onmouseout  = evtOnMouseOut
   div_Menu2.innerHTML   = "转派";
   
   var div_Menu3          = document.createElement("Div");
   div_Menu3.id 		 = "div_RightMenu_3";
   div_Menu3.className   = "divMenuItem";
   div_Menu3.onclick     = evtBatchDispatch;
   div_Menu3.onmousemove = evtMenuOnmouseMove;
   div_Menu3.onmouseout  = evtOnMouseOut;
   div_Menu3.innerHTML   = "批量转派";
 
   
   
   div_Menu.appendChild(div_Menu1);
   div_Menu.appendChild(Hr1);
   div_Menu.appendChild(div_Menu2);
   div_Menu.appendChild(div_Menu3);
   
   
   document.body.appendChild(div_Menu);
}

// 判断客户端浏览器
function IsIE() 
{
    if (navigator.appName=="Microsoft Internet Explorer") 
    {
        return true;
    } 
    else 
    {
        return false;
    }
}
            
function ShowMenu(nodeId)
{
    if (IsIE()){
        document.body.onclick  = HideMenu;
        
        var redge = document.body.clientWidth-event.clientX;
        var bedge = document.body.clientHeight-event.clientY;
        var menu = $("div_RightMenu");
		
        if (redge < menu.offsetWidth){
            menu.style.left = document.body.scrollLeft + event.clientX-menu.offsetWidth;
        } else {
            menu.style.left = document.body.scrollLeft + event.clientX;
			menu.style.display = "block";
			nodeMenuId = nodeId;
//			if (nodeId == 0) { 
//				document.getElementById("div_RightMenu_2").style.display = "none";
//				document.getElementById("div_RightMenu_3").style.display = "none";
//			} else {
//				nodeMenuId = nodeId;
//			}
        }
        if (bedge < menu.offsetHeight){
            menu.style.top = document.body.scrollTop + event.clientY - menu.offsetHeight;
        }else{
            menu.style.top = document.body.scrollTop + event.clientY;
			menu.style.display = "block";
			nodeMenuId = nodeId;
//			if (nodeId == 0) {
//				document.getElementById("div_RightMenu_2").style.display = "none";
//				document.getElementById("div_RightMenu_3").style.display = "none";
//			} else {
//				nodeMenuId = nodeId;
//			}
        }
    }
    return false;
}
            
function HideMenu(){
    if (IsIE()) $("div_RightMenu").style.display="none"; 
}

function $(gID)
{
    return document.getElementById(gID);
}
