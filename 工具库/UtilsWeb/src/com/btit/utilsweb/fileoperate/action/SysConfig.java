package com.btit.utilsweb.fileoperate.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class SysConfig {
	
	
	private Properties config=new Properties();
	
	public SysConfig(String fileName){
		try {
			FileInputStream fin = new FileInputStream(fileName);
			
			config.load(fin); //载入文件 
			fin.close();
		} catch (IOException ex) {
			System.err.println("无法读取指定的配置文件:" + fileName);
			ex.printStackTrace(System.err);
		}
	}
	/**
	 * @param args
	 */
	
	public String getValue(String itemName){
		return config.getProperty(itemName); 
	}
	
	public void showConfig(){
		
		if(config != null){
			Set keySet = config.keySet();
			Iterator iterator = keySet.iterator();
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				String value = config.getProperty(key);
			   System.out.println(key+":   "+value);	
			}
			
	}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SysConfig s=new SysConfig("E:\\work\\Utils\\src\\extremetable.properties");
		System.out.println(s.getValue("table.filterRowsCallback.default"));
		s.showConfig();
		
	}

}
