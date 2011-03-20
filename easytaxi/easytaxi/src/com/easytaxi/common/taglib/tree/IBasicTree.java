package com.easytaxi.common.taglib.tree;

/**
 * basic tree info
 * @author renmian
 *
 */
public interface IBasicTree {
	
	/**
	 * @return [String] 树对象的页面唯一ID。
	 */ 
	public String getTreeName();
	/**
	 * 树对象的显示
	 * @return
	 */
	public String getTreeShowStr();

	/**
	 * @return [String] form名称，没有可以为空。
	 * @deprecated
	 */ 
	public String getFormname();

	/**
	 * @return [String] 是否为即点即查树。true为是；false为不是。
	 * @deprecated
	 */ 
	public boolean getShowType();

	/**
	 * @return [String] 树的式样ID，暂填为""。
	 * @deprecated
	 */ 
	public String getStyle();

	/**
	 * @return [String] 加载该树的父元素名，可为空。
	 * @deprecated
	 */ 
	public String getFatherElement();

	/**
	 * @return 是否显示CheckBox。true：显示；false：不显示；缺省为显示。
	 */ 
	public boolean getShowCheck();

	/**
	 * @return 是否可用disabled。true：不可用；false：可用；缺省为可用。
	 */ 
	public boolean canDisable();
	
	/**
	 * @return 用于显示树的图片的路径，如："../images/"
	 */ 
	public String getImagePath();
	
	/**
	 * @return 树是否需要连线
	 */ 
	public boolean isShowLine();
	/**
	 * 是否进行懒加载
	 * @return
	 */
	public boolean isLazyLoad();

}
