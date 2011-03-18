package com.btit.common.taglib.tree;

/**
 * 所有需要在树上显示的数据必须实现的接口
 * @author renmian
 *
 */
public interface IBasicTreeItem {

	/**
	 * @return 关键字，节点的业务唯一标示。  
	 */ 
	public String getName();
	
	/**
	 * 获取树节点的自定义的属性id
	 * @return
	 */
	public String getId();
	/**
	 * @return 显示文字，节点显示的文字。  
	 */ 
	public String getShowStr();

	/**
	 * @return 点击节点执行的JS脚本方法。  
	 */
	public String getJsFun();
	/**
	 * @return 打开/关闭节点执行额外的JS方法，在即点即查方式下有效。 
	 * @deprecated 
	 */
	public String getOpenJs();
	/**
	 * @return 该节点存放的参数。形式为：&name1=value1&name2=value2  
	 * @deprecated
	 */
	public String getParas();
	/**
	 * @return 该节点是否打开，true为打开；false为关闭。  
	 */
	public boolean isOpen();
	/**
	 * @return 该节点是否已经执行过打开节点的操作，在即点即查方式下有效。  
	 */
	public boolean isDoOpen();
	/**
	 * @return 该节点打开状态下的图片：建议16*16。  
	 * @deprecated
	 */
	public String getopenImg();
	/**
	 * @return 该节点关闭状态下的图片：建议16*16。  
	 * @deprecated
	 */
	public String getcloseImg();
	/**
	 * @return 该节点为叶子时的图片：建议16*16。  
	 */
	public String getNodeImg();
	
	/**
	 * 是否是根结点
	 * @return
	 */
	public boolean isRootNode();
	
	/**
	 * 得到父结点的Name
	 * @return
	 */
	public String getFatherName();
	
	/**
	 * 得到右键菜单的索引，如果没有右键菜单，请设为1。
	 * @deprecated
	 * @return
	 */
	public int getRmAreaIndex();
	/**
	 * 设置节点能打开
	 * @param isDoOpen
	 */
	public void setDoOpen(boolean isDoOpen);
	/**
	 * 设置节点打开与否
	 * @param isOpen
	 */
	public void setOpen(boolean isOpen); 
	/**
	 * 获取右键菜单点击js
	 * @return
	 */
	public String getRightMenuJsFun();
	/**
	 * 做保留字自定义节点（方便扩展）
	 * @return
	 */
	public Object getOtherData();
}
