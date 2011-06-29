package com.mobilesoft.smarttaxi.bo;

import java.util.Date;

public class GPSData {
	
	private String userid;

	private double lat;

	private double lng;
	
	private String text ;
	
	private Date createtime;

	public GPSData(){
		
	}
	
	public GPSData(double lat , double lng){
		this.lat = lat;
        this.lng = lng;
	}
	
	public GPSData(String userid, double lat , double lng){
		this.setUserid(userid);
		this.lat = lat;
        this.lng = lng;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getCreatetime() {
		return createtime;
	}


}
