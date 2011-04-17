package com.easytaxi.bo;

import java.util.Date;

public class TrackHistory {
	
	private String trackid;
	
	private String userid ;
	
	private int type;
	
	private Date begintime ;
	
	private Date endtime ;
	
	private String trackfile ;

	private double lat;
	
	private double lng;
	
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

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTrackfile() {
		return trackfile;
	}

	public void setTrackfile(String trackfile) {
		this.trackfile = trackfile;
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
