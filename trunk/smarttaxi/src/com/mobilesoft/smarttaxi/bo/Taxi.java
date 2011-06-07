package com.mobilesoft.smarttaxi.bo;

import java.util.Date;
import java.util.List;

/**
 * @table "taxi"
 * @modified by renmian 2011-04-04
 */
public class Taxi {

    private String userid;
	//出租车牌号
    // plate_number
    private String cab;
	
	//密码
	private String password ;
	
	//所属公司
	private String company ;
	
	//车型号
	private String carModel ;
	
	//付费方式
	private String chargeModel ;
	
	//联系邮件
	private String email ;
	
	//联系人1
	private String driver0 ;
	
	//联系电话
	private String phone0 ;
	
	//联系人2
	private String driver1 ;
	
	//联系电话2
	private String phone1 ;
	
	//说明
    private String descr;

    // license
    private String license;

    // 出租车状态0:空车,1:负载，2：其它
    private int status = 0;

    // 信用度
    private float credit = 3.0f;

    // 注册时间 DateTime
    private Date registerTime;
    // 修改时间
    private Date modifiedTime;

    private double lat ;
    
    private double lng ;
    
    private String taxiLocation ;
    
    public Taxi(){
    	
    }
    
    //出租车注册
    public Taxi(String cab,String password,String license,String company,String email,
    		String carModel,String chargeModel,List <Driver> list,String descr){
    	this.cab = cab ;
    	this.password = password ;
    	this.license = license ;
    	this.company = company ;
    	this.email = email ;
    	this.carModel = carModel ;
    	this.chargeModel = chargeModel ;
    	this.descr = descr ;
    }

    // 出租车注册
    public Taxi(String cab, String password, String license, String company, String email, String carModel,
        String chargeModel, String descr) {
        this.cab = cab;
        this.password = password;
        this.license = license;
        this.company = company;
        this.email = email;
        this.carModel = carModel;
        this.chargeModel = chargeModel;
        this.descr = descr;
    }
	
	public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getCab() {
        return cab;
	}

    public void setCab(String plateNumber) {
        this.cab = plateNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getChargeModel() {
		return chargeModel;
	}

	public void setChargeModel(String chargeModel) {
		this.chargeModel = chargeModel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDriver0() {
		return driver0;
	}

	public void setDriver0(String driver0) {
		this.driver0 = driver0;
	}

	public String getPhone0() {
		return phone0;
	}

	public void setPhone0(String phone0) {
		this.phone0 = phone0;
	}

	public String getDriver1() {
		return driver1;
	}

	public void setDriver1(String driver1) {
		this.driver1 = driver1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

    public String getDescr() {
        return descr;
	}

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

	public String getTaxiLocation() {
		return taxiLocation;
	}

	public void setTaxiLocation(String taxiLocation) {
		this.taxiLocation = taxiLocation;
	}
	
}
