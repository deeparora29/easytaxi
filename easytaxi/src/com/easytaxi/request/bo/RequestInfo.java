
package com.easytaxi.request.bo;

import java.util.Date;

/**
 * @table "requestinfo"
 * @author rennnmia
 */
public class RequestInfo {
    private String requestNo;
    private String userid;
    // ---location
    private double startLong = 0;
    private double startLat = 0;
    private String startText = "";
    private double endLong = 0;
    private double endLat = 0;
    private String endText = "";
    // ---end
    private Date requestTime;
    private int number = 1;
    private int luggage = 0;
    private String comments;
    // 是否支持拼车
    // value:yes/no
    private String share = "yes";
    // 订单状态
    // value: 0:isValied; 1:confirmed; 2:canceled
    private int status = 0;
    private Date operatorTime;
    private String operatorid;
    // value:0:taxi, 1:passenger
    private int operatorType;
    private String operatorComments;

    // taxi&passenger info

    public RequestInfo() {
    }

    public RequestInfo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public double getStartLong() {
        return startLong;
    }

    public void setStartLong(double startLong) {
        this.startLong = startLong;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public String getStartText() {
        return startText;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    public double getEndLong() {
        return endLong;
    }

    public void setEndLong(double endLong) {
        this.endLong = endLong;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorComments() {
        return operatorComments;
    }

    public void setOperatorComments(String operatorComments) {
        this.operatorComments = operatorComments;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

}
