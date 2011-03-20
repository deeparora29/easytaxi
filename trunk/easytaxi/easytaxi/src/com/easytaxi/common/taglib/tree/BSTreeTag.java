package com.easytaxi.common.taglib.tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * BinaryStarTree标签
 * @author renmian
 *
 */
public class BSTreeTag extends ComponentTagSupport {
	
	private String id;

	private String scope;

	private String treeDesc;

	private String treeNodes;
	
	

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return new BSTreeComponent(arg0);
	}
	
	
	
	@Override
	protected void populateParams() {
		// TODO Auto-generated method stub
		super.populateParams();
		
		BSTreeComponent bsTreeComponent = (BSTreeComponent)component;
		bsTreeComponent.setId(id);
		bsTreeComponent.setScope(scope);
		bsTreeComponent.setTreeDesc(treeDesc);
		bsTreeComponent.setTreeNodes(treeNodes);
	}



	@Override
	public String getId() {
		return id;
	}

	@Override
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
