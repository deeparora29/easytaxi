package com.easytaxi.bo;

public class Passenger {
	private String transCode ;
	
	private String phone;

	private String email;

	private String password;

	private String firstname;

	private String lastname;

	private String nickName;

	private String gender;

	private String descr;

	private String startLocation;

	private String endLocation;

	private int number;

	private int luggage;

	private String otherInfos;

	private int userid;

	
	//乘客注册
	public Passenger(String transCode , String firstname, String lastname, String password,
			String phone, String email, String nickName, String gender,
			String descr) {
		this.firstname = firstname ;
		this.lastname = lastname ;
		this.password = password ;
		this.phone = phone ;
		this.email = email ;
		this.nickName = nickName ;
		this.gender = gender ;
		this.descr = descr ;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLuggage() {
		return luggage;
	}

	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}

	public String getOtherInfos() {
		return otherInfos;
	}

	public void setOtherInfos(String otherInfos) {
		this.otherInfos = otherInfos;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
