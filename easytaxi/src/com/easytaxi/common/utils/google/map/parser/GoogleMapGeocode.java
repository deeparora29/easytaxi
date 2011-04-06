package com.easytaxi.common.utils.google.map.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import com.easytaxi.common.SystemPara;
import net.sf.json.JSONObject;

public class GoogleMapGeocode {
	
	public  GoogleMapGeocode(){
		
	}
	
	/**
	 * 地址解析
	 * @param address 地址
	 * @return 经纬度，结果形如：lat,lng
	 */
	public static String getLatLngByAddress(String address){
		String latLng = "";
		BufferedReader in= null;
		try {
			URL url = new URL(SystemPara.GOOLGE_MAP_GECODE_JSON + "?address="+URLEncoder.encode(address,"UTF-8")+"&language=zh-CN&sensor=true");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();   
			httpConn.setDoInput(true);   
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));   
		    String line;
		    String result="";
		    while ((line = in.readLine()) != null) {   
		        result += line;   
		    }   
		    in.close();
		    JSONObject jsonObject = JSONObject.fromObject( result );
		    GoogleMapJSONBean bean = (GoogleMapJSONBean) JSONObject.toBean( jsonObject, GoogleMapJSONBean.class );
		    latLng = bean.getResults()[0].getGeometry().getLocation().lat+","+bean.getResults()[0].getGeometry().getLocation().lng;
		    //System.out.println(latLng);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return latLng;
	}
	
	/**
	 * 反向地址解析
	 * @param latLng 经纬度，格式形如：lat,lng
	 * @return 地址
	 */
	public static String getAddressByLatLng(String latLng){
		String address = "";
		BufferedReader in= null;
		try {
			URL url = new URL(SystemPara.GOOLGE_MAP_GECODE_JSON+"?latlng="+latLng+"&language=zh-CN&sensor=true");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoInput(true);   
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));   
		    String line;
		    String result="";
		    while ((line = in.readLine()) != null) {   
		        result += line;   
		    }   
		    in.close();
		    JSONObject jsonObject = JSONObject.fromObject( result );
		    GoogleMapJSONBean bean = (GoogleMapJSONBean) JSONObject.toBean( jsonObject, GoogleMapJSONBean.class );
		    address = bean.getResults()[0].formatted_address;
		    //System.out.println("address="+new String(address.getBytes("GBK"),"UTF-8"));
		    //System.out.println("address="+address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	
	
	public static void main(String[] args){
		//getLatLngByAddress("广州市");
		//getLatLngByAddress("成都市");
		getAddressByLatLng("30.658602,104.06486");
	}
	
}
