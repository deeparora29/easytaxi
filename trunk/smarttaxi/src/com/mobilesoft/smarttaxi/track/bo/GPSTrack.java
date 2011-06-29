package com.mobilesoft.smarttaxi.track.bo;

import java.util.Date;
/**
 * Track gps data
 * @table "tracklog"
 * @author renmian
 *
 */
public class GPSTrack {
	//yyyymmdd0000
	private String trackid;
	private String userid;
	private String requestNo;
	private int type;
	private Date createtime;
	private double lat;
	private double lng;
	private float speed = 0.0f;
	private int direction = 0;
	private int status = 0;
	
	public GPSTrack(){}
	
	public GPSTrack(String userid, double lat, double lng){
		this.userid = userid;
		this.lat = lat;
		this.lng = lng;
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
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
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
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
