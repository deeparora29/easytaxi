package com.easytaxi.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
  
/**
 * @Copyright (C) 2005-2008 亚信科技有限公司
 * @author: 付奎
 * @E-mail:fukui@asiainfo.com
 * @version 创建时间：2008-11-27 下午04:48:42
 * @comments 处理json的工具类，负责json数据转换成java对象和java对象转换成json 
 */
public class JsonUtil {  
  
    /** 
     * 从一个JSON 对象字符格式中得到一个java对象 
     *  
     * @param jsonString 
     * @param pojoCalss 
     * @return 
     */  
    public static Object getObjectByJsonString(String jsonString, Class pojoCalss) {  
        Object pojo;  
        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
        pojo = JSONObject.toBean(jsonObject, pojoCalss);  
        return pojo;  
    }  
  
  
    /** 
     * 从json HASH表达式中获取一个map，改map支持嵌套功能 
     *  
     * @param jsonString 
     * @return 
     */  
    public static Map getMapByJsonString(String jsonString) {  
        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
        Iterator keyIter = jsonObject.keys();  
        String key;  
        Object value;  
        Map valueMap = new HashMap();  
  
        while (keyIter.hasNext()) {  
            key = (String) keyIter.next();  
            value = jsonObject.get(key);  
            valueMap.put(key, value);  
        }  
  
        return valueMap;  
    }  
  
  
    /** 
     * 从json数组中得到相应java数组 
     *  
     * @param jsonString 
     * @return 
     */  
    public static Object[] getObjectArrayByJsonString(String jsonString) {  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        return jsonArray.toArray();  
  
    }  
  
  
    /** 
     * 从json对象集合表达式中得到一个java对象列表 
     *  
     * @param jsonString 
     * @param pojoClass 
     * @return 
     */  
    public static List getListByJsonString(String jsonString, Class pojoClass) {  
  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        JSONObject jsonObject;  
        Object pojoValue;  
  
        List list = new ArrayList();  
        for (int i = 0; i < jsonArray.size(); i++) {  
  
            jsonObject = jsonArray.getJSONObject(i);  
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);  
            list.add(pojoValue);  
  
        }  
        return list;  
  
    }  
  
  
    /** 
     * 从json数组中解析出java字符串数组 
     *  
     * @param jsonString 
     * @return 
     */  
    public static String[] getStringArrayByJsonString(String jsonString) {  
  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        String[] stringArray = new String[jsonArray.size()];  
        for (int i = 0; i < jsonArray.size(); i++) {  
            stringArray[i] = jsonArray.getString(i);  
  
        }  
  
        return stringArray;  
    }  
  
  
    /** 
     * 从json数组中解析出javaLong型对象数组 
     *  
     * @param jsonString 
     * @return 
     */  
    public static Long[] getLongArrayByJsonString(String jsonString) {  
  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        Long[] longArray = new Long[jsonArray.size()];  
        for (int i = 0; i < jsonArray.size(); i++) {  
            longArray[i] = jsonArray.getLong(i);  
  
        }  
        return longArray;  
    }  
  
  
    /** 
     * 从json数组中解析出java Integer型对象数组 
     *  
     * @param jsonString 
     * @return 
     */  
    public static Integer[] getIntegerArrayByJsonString(String jsonString) {  
  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        Integer[] integerArray = new Integer[jsonArray.size()];  
        for (int i = 0; i < jsonArray.size(); i++) {  
            integerArray[i] = jsonArray.getInt(i);  
  
        }  
        return integerArray;  
    }  
  
    /** 
     * 从json数组中解析出java Integer型对象数组 
     *  
     * @param jsonString 
     * @return 
     */  
    public static Double[] getDoubleArrayByJsonString(String jsonString) {  
  
        JSONArray jsonArray = JSONArray.fromObject(jsonString);  
        Double[] doubleArray = new Double[jsonArray.size()];  
        for (int i = 0; i < jsonArray.size(); i++) {  
            doubleArray[i] = jsonArray.getDouble(i);  
  
        }  
        return doubleArray;  
    }  
  
  
    /** 
     * 将java对象转换成json字符串 
     *  
     * @param javaObj 
     * @return 
     */  
    public static String getJsonStringByJavaPOJO(Object javaObj) {  
  
        JSONObject json;  
        json = JSONObject.fromObject(javaObj);  
        return json.toString();  
  
    }  
  
  
  
}  
