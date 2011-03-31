package com.easytaxi.bo;

public class DestLocation {

	private long lat;

	private long lng;
	
	private String text ;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}

}
