var ECCNList={};
var ECCNConstants={};

var ECCN=function(formid){


	var Me=this;
	
	ECCNList[formid]=Me;
	
	this.id=formid;
	this.doPrep=true;
	this.isDebug=false;
	this.doPrepPrev=true;

	this.prepState={next : 0 ,prev : 0};
	this.prepPage={next : 0 ,prev : 0};
	this.prepareaName={};
	this.pageFieldName=this.id+"_p";

	this.totalPagesFieldName=this.id+"_totalpages";
	this.totalRowsFieldName=this.id+"_totalrows";


	this.prepareaName['next']=this.id+"_ec_preparea_n";

	this.prepareaName['prev']=this.id+"_ec_preparea_p";



	Me.buildPrepArea=function(){
		var ta=document.createElement("textarea");
		ta.id=this.prepareaName['next'];
		ta.disabled=true;
		ta.style.display="none";
		document.body.appendChild(ta);
		
		var tb=document.createElement("textarea");
		tb.id=this.prepareaName['prev'];
		tb.disabled=true;
		tb.style.display="none";
		document.body.appendChild(tb);

		/* for Debug */
		if(Me.isDebug){
			ta.disabled=false;
			ta.style.display="inline";
			ta.rows=10;
			ta.cols=50;
			tb.disabled=false;
			tb.style.display="inline";
			tb.rows=10;
			tb.cols=50;
		}

	};

	Me.goPage=function(){
    	var newPageNO = $(Me.pageFieldName).value;
		
		var key=null;

		if(newPageNO==Me.prepPage['next'] && Me.prepState['next']==2){
			key='next';
		}else if(newPageNO== Me.prepPage['prev'] && Me.prepState['prev']==2 && Me.doPrepPrev){
			key='prev';
		}
		
    	if (key!=null){
			try{
				var newhtml=$(Me.prepareaName[key]).value;
				if (newhtml==""){	$(Me.id).submit(); return;	}
				$(Me.id).innerHTML=newhtml;
				Me.prepState[key]=0;
				window.setTimeout(Me.ajaxPrepSubmit,10);
			}catch(ex){

				$(Me.pageFieldName).value=newPageNO;
				Me.ajaxSubmit();
				//$(Me.id).submit();
			}
    	}else{
	    	//$(Me.id).submit();
			Me.ajaxSubmit();
    	}

 	};

	Me.dealResponse={
		'next'	: function(originalRequest){
			$(Me.prepareaName['next']).value =Me.cutText(originalRequest.responseText);
			Me.prepState['next']=2;
			Me.doingAjaxSubmit=false;
		},
		'prev'	: function(originalRequest){
			$(Me.prepareaName['prev']).value =Me.cutText(originalRequest.responseText);
			Me.prepState['prev']=2;
			Me.doingAjaxSubmit=false;
		}
	};
    
	Me.cutText=function(text){
       eval("var rex= /<"+"fo"+"rm"+".+id=\"?"+Me.id+"\"?[^>]*>/im ;");
		var mtext=text.match(rex);
		if (mtext==null){ return "";}
		text=text.substr(mtext.index+mtext[0].length);
		var end=text.indexOf("</form>");
		return text.substring(0,end);
	};

	Me.ajaxPrepSubmit=function(){
		if (!Me.doPrep){
			return;
		}
		try{
		Me.ajaxPrep(1);
		Me.ajaxPrep(-1);
		}catch(e){}
	};

    Me.ajaxPrep=function(which){

		var key;
		if (which==1){
			key='next';
		}else if (which==-1 && Me.doPrepPrev){
			key='prev';
		}else{
			return;
		}
		Me.prepState[key]=1;
		Me.prepPage[key]=$(Me.pageFieldName).value/1+which;
		if (Me.prepPage[key]<1 || Me.prepPage[key]>($(Me.totalPagesFieldName).value/1)) return;
		$(Me.pageFieldName).value=Me.prepPage[key];

		Me.ajaxSubmit(Me.dealResponse[key],true);

		$(Me.pageFieldName).value=Me.prepPage[key]-which;
	};
	
 	
 	Me.init=function(){
		Me.buildPrepArea();
		Me.ajaxPrepSubmit();
	};
	
	Me.doingAjaxSubmit=false;
	Me.ajaxSubmit=function(resfunc,asy){
		if ($("ajaxWaitng")){
			$("ajaxWaitng").style.visibility="visible";
		}
		

		if (!asy){
			asy=false;
		}
		if (!resfunc){
			resfunc=Me.fillForm;
		}
		if(!asy && Me.doingAjaxSubmit){
			//alert("前一次提交操作尚未完成，请稍候再试。");
			//return;
		}
		Me.doingAjaxSubmit=true;

		Ajax.formSubmit(Me.id,resfunc,"post",asy);

	};
	Me.fillForm=function(originalRequest){
		var newhtml=Me.cutText(originalRequest.responseText);
		
		if (newhtml==""){
			return;	
		}
		$(Me.id).innerHTML=newhtml;
		Me.init();
		Me.doingAjaxSubmit=false;

		if ($("ajaxWaitng")){
			$("ajaxWaitng").style.visibility="hidden";
		}
		
	};
};
    
