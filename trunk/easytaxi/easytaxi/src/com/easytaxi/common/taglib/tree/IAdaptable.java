package com.easytaxi.common.taglib.tree;

/**
 * 所有可以被适配的资源的接口
 * @author renmian
 *
 */
public interface IAdaptable {
	/**
	 * 得到当前对象的apapter
	 * 
	 * @param adapter
	 *            目标接口
	 * @return 可以被转型为参数接口的类
	 */
	public Object getAdapter(Class adapter);

}
