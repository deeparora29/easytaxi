package com.btit.common.taglib.tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class XYTreeTag extends ComponentTagSupport {

	private String divId; //tree存放div id
	
	private String treeId;  //div tree 名称

	private String scope;

	private String treeDesc;

	private String treeNodes;
	
	

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return new XYTreeComponent(arg0);
	}
	
	
	
	@Override
	protected void populateParams() {
		// TODO Auto-generated method stub
		super.populateParams();
		
		XYTreeComponent xyTreeComponent = (XYTreeComponent)component;
		xyTreeComponent.setDivId(divId);
		xyTreeComponent.setTreeId(treeId);
		xyTreeComponent.setScope(scope);
		xyTreeComponent.setTreeDesc(treeDesc);
		xyTreeComponent.setTreeNodes(treeNodes);
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
