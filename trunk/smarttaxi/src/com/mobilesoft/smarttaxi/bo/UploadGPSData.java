package com.mobilesoft.smarttaxi.bo;

public class UploadGPSData {
	
	private String userId;
	
	private int status ;
	
	private String cab ;

	private GPSData gpsdata;
	
	private String track ;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	public GPSData getGpsdata() {
		return gpsdata;
	}

	public void setGpsdata(GPSData gpsdata) {
		this.gpsdata = gpsdata;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}
	
	
}
