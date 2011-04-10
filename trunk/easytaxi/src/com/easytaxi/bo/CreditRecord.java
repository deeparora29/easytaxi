package com.easytaxi.bo;

import java.util.Date;

/**
 * @table "creditrecord"
 * @author renmian
 */
public class CreditRecord {

    private String requestNo;
    private String userid;
    private int type;
    private float credit;
    private String comments;
    private Date creditTime;
    // 被评价人：passenger被评价是userid；taxi被评价是cab(plate number)车牌号
    private String creditee;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public void setCreditee(String creditee) {
        this.creditee = creditee;
    }

    public String getCreditee() {
        return creditee;
    }

}
