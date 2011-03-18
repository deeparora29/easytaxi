/**
 * <p>Copyright ? 2009 BTIT International Group Co. Ltd. </p>
 * <p>Project            :BASE</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :缓存工具接口</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2009.05.27</p>
 * <p>1.2009.05.27 yuxd new</p>
 **/
package com.btit.support.util;

/**
 * <p>Title: 缓存工具接口 </p>
 * <p>Description: 缓存工具接口</p>
 * <p>Copyright: Copyright ? 2009</p>
 * <p> Company: BTIT </p>
 * 
 * @author :yuxd
 * @version 2.0
 */
public interface ICacheUtil {
	/**
	 * 生成缓存映射KEY方法
	 * @param tagName
	 * @param object
	 * @return
	 */
	public String getKey(String tagName, Object object);
	/**
	 * 得到缓存中对应值
	 * @param key
	 * @return
	 */
	public Object get(String key);
	/**
	 * 把key-value值放入缓存中
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
}
