package com.easytaxi.bo;

import java.util.Date;

/**
 * @table "passenger"
 * @modified by renmian 2011-04-04
 */
public class Passenger {
	private String transCode;

    // 电话
	private String phone;

	private String email;

	private String password;

	private String firstname;

	private String lastname;

    private String nickname;
    // value:female/male
    private String gender = "male";

	private String descr;

	private GPSData destLocation;

	private int number;

	private int luggage;

	private String otherInfos;
    // 用户primary key
	private String userid;
	
	private String share ;

    // 是否同意协议
    // value:yes/no
    private String agreement = "yes";

    private Date registerTime;
    private Date modifiedTime;
    private String province;
    private String city;
    // 信用度
    private float credit = 3.0f;
    
    //图片
    private int picid = 0;

	/*** 乘客注册*/
	public Passenger(String transCode, String firstname, String lastname,
			String password, String phone, String email, String nickName,
			String gender, String descr) {
		this.transCode =  transCode;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.phone = phone;
		this.email = email;
        this.nickname = nickName;
		this.gender = gender;
		this.descr = descr;
	}

	
	
	/*** 信用评价*/
	public Passenger(String transCode, String phone,String plateNumber){
		this.transCode =  transCode;
	}

    public Passenger() {
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

    public String getNickname() {
        return nickname;
	}

    public void setNickname(String nickname) {
        this.nickname = nickname;
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


	public GPSData getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(GPSData destLocation) {
		this.destLocation = destLocation;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    
    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getCredit() {
        return credit;
    }

}
