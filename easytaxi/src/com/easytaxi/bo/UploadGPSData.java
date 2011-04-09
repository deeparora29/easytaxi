package com.easytaxi.bo;

public class UploadGPSData {
	
	private String userId;
	
	private int status ;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private GPSData DestLocation;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public GPSData getDestLocation() {
		return DestLocation;
	}

	public void setDestLocation(GPSData destLocation) {
		DestLocation = destLocation;
	}
}
