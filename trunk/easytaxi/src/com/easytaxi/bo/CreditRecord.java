package com.easytaxi.bo;

import java.util.Date;

/**
 * @table "creditrecord"
 * @author renmian
 */
public class CreditRecord {

    private String requestNo;
    // 被评价者
    private String userid;
    private float credit;
    private String comments;
    private Date creditTime;
    // 评价者
    private String creditUserid;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public void setCreditUserid(String creditUserid) {
        this.creditUserid = creditUserid;
    }

    public String getCreditUserid() {
        return creditUserid;
    }

}
