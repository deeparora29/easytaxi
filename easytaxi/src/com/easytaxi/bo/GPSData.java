package com.easytaxi.bo;

public class GPSData {
	
	private String userId;
	
	private int status ;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private DestLocation DestLocation;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DestLocation getDestLocation() {
		return DestLocation;
	}

	public void setDestLocation(DestLocation destLocation) {
		DestLocation = destLocation;
	}
}
