package com.btit.common.tree.bo;

import java.io.Serializable;

import com.btit.common.taglib.tree.IAdaptable;
import com.btit.common.taglib.tree.IBasicTreeItem;

/**
 * 定义树形
 * @author Renmian
 *
 */
public class NormalTree implements IAdaptable, Serializable {
	
	//树节点id
	private String nodeId;
	//树节点呈现名称
	private String nodeName;
	//树的父节点id
	private String pid;
	//树的父节点名称
	private String pname; 
	
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		if (adapter == IBasicTreeItem.class) {
			return new NormalTreeItem(this);
		}else{
			return null;
		}
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
