package com.btit.common.utils.ajax;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * @author renmian
 * 用于构造Ajax Search Suggest所用到的XML文件
 *
 */
public class XMLConstructor {
	/**
	 * 根据结果集构造XML
	 * @param resultMap
	 * @return
	 */
	public static String construtcXml(HashMap<String, String> resultMap){
		StringBuffer xmlStr = new StringBuffer();
		xmlStr.append("<?xml version='1.0' encoding='utf-8'?><all>");
		int i = 0;
		for(Entry<String, String> entry : resultMap.entrySet()){
			xmlStr.append("<response value=\"" + entry.getKey() + "\"");
			if(i == 0){
				xmlStr.append(" result=\"共" + resultMap.size() + "个结果\"");
			}
			xmlStr.append(">" + entry.getValue() + "</response>");
			i++;
		}
		xmlStr.append("</all>");
		return xmlStr.toString();
	}
	/**
	 * 根据输入内容构造xml文件
	 * @param dataMap
	 * @param inputText
	 * @return
	 */
	public static String construtcXmlBy(HashMap<String, String> dataMap, String inputText){
		StringBuffer xmlStr = new StringBuffer();
		xmlStr.append("<?xml version='1.0' encoding='utf-8'?><all>");
		int i = 0;
		for(Entry<String, String> entry : dataMap.entrySet()){
			if(entry.getValue().indexOf(inputText) < 0){
				continue;
			}
			i++;
			xmlStr.append("<response value=\"" + entry.getKey() + "\"");
			if(i == 0){
				xmlStr.append(" result=\"共" + dataMap.size() + "个结果\"");
			}
			xmlStr.append(">" + entry.getValue() + "</response>");
		}
		xmlStr.append("</all>");
		return xmlStr.toString();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("1", "小a");
		paramMap.put("2", "小b");
		paramMap.put("3", "小c");
		
		String result = construtcXml(paramMap);
		System.out.println(result);
		
	}

}