var EccnUtil={};

EccnUtil.hideOrShowCol=function(obj){
	var id=obj.id+"_c";
	col=$(id);
		if (col.style.display=="none"){
			col.style.display="block";
			col.setAttribute("width",obj.getAttribute("ow"));
		}else{
			col.style.display="none";
			obj.setAttribute("ow",col.getAttribute("width"));
			col.setAttribute("width","0");
		}

}

// EccnUtil.noExport("form的id");

EccnUtil.noExport=function(formid,etiid){
	if (!etiid){
		etiid="eti";
	}
	var form=document.getElementById(formid);
	try{
		form[etiid].value="";
	}catch(e){
	}
	
}

EccnUtil.refresh=function(formid){
	var form=document.getElementById(formid);
	try{
	form[formid+"_totalrows"].value="";
	}catch(e){
	}
}

EccnUtil.gotoPage=function(formid,action,method,pageid,etiid,pageno){
	var form=document.getElementById(formid);
	form.setAttribute('action',action);
	form.setAttribute('method',method);
	form[pageid].value=pageno;
	EccnUtil.noExport(formid,etiid);

	try {
		if (ECCNList[formid].doPrep){
			ECCNList[formid].goPage();
		}else {
			ECCNList[formid].ajaxSubmit();
		}
	}catch (e){
		try {
			ECCNList[formid].ajaxSubmit();
		}catch (e2){
			form.submit();
		}
	}
}


EccnUtil.gotoPageByInput=function(formid,action,method,pageid,etiid,inputid){

	var form=document.getElementById(formid);

	var pageno=form[inputid].value/1;
	var totalpages=form[formid+"_totalpages"].value/1;
	if (!isFinite(pageno) || (pageno+"").indexOf(".")!=-1 || pageno<1 || pageno>totalpages){
		alert("跳转页数只能是 1 至"+totalpages+" 的整数。");
		form[inputid].focus();
		form[inputid].select();
		return;
	}
	if (pageno<1){
		pageno=1;
	}
	
	EccnUtil.gotoPage(formid,action,method,pageid,etiid,pageno);

}

EccnUtil.doExportList=function(formid,fileName){
	var action=document.getElementById(formid).getAttribute('action');
	var method="post";
	var etiid="eti";
	var type="xls";
	EccnUtil.doExport(formid,action,method,etiid,type,fileName)
}

// EccnUtil.doExport('list1','/ec/query.do?actionMethod=query2','post','eti','xls','1名单test.xls')
EccnUtil.doExport=function(formid,action,method,etiid,type,fileName){
	var form=document.getElementById(formid);
	form[formid+"_ev"].value=type;
	form[formid+"_efn"].value=fileName;
	form[etiid].value=formid;
	form.setAttribute('action',action);
	form.setAttribute('method',method);
	form.submit();

};

EccnUtil.changeRowsDisplayed=function(formid,action,method,pageid,etiid,selectObj){
	var form=document.getElementById(formid);
	form[formid+"_crd"].value=selectObj.options[selectObj.selectedIndex].value;
	form[pageid].value='1';
	form.setAttribute('action',action);
	form.setAttribute('method',method);
	EccnUtil.noExport(formid,etiid);
	try {
		ECCNList[formid].ajaxSubmit();
	}catch (e2){
		form.submit();
	}
};

EccnUtil.checkAll=function(formid,checkboxname,checkcontrol){
	var form=$(formid);
	for(i = 0; i < form.elements.item(checkboxname).length; i++) {
		form.elements.item(checkboxname).item(i).checked= form.elements.item(checkcontrol).checked;
	}
};
