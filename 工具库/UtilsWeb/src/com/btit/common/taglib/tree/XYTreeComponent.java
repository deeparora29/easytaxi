package com.btit.common.taglib.tree;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 仅支持xyTree
 * @author renmian
 *
 */
public class XYTreeComponent extends Component {
	private final Log logger = LogFactory.getLog(XYTreeComponent.class);
	
	private String divId; 
	
	private String treeId;

	private String scope;

	private String treeDesc;

	private String treeNodes;

	public XYTreeComponent(ValueStack arg0) {
		super(arg0);
	}

	@Override
	public boolean start(Writer writer) {
		// TODO Auto-generated method stub
		boolean result = super.start(writer);
		try {
			StringBuilder str = new StringBuilder();
			boolean isValid = true;
			IBasicTree tree = null;
			IBasicTreeItem[] treeItems = null;
			
			if (isValid) {
				tree = (IBasicTree) this.getStack().findValue(treeDesc);
				isValid = tree == null ? false : true;
				if (isValid) {
					treeItems = (IBasicTreeItem[]) this.getStack().findValue(
							treeNodes);
					isValid = treeItems == null ? false : true;

					if (isValid) {
						divId = divId == null ? "TreeDemo" : divId;
						treeId = treeId == null ? tree.getTreeName() : treeId;
						
						str.append("<script type='text/javascript'>\n");
						
						str.append("var div = \"<div id='" + divId + "'></div>\";\n");
						str.append("document.write(div);\n");
						
						str.append("var " + treeId
								+ " = new xyTree.DivTree('" + tree.getTreeShowStr()
								+ "','" + tree.getStyle() + "'," 
								+ tree.getShowCheck()
								+ ");\n");
						str.append(treeId + ".setImagesPath(\""
								+ tree.getImagePath() + "\");\n");
						boolean showCheck = tree.getShowCheck();
						// 添加根结点
						ArrayList rootNames = new ArrayList();
						for (int i = 0; i < treeItems.length; i++) {			
							if (treeItems[i].isRootNode()) {
								rootNames.add(treeItems[i].getName());
								str.append(getTreeNodeJS(treeId, treeItems[i], showCheck));
							}
						}
						// 添加叶子结点,由于必须先初始化上一级结点，才能初始化下一级结点，此处需要做特殊处理。

						HashMap treeMap = getTreeitemMap(treeItems);
						for (int i = 0; i < rootNames.size(); i++) {
							String root = (String) rootNames.get(i);
							str.append(printTree(root, treeMap, showCheck));
						}
						
						str.append("document.getElementById('" + divId +"').appendChild(" + treeId + ".div);\n");
						if(tree.isLazyLoad()){
							str.append(treeId + ".init(callbackAjaxTreeNodeJsFunc, callBackRightMenuJs);\n");
							str.append(printAjaxTreeJs());
							str.append(printTreeRightMenuJs());
						}
						else{
							str.append(treeId + ".init(callBackTreeNodeJs, callBackRightMenuJs);\n");
							str.append(printTreeJs());
							str.append(printTreeRightMenuJs());
						}
						str.append("\n</script>");
					}
				}
			}
			writer.write(str.toString());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("在taglib中构造过程中抛出异常：", e);
		}
		return result;
	}
	
	/**
	 * 获取初始化树
	 * @param tree
	 * @return
	 */
	private String printTreeJs() {
		StringBuffer str = new StringBuffer();
		// 选中某个节点
		str.append("function callBackTreeNodeJs(node){\n");
		str.append("\tif(node.clickNodeJs)\n\teval(node.clickNodeJs);\n");
		str.append("\telse\n\tnode.getHtmlElementjiahaoimg().onclick();\n");
		str.append("}\n");
		return str.toString();
	}
	
