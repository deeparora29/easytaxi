package com.btit.common.tree.bo;

import com.btit.common.taglib.tree.IBasicTreeItem;

/**
 * 定义树节点上的信息
 * @author lenovo
 *
 */
public class NormalTreeItem implements IBasicTreeItem {
	private NormalTree normalTree;
	
	private boolean isRootNode = false;// 该节点是否根节点
	
	public NormalTreeItem(NormalTree normalTree){
		this.normalTree = normalTree;
		
	}
	
	public String getcloseImg() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getFatherName() {
		// TODO Auto-generated method stub
		return "node" + "_" + normalTree.getPid();
	}

	public String getId() {
		// TODO Auto-generated method stub
		return normalTree.getNodeId();
	}

	public String getJsFun() {
		// TODO Auto-generated method stub
		//return "";
		return "alert('[nodeId=" + normalTree.getNodeId() + "]" + normalTree.getNodeName() + "');";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "node" + "_" + normalTree.getNodeId();
	}

	public String getNodeImg() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getopenImg() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getOpenJs() {
		// TODO Auto-generated method stub
		return "";
	}

	public Object getOtherData() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRightMenuJsFun() {
		// TODO Auto-generated method stub
		return "";
	}

	public int getRmAreaIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getShowStr() {
		// TODO Auto-generated method stub
		return normalTree.getNodeName();
	}

	public boolean isDoOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDoOpen(boolean isDoOpen) {
		// TODO Auto-generated method stub
		
	}

	public void setOpen(boolean isOpen) {
		// TODO Auto-generated method stub
		
	}

	public NormalTree getNormalTree() {
		return normalTree;
	}

	public void setNormalTree(NormalTree normalTree) {
		this.normalTree = normalTree;
	}

	public boolean isRootNode() {
		// TODO Auto-generated method stub
		return normalTree.getPid().equals("-1");
	}

	public void setRootNode(boolean isRootNode) {
		this.isRootNode = isRootNode;
	}

}
