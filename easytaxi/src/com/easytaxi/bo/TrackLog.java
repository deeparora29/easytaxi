package com.easytaxi.bo;

import java.util.Date;

public class TrackLog {
	
	private Integer id ;
	
	private String trackid ;
	
	private String userid ;
	
	private int type ;
	
	private Date createtime ;
	
	private double lat ;
	
	private double lng ;

	public TrackLog(String trackid,String userid,int type,double lat,double lng){
		this.trackid = trackid;
		this.userid = userid;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
	}
	
	public TrackLog(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrackid() {
		return trackid;
	}

	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	
}
