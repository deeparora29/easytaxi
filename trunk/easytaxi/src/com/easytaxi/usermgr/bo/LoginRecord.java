
package com.easytaxi.usermgr.bo;

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
    private int type;
    private Date loginTime;
    private String phone;
    private double latitude;
    private double longtitude;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

}
