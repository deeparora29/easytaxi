/**
 * <p>Copyright ? 2009 BTIT International Group Co. Ltd. </p>
 * <p>Project            :BASE</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :缓存工具本地接口实现</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2009.05.27</p>
 * <p>1.2009.05.27 yuxd new</p>
 **/
package com.btit.support.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: 缓存工具本地接口实现 </p>
 * <p>Description: 缓存工具本地接口实现 </p>
 * <p>Copyright: Copyright ? 2009</p>
 * <p> Company: BTIT </p>
 * 
 * @author :yuxd
 * @version 2.0
 */
public class LocalCacheUtil implements ICacheUtil {
	public static Map localCacheMap = new HashMap();
	
	public Object get(String key) {
		return localCacheMap.get(key);
	}

	public String getKey(String tagName, Object object) {
		if(object instanceof String){
			return tagName + "_" + object.toString();
		}else if(object instanceof Long){
			return tagName + "_" + (Long)object;
		}
		
		return tagName + "_" + object.toString();
	}

	@SuppressWarnings("unchecked")
	public void put(String key, Object value) {
		localCacheMap.put(key, value);
		
	}

}
