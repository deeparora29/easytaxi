
package com.mobilesoft.smarttaxi.usermgr.bo;

import java.util.Date;

/**
 * @table "loginrecord"
 * @description record the login log
 * @author rennnmia
 */
public class LoginRecord {

    private String userid;
    // user type
    // 0:taxi, 1:passenger
    // private int type;
    private Date loginTime;
    private String account;
    private double latitude = 0;
    private double longtitude = 0;

    public LoginRecord() {
    }

    public LoginRecord(String userid, String account) {
        this.userid = userid;
        this.account = account;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

}
