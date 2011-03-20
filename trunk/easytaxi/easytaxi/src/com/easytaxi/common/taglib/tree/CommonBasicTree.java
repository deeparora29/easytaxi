package com.easytaxi.common.taglib.tree;

/**
 * 默认的树，如果需要修改，请extends该方法，并覆盖该方法。
 * @author renmian
 *
 */
public abstract class CommonBasicTree implements IBasicTree {
	/**
	 *  树图片的目录
	 */
	private String context;
	
	public CommonBasicTree(String context) {
		super();
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public CommonBasicTree() {

	}
	/**
	 * @deprecated
	 */
	public boolean canDisable() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @deprecated
	 */
	public String getFatherElement() {
		// TODO Auto-generated method stub
		return "";
	}
	/**
	 * @deprecated
	 */
	public String getFormname() {
		// TODO Auto-generated method stub
		return "";
	}
	/**
	 *  是否采用复选框树
	 *  default：false
	 */
	public boolean getShowCheck() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @deprecated
	 */
	public boolean getShowType() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 
	 */
	public String getStyle() {
		// TODO Auto-generated method stub
		return "";
	}
	/**
	 * 缺省树名
	 */
	public String getTreeName() {
		return "commonBasic";
	}
	
	public boolean isShowLine() {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 缺省树根节点呈现名称
	 */
	public String getTreeShowStr() {
		// TODO Auto-generated method stub
		return "所有";
	}
	/**
	 * 是否支持懒加载
	 * default:不支持
	 */
	public boolean isLazyLoad(){
		return false;
	}

}
