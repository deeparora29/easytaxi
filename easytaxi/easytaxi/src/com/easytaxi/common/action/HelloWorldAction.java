package com.easytaxi.common.action;

public class HelloWorldAction extends BaseAction {

	private String pageMessage;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.setPageMessage("Hello World. This is Struts2 Demo App.");
		return SUCCESS;
	}

	public String getPageMessage() {
		return pageMessage;
	}

	public void setPageMessage(String pageMessage) {
		this.pageMessage = pageMessage;
	}
}
