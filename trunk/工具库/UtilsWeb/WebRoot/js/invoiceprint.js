/*****************************************************************
** 描    述：页面打印javascript类库
** 适用范围：IE浏览器，需支持CLSID:8856F961-340A-11D0-A96B-00C04FD705A2控件
******************************************************************/
var hkey_root,hkey_path,hkey_key 
hkey_root  = "HKEY_CURRENT_USER" 
hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup"   //IE打印设置的注册表地址

//隐藏不打印的内容，如工具条
function hideOutputButton()
{
		//隐藏不打印的内容，如工具条
    var tdUCToolBar=document.getElementById("tdUCToolBar");
    var printlog = document.getElementById("printlogo");
    if (tdUCToolBar.style.visibility=="hidden")
    { 
        if(tdUCToolBar!=null) tdUCToolBar.style.visibility="visible";
        if(printlog) printlog.style.display="none";
    }
    else
    {
        if(tdUCToolBar!=null)  tdUCToolBar.style.visibility="hidden";
        if(printlog) printlog.style.display="";
    }
}

//i=8时，页面设置
//i=7时，打印预览
function ExecWB(i)
{
		if(document.getElementById('hidExportTag').value=='1')
		{
				//导出
				window.location=strQuery+'&execwb='+i;
		}
		else
		{
				if(i==8){document.all.wb.ExecWB(8,1);getClientPrintSetting(billType);}
				if(i==7){hideOutputButton();document.all.wb.ExecWB(7,1);hideOutputButton();}
		}
}

function setClientPrintSetting(billType)
{
    //从数据库中取值
}
function setClientReg()
{
    try{
		    var IEPrintSetting;
		    eval(this.xmlHttp.responseText);
		    if(IEPrintSetting)
		    {
				    var RegWsh=new ActiveXObject("WScript.Shell"); 
				    hkey_key="\\header" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.head,"REG_SZ"); //页眉
				
				    hkey_key="\\footer" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.foot,"REG_SZ");  //页脚
				
				    hkey_key="\\margin_left" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.left,"REG_SZ");  //键值设定--左边边界
				
				    hkey_key="\\margin_top" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.top,"REG_SZ"); //键值设定--上边边界
				
				    hkey_key="\\margin_right" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.right,"REG_SZ"); //键值设定--右边边界
				
				    hkey_key="\\margin_bottom" 
				    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,IEPrintSetting.bottom,"REG_SZ"); //键值设定--下边边界
		    }
    }
    catch(ex){}
}

function getClientPrintSetting(billType)
{
    try
    {
		    var headVal,footVal,leftVal,topVal,rightVal,bottomVal;
		    var RegWsh=new ActiveXObject("WScript.Shell"); 
		    hkey_key="\\header" 
		    headVal=encodeURIComponent(RegWsh.RegRead(hkey_root+hkey_path+hkey_key)); //页眉
		
		    hkey_key="\\footer" 
		    footVal=encodeURIComponent(RegWsh.RegRead(hkey_root+hkey_path+hkey_key));  //页脚
		
		    hkey_key="\\margin_left" 
		    leftVal=RegWsh.RegRead(hkey_root+hkey_path+hkey_key);  //键值设定--左边边界
		
		    hkey_key="\\margin_top" 
		    topVal=RegWsh.RegRead(hkey_root+hkey_path+hkey_key); //键值设定--上边边界
		
		    hkey_key="\\margin_right" 
		    rightVal=RegWsh.RegRead(hkey_root+hkey_path+hkey_key); //键值设定--右边边界
		
		    hkey_key="\\margin_bottom" 
		    bottomVal=RegWsh.RegRead(hkey_root+hkey_path+hkey_key); //键值设定--下边边界
		    //保存至数据库
    }
    catch(ex){}
}