	private String printAjaxTreeJs(){
		StringBuffer str = new StringBuffer();
		str.append("var loadinggifnode;\n");//为了Ajax效果定义的全局变量
		str.append("function callbackAjaxTreeNodeJsFunc(node){\n");
		str.append("\tloadinggifnode = node;\n");//为了使用定时器，没法带参数，只好使用全局变量
		str.append("\tif (node.child.length>0)return;\n");//如果已经添加了子节点则返回
		str.append("\tif(node.nochild)return;\n");//如果节点有这个属性也返回，这个属性是给节点赋值时加的
		str.append("\tnode.loadingGif();\n");//显示动画，表示需要从服务器获取子节点
		//str.append("\talert('id:' + node.id +'level:' + node.level);\n");
		str.append("\tloadAjaxJs(node);\n");  //ajax方法调用
		str.append("}\n");
		return str.toString();
	}
	
	private String printTreeRightMenuJs(){
		StringBuffer str = new StringBuffer();
		str.append("function callBackRightMenuJs(node){\n");
		str.append("\tif(node.rightMenu)\n\tshowRightMenu(node);\n");
		str.append("\telse\n\treturn true;\n");
		str.append("}\n");
		return str.toString();
	}
	
	/**
	 * js中node定义：xyTree.Node = function(name, showCheck, id, img, clickNodeJs, rightMenu)
	 * @param fatherNodeName
	 * @param treeItem
	 * @return
	 */
	private String getTreeNodeJS(String fatherNodeName, IBasicTreeItem treeItem, boolean showCheck){
		StringBuffer str = new StringBuffer();
		str.append("var " + treeItem.getName()
				+ " = new xyTree.Node(\""
				+ treeItem.getShowStr() + "\","  //呈现名称
				+ showCheck + ",\""
				+ treeItem.getId()+ "\",\""      //node id
				+ treeItem.getNodeImg() + "\",\""  //节点图片
				+ treeItem.getJsFun() + "\",\""    //点击节点触发事件
				+ treeItem.getRightMenuJsFun() + "\""      //是否加载右键菜单
				+ ");\n");
		if(treeItem.getOtherData() != null && !treeItem.getOtherData().equals(""))
			str.append(treeItem.getName() + ".otherData=" + treeItem.getOtherData() + ";\n");
		str.append(fatherNodeName + ".add(" + treeItem.getName() + ");\n");
		return str.toString();
	}

	/**
	 * 返回的map，key为父结点name，value为IRpTreeItem的ArrayList
	 * 
	 * @param treeItems
	 * @return
	 */
	private HashMap getTreeitemMap(IBasicTreeItem[] treeItems) {
		HashMap treeMap = new HashMap();
		for (int i = 0; i < treeItems.length; i++) {

			ArrayList brotherItems = (ArrayList) treeMap.get(treeItems[i]
					.getFatherName());
			if (brotherItems == null) {
				brotherItems = new ArrayList();
				treeMap.put(treeItems[i].getFatherName(), brotherItems);

			}
			brotherItems.add(treeItems[i]);
		}
		return treeMap;
	}

	private String printTree(String fatherNode, HashMap treeMap, boolean showCheck) {
		// 如果存在该节点，则打印所有其子结点。
		StringBuffer str = new StringBuffer();
		IBasicTree tree = null;

		if (treeMap.containsKey(fatherNode)) {// 有孩子节点的情况
			ArrayList childs = (ArrayList) treeMap.get(fatherNode);
			IBasicTreeItem[] treeItems = (IBasicTreeItem[]) childs
					.toArray(new IBasicTreeItem[childs.size()]);
			for (int i = 0; i < treeItems.length; i++) {
				str.append(getTreeNodeJS(fatherNode, treeItems[i], showCheck));
				str.append(printTree(treeItems[i].getName(), treeMap, showCheck));
			}
		}
		return str.toString();
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTreeDesc() {
		return treeDesc;
	}

	public void setTreeDesc(String treeDesc) {
		this.treeDesc = treeDesc;
	}

	public String getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(String treeNodes) {
		this.treeNodes = treeNodes;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

}
