package com.mobilesoft.smarttaxi.bo;

public class Driver {

	private String name ;
	
	private String phone ;

    public Driver() {
    }

    public Driver(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
