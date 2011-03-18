<!--//
	var realPath = window.location.pathname;
	realPath = realPath.split("/");
	//增加
	function add(){
	    var oldtarget = document.all.simplepage.target;
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","ADD","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
		document.all.simplepage.target = "ADD";
		document.all.simplepage.action = "goadd.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
		owin.focus();
	}
	//修改
	function mod(){
		if(!document.all.simplepage.simpleId.value > 0){
			alert("请选择记录");
			return false;;
		}
	    var oldtarget = document.all.simplepage.target;
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","MOD","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
		document.all.simplepage.target = "MOD";
		document.all.simplepage.action = "gomod.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
		owin.focus();
	}
	//删除
	function del(){
		if(!document.all.simplepage.simpleId.value > 0){
			alert("请选择记录");
			return false;;
		}

        if(!confirm('确定要删除？')){
		    return false;
		}
	    var oldtarget = document.all.simplepage.target;
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","ALERT","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,copyhistory=yes,width=430,height=184");
		document.all.simplepage.target = "ALERT";
		document.all.simplepage.action = "del.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
		owin.focus();
	}
	//查看
	function info(){
		if(!document.all.simplepage.simpleId.value > 0){
			alert("请选择记录");
			return false;;
		}
	    var oldtarget = document.all.simplepage.target;
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","INFO","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes");
		document.all.simplepage.target = "INFO";
		document.all.simplepage.action = "info.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
		owin.focus();
	}
	//打印
	function simpleprint(){
		window.print();
		return false;
	}
	function importsimple(){
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","IMPORT","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=yes,copyhistory=yes");
		document.all.simplepage.target = "IMPORT";
		document.all.simplepage.action = "/"+realPath[1]+"/cum/import/goimporttemporary.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = "_self";
		document.all.simplepage.action = oldaction;
		
		owin.focus();
	}
	//导出
	function exportsimple(){
		if(!document.all.simplepage.simpleId.value > 0){
			alert("没有记录");
			return false;;
		}
		var objTr = document.getElementById("title");
		if(null != objTr){
			var titles = "";
			for(var i = 1; i < objTr.cells.length; i++){
				if(i > 1){
					titles += ",";
				}
				var text = objTr.cells[i].innerHTML;
				titles += text;
			}
			document.getElementsByName("tableTitle")[0].value = titles;
		}
		var objTr1 = document.getElementById("simplTitle");
		if(null != objTr1){
			var title = "";
			for(var i = 1; i < objTr1.cells.length; i++){
				if(i > 1){
					title += ",";
				}
				var text1 = objTr1.cells[i].id;
				title += text1;
			}
			document.getElementsByName("propertyName")[0].value = title;
		}
	    var oldtarget = document.all.simplepage.target;
		var oldaction = document.all.simplepage.action;
		document.all.simplepage.action = "/"+realPath[1]+"/cum/export/export.action";
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
	}
	//筛选排序
	function query(){
	    var oldtarget = document.all.simplepage.target;
	    var oldaction = document.all.simplepage.action;
	    var owin = window.open("about:blank","QUERY","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=yes,copyhistory=yes");
		document.all.simplepage.target = "QUERY";
		if(document.all.simplepage.queryTag != null){
			document.all.simplepage.action = "/"+realPath[1]+"/pub/querysortset.action?queryAction=" + oldaction;
		}else{
			document.all.simplepage.action = "/"+realPath[1]+"/pub/querysortset.action?queryTag=SIMPLE&queryAction=" + oldaction;
		}
		document.all.simplepage.submit();
		document.all.simplepage.target = oldtarget;
		document.all.simplepage.action = oldaction;
		owin.focus();
	}
//-->