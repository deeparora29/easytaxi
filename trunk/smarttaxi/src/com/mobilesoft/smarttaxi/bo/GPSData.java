package com.mobilesoft.smarttaxi.bo;

public class GPSData {
	
	

	private double lat;

	private double lng;
	
	private String text ;

	public GPSData(){
		
	}
	
	public GPSData(double lat , double lng){
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


}
