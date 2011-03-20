package com.easytaxi.common.tree.bo;

import java.io.Serializable;

import com.easytaxi.common.taglib.tree.IAdaptable;
import com.easytaxi.common.taglib.tree.IBasicTreeItem;
/**
 * 构造员工树的数据结构
 * @author renmian
 *
 */
public class StaffInfoTree implements IAdaptable, Serializable {
	//树节点id
	private String nodeId;
	//树节点呈现名称
	private String nodeName;
	//树的父节点id
	private String pid;
	//树的父节点名称
	private String pname;
	
	private String jsFunc;
	
	private Object otherData;
	

	public Object getOtherData() {
		return otherData;
	}


	public void setOtherData(Object otherData) {
		this.otherData = otherData;
	}


	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		if (adapter == IBasicTreeItem.class) {
			return new StaffInfoTreeItem(this);
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


	public String getJsFunc() {
		return jsFunc;
	}


	public void setJsFunc(String jsFunc) {
		this.jsFunc = jsFunc;
	}
}
