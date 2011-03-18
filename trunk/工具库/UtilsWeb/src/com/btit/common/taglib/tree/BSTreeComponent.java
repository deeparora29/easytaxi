package com.btit.common.taglib.tree;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class BSTreeComponent extends Component {
	final static Log logger = LogFactory.getLog(BSTreeComponent.class);
	private String id;

	private String scope;

	private String treeDesc;

	private String treeNodes;

	public BSTreeComponent(ValueStack arg0) {
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
						str.append("<script type=\'text/javascript\'>");
						str.append(this.printTreeJs(tree));
						str.append("var " + tree.getTreeName()
								+ " = new BSTreeView(\"" + tree.getTreeName()
								+ "\",\"" + tree.getFormname() + "\","
								+ tree.getShowType() + ",\"" + tree.getStyle()
								+ "\",\"" + tree.getFatherElement() + "\","
								+ tree.getShowCheck() + "," + tree.canDisable()
								+ ");");
						str.append(tree.getTreeName() + ".setImagesPath(\""
								+ tree.getImagePath() + "\");");
						str.append(tree.getTreeName() + ".setShowLine("
								+ tree.isShowLine() + ");");
						// 添加根结点

						// 类型为String,此处支持添加多个树。

						ArrayList rootNames = new ArrayList();
						for (int i = 0; i < treeItems.length; i++) {
//							System.out.println( i + "***" + treeItems[i].isRootNode() + "***Name:" + treeItems[i].getName());
							if (treeItems[i].isRootNode()) {
								rootNames.add(treeItems[i].getName());
								str.append("var " + treeItems[i].getName()
										+ " = " + tree.getTreeName()
										+ ".addRootNode(\""
										+ treeItems[i].getName() + "\",\""
										+ treeItems[i].getShowStr() + "\",\""
										+ treeItems[i].getJsFun() + "\",\""
										+ treeItems[i].getOpenJs() + "\",\""
										+ treeItems[i].getParas() + "\","
										+ treeItems[i].isOpen() + ","
										+ treeItems[i].isDoOpen() + ","
										+ treeItems[i].getRmAreaIndex() + ",\""
										+ treeItems[i].getopenImg() + "\",\""
										+ treeItems[i].getcloseImg() + "\",\""
										+ treeItems[i].getNodeImg() + "\");");

							}
						}
						// 添加叶子结点,由于必须先初始化上一级结点，才能初始化下一级结点，此处需要做特殊处理。

						HashMap treeMap = getTreeitemMap(treeItems);
						for (int i = 0; i < rootNames.size(); i++) {
							String root = (String) rootNames.get(i);
							str.append(printTree(root, treeMap));
						}

						str.append(tree.getTreeName() + ".DrawTree(true);");
						str.append("</script>");
					}
				}
			}
			writer.write(str.toString());
		} catch (IOException e) {
			// TODO: handle exception
			logger.error("BSTreeComponent.start error:", e);

		}
		return result;
	}

	private String printTreeJs(IBasicTree tree) {
		StringBuffer str = new StringBuffer();
		// 选中某个节点
		str.append("function rp_setSelect(rp_nodeName){");

		str.append("\tif(" + tree.getTreeName()
				+ ".getNodeByName(rp_nodeName)!=null)");
		str.append("\t\t" + tree.getTreeName()
				+ ".getNodeByName(rp_nodeName).doActived(); ");
		str.append("}");
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

	private String printTree(String fatherNode, HashMap treeMap) {
		// 如果存在该节点，则打印所有其子结点。
		StringBuffer str = new StringBuffer();
		IBasicTree tree = null;

		if (treeMap.containsKey(fatherNode)) {// 有孩子节点的情况
			ArrayList childs = (ArrayList) treeMap.get(fatherNode);
			IBasicTreeItem[] treeItems = (IBasicTreeItem[]) childs
					.toArray(new IBasicTreeItem[childs.size()]);
			for (int i = 0; i < treeItems.length; i++) {

				// 先打印该节点，然后递归打印其子节点
				str.append("var " + treeItems[i].getName() + "=" + fatherNode
						+ ".addNode(\"" + treeItems[i].getName() + "\",\""
						+ treeItems[i].getShowStr() + "\",\""
						+ treeItems[i].getJsFun() + "\",\""
						+ treeItems[i].getOpenJs() + "\",\""
						+ treeItems[i].getParas() + "\","
						+ treeItems[i].isOpen() + "," + treeItems[i].isDoOpen()
						+ "," + treeItems[i].getRmAreaIndex() + ",\""
						+ treeItems[i].getopenImg() + "\",\""
						+ treeItems[i].getcloseImg() + "\",\""
						+ treeItems[i].getNodeImg() + "\");");
				str.append(printTree(treeItems[i].getName(), treeMap));
			}
		}
		return str.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
