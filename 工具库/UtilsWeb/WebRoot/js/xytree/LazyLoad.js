/**
 * 封装懒加载
 *
 **/
/**
 *@param nodeId：node.id传入的是node中的id值
 **/
function loadAjaxJs(node){
	var nodeLevel = node.level;
	var nodeId = node.id;
	var nodeName = node.name;
	if(nodeLevel <= 2)
		DwrLazyLoadTree.getChildStaffInfoTreeList(nodeId, nodeName, nodeLevel, callBack_loadAjaxJs);
}

function callBack_loadAjaxJs(data){
	var nodearr = [];
  	//var strarr = data[loadinggifnode.name]; //使用对象的关联数组特性
 	//alert(strarr);
  	for(var i=0;i < data.length;i++){    //    获得节点的下一级子节点
  		var node = staffInfoTree.findOneNodeByName(data[i].pname);
  		if(data[i].nodeName == data[i].pname){
  			node.nochild = true; 
  			loadinggifnode.loadingGifRenew();
  			break;
  		}
    	var newnode = new xyTree.Node(data[i].nodeName,true,data[i].nodeId);
    	newnode.toString = function (){return this.name;};//为了显示结果
    	if(node.level == 2)  {//已经加载到员工节点上
      			newnode.nochild = true; //这句话是重要的，免得没子节点还ajax，自定义属性
    	}
    	nodearr.push(newnode);   
  	} 
  	if(nodearr.length > 0){
  		loadinggifnode.addDynamic(nodearr); //动态添加
  		loadinggifnode.loadingGifRenew(); //其实这句话完全可以不加
  	}
  	
}