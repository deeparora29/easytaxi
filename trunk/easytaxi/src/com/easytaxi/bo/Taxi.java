package com.easytaxi.bo;

import java.util.Date;

/**
 * @table "taxi"
 * @modified by renmian 2011-04-04
 */
public class Taxi {

    private String userid;
	//出租车牌号
	private String plateNumber ;
	
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
    private String descrs;

    // license
    private String license;

    // 出租车状态
    private int status;

    // 信用度
    private float credit;

    // 注册时间 DateTime
    private Date registerTime;
    // 修改时间
    private Date modifiedTime;

	
	public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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

    public String getDescrs() {
        return descrs;
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

    public void setDescrs(String descrs) {
        this.descrs = descrs;
	}

	
}
