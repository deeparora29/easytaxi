package com.easytaxi.common.tree.bo;

import org.apache.commons.lang.StringUtils;

import com.easytaxi.common.taglib.tree.IBasicTreeItem;
/**
 * 员工树节点
 * @author renmian
 *
 */
public class StaffInfoTreeItem implements IBasicTreeItem {
	private StaffInfoTree staffInfoTree;
	
	private boolean isRootNode = false;// 该节点是否根节点
	
	public StaffInfoTreeItem(StaffInfoTree staffInfoTree){
		this.staffInfoTree = staffInfoTree;
		
	}

	public String getcloseImg() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getFatherName() {
		// TODO Auto-generated method stub
		return "node" + "_" + staffInfoTree.getPid();
	}

	public String getId() {
		// TODO Auto-generated method stub
		return staffInfoTree.getNodeId();
	}

	public String getJsFun() {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(staffInfoTree.getJsFunc()))
			return staffInfoTree.getJsFunc();
		else
			return "";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "node" + "_" + staffInfoTree.getNodeId();
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
		return staffInfoTree.getNodeName();
	}

	public boolean isDoOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLazyload() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRootNode() {
		// TODO Auto-generated method stub
		return staffInfoTree.getPid().equals("-1");
	}

	public void setDoOpen(boolean isDoOpen) {
		// TODO Auto-generated method stub
		
	}

	public void setOpen(boolean isOpen) {
		// TODO Auto-generated method stub
	}
	
	public Object getOtherData() {
		// TODO Auto-generated method stub
		return staffInfoTree.getOtherData();
	}

}
